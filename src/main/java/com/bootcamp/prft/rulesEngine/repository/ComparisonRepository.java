package com.bootcamp.prft.rulesEngine.repository;

import com.bootcamp.prft.rulesEngine.model.Comparison;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ComparisonRepository extends CrudRepository<Comparison, UUID> {
}