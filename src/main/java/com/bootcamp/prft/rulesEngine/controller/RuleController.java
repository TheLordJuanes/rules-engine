package com.bootcamp.prft.rulesEngine.controller;

import com.bootcamp.prft.rulesEngine.api.RuleAPI;
import com.bootcamp.prft.rulesEngine.dto.RuleDTO;
import com.bootcamp.prft.rulesEngine.mapper.RuleMapper;
import com.bootcamp.prft.rulesEngine.service.RuleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RuleController implements RuleAPI {

    private final RuleService ruleService;
    private final RuleMapper ruleMapper;

    @Override
    public RuleDTO getRule(UUID ruleId) {
        return ruleMapper.fromRule(ruleService.getRule(ruleId));
    }

    @Override
    public RuleDTO createRule(RuleDTO ruleDTO) {
        return ruleMapper.fromRule(ruleService.createRule(ruleMapper.fromDTO(ruleDTO)));    }

    @Override
    public List<RuleDTO> getRules() {
        return ruleService.getRules().stream().map(ruleMapper::fromRule).collect(Collectors.toList());
    }
}