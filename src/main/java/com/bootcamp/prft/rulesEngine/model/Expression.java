package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Table(name = "expressions")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expression {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "expression_id")
    private UUID id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "comparison_id")
    private Comparison comparison;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}