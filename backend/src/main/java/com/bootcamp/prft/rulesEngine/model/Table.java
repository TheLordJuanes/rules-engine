package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import java.util.UUID;

@Data
@javax.persistence.Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    @Id
    private UUID id;

    private String country;

    private String color;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}