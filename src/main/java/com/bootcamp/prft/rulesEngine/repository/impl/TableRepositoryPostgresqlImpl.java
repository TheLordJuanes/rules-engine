package com.bootcamp.prft.rulesEngine.repository.impl;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.constant.TypeData;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.bootcamp.prft.rulesEngine.mapper.ColumnInformationRowMapper;
import com.bootcamp.prft.rulesEngine.mapper.RecordMapper;
import com.bootcamp.prft.rulesEngine.mapper.TableSimplifiedMapperSQL;
import com.bootcamp.prft.rulesEngine.model.*;
import com.bootcamp.prft.rulesEngine.repository.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Dictionary;
import java.util.List;

@AllArgsConstructor
public class TableRepositoryPostgresqlImpl implements TableRepository {

    @Qualifier("jdbcTemplate")
    private final JdbcTemplate jdbcTemplate;
    private Dictionary<String, TypeData> dictionary;

    @Override
    public List<ColumnInformation> getInfoOfTableColumns(String tableName) {
        String sql = " SELECT table_name, column_name, data_type FROM information_schema.columns WHERE table_name = ?";
        return jdbcTemplate.query(sql, new ColumnInformationRowMapper(), tableName);
    }

    @Override
    public Table getRowsByRule(Rule rule) {
        String[] expressions = new String[2];
        expressions[0] = "";
        expressions[1] = rule.getEncoded();
        String tableName = rule.getTableName();
        convertRuleToPostgresql(expressions, rule, tableName);
        String sql = "SELECT * FROM " + tableName + " WHERE " + expressions[0];
        List<ColumnInformation> columnsInformation = getInfoOfTableColumns(tableName);
        List<Row> rows = jdbcTemplate.query(sql, new RecordMapper(columnsInformation, dictionary));
        return new Table(rows);
    }

    private void convertRuleToPostgresql(String[] expressions, Rule rule, String tableName){
        while (expressions[1].length()>0){
            convertExpressionAndLogicalConnector(expressions, rule, tableName);
        }
    }

    private void convertExpressionAndLogicalConnector(String[] expressions, Rule rule, String tableName){
        convertExpressionToPostgresql(expressions, rule, tableName);
        if(isThereAValidLogicalConnectorNext(expressions[1])){
            convertLogicalConnectorToPostgresql(expressions);
        }else if (expressions[1].length() > 0 && !isThereAClosingParenthesisNext(expressions[1])) {
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
        }
    }

    private void convertLogicalConnectorToPostgresql(String[] expressions){
        char currentCharacter = expressions[1].charAt(0);
        switch (currentCharacter){
            case 'Y':
                expressions[0] += " AND ";
                break;
            case 'O':
                expressions[0] += " OR ";
                break;
            default:
                throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
        }
        expressions[1] = expressions[1].substring(1);
    }

    private void convertExpressionToPostgresql(String[] expressions, Rule rule, String tableName){
        char currentCharacter = expressions[1].charAt(0);
        boolean isThereAParenthesisInTheExpression = false;
        expressions[1] = expressions[1].substring(1);

        if(currentCharacter == '('){
            isThereAParenthesisInTheExpression = true;
            expressions[0] += "(";
            convertExpressionWithParenthesesToPostgresql(expressions, rule, tableName);
        } else if (isAExpression(currentCharacter)) {
            expressions[0] += convertExpression(currentCharacter, rule, tableName);
        } else{
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
        }

        if(isThereAParenthesisInTheExpression){
            if(isThereAClosingParenthesisNext(expressions[1])){
                expressions[0] += ")";
                expressions[1] = expressions[1].substring(1);
            }else {
                throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
            }
        }
    }

    private void convertExpressionWithParenthesesToPostgresql(String[] expressions, Rule rule, String tableName){
        char currentCharacter;
        boolean existAClosingParenthesis = false;
        do{
            convertExpressionAndLogicalConnector(expressions, rule, tableName);
            try{
                currentCharacter = expressions[1].charAt(0);
            }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
            }

            if(currentCharacter == ')'){
                existAClosingParenthesis = true;
            }
        }
        while (!existAClosingParenthesis && expressions[1].length()>1);
        if(!existAClosingParenthesis){
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
        }
    }

    private boolean isThereAClosingParenthesisNext(String expression){
        if(expression.length() >= 1){
            char currentCharacter = expression.charAt(0);
            return currentCharacter == ')';
        }
        return false;
    }

    private boolean isThereAValidLogicalConnectorNext(String expression){
        if(expression.length() > 1){
            char currentCharacter = expression.charAt(0);
            return isALogicalConnector(currentCharacter);
        }
        return false;
    }

    private String convertExpression(char expression, Rule rule, String tableName){
        Expression expression1 = getExpressionByNumber(String.valueOf(expression), rule);
        if(expression1 == null){
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_01, RuleErrorCode.CODE_01.getMessage()));
        }
        return expression1.getComparison().convertToSQL(getInfoOfTableColumns(tableName), dictionary);
    }

    private Expression getExpressionByNumber(String number, Rule rule){
        List<Expression> expressions = rule.getExpressions();
        int numberInt = Integer.parseInt(number);
        for(Expression expression: expressions){
            if(expression.getNumber() == numberInt){
                return expression;
            }
        }
        return null;
    }

    private boolean isALogicalConnector(char currentCharacter){
        String logicalConnectors = "YO";
        return logicalConnectors.indexOf(currentCharacter) >= 0;
    }

    private boolean isAExpression(char currentCharacter){
        String expressions = "1234";
        return expressions.indexOf(currentCharacter) >= 0;
    }

    @Override
    public void saveRow(Row row, String tableName) {
        String sql = "INSERT INTO " + getTableForInsert(row, tableName) + " VALUES " + getValuesForInsert(row);
        jdbcTemplate.update(sql);
    }

    @Override
    public List<TableSimplified> getTablesOfDataBase() {
        String sql = "SELECT tablename as table_name FROM pg_catalog.pg_tables WHERE schemaname = 'public'";
        List<TableSimplified> tablesSimplified = jdbcTemplate.query(sql, new TableSimplifiedMapperSQL());
        deleteFlyWayTable(tablesSimplified);
        return tablesSimplified;
    }

    private void deleteFlyWayTable(List<TableSimplified> tablesSimplified) {
        for (int i = 0; i < tablesSimplified.size(); i++) {
            if (tablesSimplified.get(i).getTableName().equals("flyway_schema_history")) {
                tablesSimplified.remove(i);
                break;
            }
        }
    }

    private String getTableForInsert(Row row, String tableName) {
        StringBuilder tableWithColumns = new StringBuilder(tableName + "(");
        List<RowCell> cells = row.getRow();
        for (int i = 0; i < cells.size()-1; i++) {
            tableWithColumns.append(cells.get(i).getCellName()).append(", ");
        }
        tableWithColumns.append(cells.get(cells.size() - 1).getCellName()).append(")");
        return tableWithColumns.toString();
    }

    private String getValuesForInsert(Row row) {
        StringBuilder values = new StringBuilder("(");
        List<RowCell> cells = row.getRow();
        for (int i = 0; i < cells.size()-1; i++) {
            String value = getValue(cells, i);
            values.append(value).append(", ");
        }
        String value = getValue(cells, cells.size()-1);
        values.append(value).append(")");
        return values.toString();
    }

    private String getValue(List<RowCell> cells, int i) {
        String value = cells.get(i).getValue().toString();
        if (cells.get(i).getValue() instanceof String)
            value = "'"+value+"'";
        return value;
    }
}