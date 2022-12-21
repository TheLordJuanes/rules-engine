package com.bootcamp.prft.rulesEngine.api;

import com.bootcamp.prft.rulesEngine.dto.RuleDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/rules")
public interface RuleAPI {

    @GetMapping("/{ruleId}")
    RuleDTO getRule(@PathVariable() UUID ruleId);

    @PostMapping()
    RuleDTO createRule(@RequestBody RuleDTO ruleDTO) throws Exception;

    @GetMapping
    List<RuleDTO> getRules();
}