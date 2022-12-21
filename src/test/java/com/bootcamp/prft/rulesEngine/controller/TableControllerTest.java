package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.dto.*;
import com.bootcamp.prft.rulesEngine.mapper.*;
import com.bootcamp.prft.rulesEngine.model.*;
import com.bootcamp.prft.rulesEngine.service.TableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TableControllerTest {

    private TableService tableService;
    private TableMapper tableMapper;
    private RowMapper rowMapper;
    private TableSimplifiedMapperDTO tableSimplifiedMapperDTO;
    private RuleMapper ruleMapper;
    private TableController tableController;

    private ColumnInformationMapper columnInformationMapper;

    private List<RowDTO> rowsDTO;

    @BeforeEach
    public void init(){
        tableService = mock(TableService.class);
        tableMapper = mock(TableMapper.class);
        rowMapper = mock(RowMapper.class);
        tableSimplifiedMapperDTO = mock(TableSimplifiedMapperDTO.class);
        ruleMapper = mock(RuleMapper.class);
        columnInformationMapper = mock(ColumnInformationMapper.class);
        tableController = new TableController(tableService, tableMapper, rowMapper, tableSimplifiedMapperDTO, ruleMapper, columnInformationMapper);
    }

    public void stage1(){
        List<RowCellDTO> cells = new ArrayList<>();
        cells.add(new RowCellDTO<>(2, "id"));
        cells.add(new RowCellDTO<>(2022, "year"));
        cells.add(new RowCellDTO<>(true, "in_cinema"));
        cells.add(new RowCellDTO<>("Black Panther: Wakanda Forever", "name"));
        cells.add(new RowCellDTO<>("Description of wakanda forever", "description"));
        rowsDTO = new ArrayList<>();
        rowsDTO.add(new RowDTO(cells));
    }

    @Test
    public void testGetRowsByRule(){
        stage1();
        List<ExpressionDTO> expressionDTOS = new ArrayList<>();
        List<Expression> expressions = new ArrayList<>();
        expressionDTOS.add(new ExpressionDTO(1, new ComparisonDTO("value", "in_cinema", "true", "=")));
        expressions.add(new Expression(null,1, new Comparison(null,"value", "in_cinema", "true", "=")));
        RuleDTO ruleDTO = new RuleDTO("movie", "1", expressionDTOS, "in_cinema igual que true");
        Rule rule = new Rule(null, "movie", "1", expressions, "in_cinema igual que true");

        when(tableMapper.fromTable(any())).thenReturn(new TableDTO(rowsDTO));
        when(ruleMapper.fromDTO(any())).thenReturn(rule);

        TableDTO tableDTO = tableController.getRowsByRule(ruleDTO);
        assertNotNull(tableDTO);
        verify(tableService, times(1)).getRowsByRule(rule);
    }

    @Test
    public void testAddRowByRule(){
        stage1();
        List<RowCell> cells = new ArrayList<>();
        cells.add(new RowCell<>(2, "id"));
        cells.add(new RowCell<>(2022, "year"));
        cells.add(new RowCell<>(true, "in_cinema"));
        cells.add(new RowCell<>("Black Panther: Wakanda Forever", "name"));
        cells.add(new RowCell<>("Description of wakanda forever", "description"));
        Row row = new Row(cells);
        TableSimplifiedDTO tableSimpDTO = new TableSimplifiedDTO("movie");

        when(rowMapper.fromDTO(any())).thenReturn(row);

        tableController.addRow(rowsDTO.get(0), tableSimpDTO);
        verify(tableService, times(1)).addRow(row, "movie");
    }

    @Test
    public void testGetTables(){
        List<TableSimplifiedDTO> tableSimplifiedDTOS = new ArrayList<>();
        tableSimplifiedDTOS.add(new TableSimplifiedDTO("movie"));
        tableSimplifiedDTOS.add(new TableSimplifiedDTO("cinema"));
        tableSimplifiedDTOS.add(new TableSimplifiedDTO("menu"));

        when(tableSimplifiedMapperDTO.fromTableSimplified(any())).thenReturn(tableSimplifiedDTOS);
        tableController.getTables();
        verify(tableService, times(1)).getTables();
    }

    public void testGetColumns(){
        List<ColumnInformationDTO> columnInformationDTOList = new ArrayList<>();
        columnInformationDTOList.add(new ColumnInformationDTO("movie_test", "id", "uuid"));
        columnInformationDTOList.add(new ColumnInformationDTO("movie_test", "name", "text"));
        columnInformationDTOList.add(new ColumnInformationDTO("movie_test", "year_publication", "numeric"));
        columnInformationDTOList.add(new ColumnInformationDTO("movie_test", "description", "character varying"));
        columnInformationDTOList.add(new ColumnInformationDTO("movie_test", "in_cinema", "character varying"));

    }

//    CREATE TABLE IF NOT EXISTS movie_test(
//            id UUID PRIMARY KEY,
//            name TEXT NOT NULL,
//            year_publication NUMERIC NOT NULL,
//            description VARCHAR(500) NOT NULL,
//    in_Cinema boolean NOT NULL,
//    unique (name)
//);
}
