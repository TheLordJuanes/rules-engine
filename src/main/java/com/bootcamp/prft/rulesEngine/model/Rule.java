package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "rules")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "rule_id")
    private UUID id;

    @Column(name = "table_name")
    private String tableName;

    private String encoded;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "rule_expression",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "expression_id"))
    private List<Expression> expressions;

    private String decoded;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}