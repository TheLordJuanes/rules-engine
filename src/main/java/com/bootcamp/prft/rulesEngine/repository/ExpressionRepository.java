package com.bootcamp.prft.rulesEngine.repository;

import com.bootcamp.prft.rulesEngine.model.Expression;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ExpressionRepository extends CrudRepository<Expression, UUID> {
}