package com.bootcamp.prft.rulesEngine.service;

import com.bootcamp.prft.rulesEngine.model.Rule;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.UUID;

public interface RuleService {

    Rule getRule(@PathVariable UUID ruleId);
    Rule createRule(@RequestBody Rule rule);
    List<Rule> getRules();
}