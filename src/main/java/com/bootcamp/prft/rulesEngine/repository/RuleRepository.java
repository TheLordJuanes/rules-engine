package com.bootcamp.prft.rulesEngine.repository;

import com.bootcamp.prft.rulesEngine.model.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuleRepository extends CrudRepository<Rule, UUID> {

    Optional<Rule> findByTableName(String tableName);
    Optional<Rule> findByDecoded(String decoded);
}