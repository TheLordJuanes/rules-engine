package com.bootcamp.prft.rulesEngine.service.impl;

import com.bootcamp.prft.rulesEngine.constant.RuleErrorCode;
import com.bootcamp.prft.rulesEngine.error.exception.RuleError;
import com.bootcamp.prft.rulesEngine.error.exception.RuleException;
import com.bootcamp.prft.rulesEngine.model.Expression;
import com.bootcamp.prft.rulesEngine.model.Rule;
import com.bootcamp.prft.rulesEngine.repository.ComparisonRepository;
import com.bootcamp.prft.rulesEngine.repository.ExpressionRepository;
import com.bootcamp.prft.rulesEngine.repository.RuleRepository;
import com.bootcamp.prft.rulesEngine.service.RuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RuleServiceImpl implements RuleService {

    private RuleRepository ruleRepository;
    private ExpressionRepository expressionRepository;
    private ComparisonRepository comparisonRepository;

    @Override
    public Rule getRule(UUID ruleId) {
        return ruleRepository.findById(ruleId).orElseThrow(() -> new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_06, RuleErrorCode.CODE_06.getMessage())));
    }

    @Override
    public Rule createRule(Rule rule) {
        validateRuleExists(rule.getTableName(), rule.getDecoded());
        createComparisons(rule.getExpressions());
        createExpressions(rule.getExpressions());
        return ruleRepository.save(rule);
    }

    private void createComparisons(List<Expression> expressions) {
        for (Expression expression: expressions)
            comparisonRepository.save(expression.getComparison());
    }

    private void createExpressions(List<Expression> expressions) {
        expressionRepository.saveAll(expressions);
    }

    @Override
    public List<Rule> getRules() {
        return StreamSupport.stream(ruleRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    private void validateRuleExists(String tableName, String decoded) {
        if (ruleRepository.findByTableName(tableName).isPresent() && ruleRepository.findByDecoded(decoded).isPresent())
            throw new RuleException(HttpStatus.BAD_REQUEST, new RuleError(RuleErrorCode.CODE_07, RuleErrorCode.CODE_07.getMessage()));
    }
}