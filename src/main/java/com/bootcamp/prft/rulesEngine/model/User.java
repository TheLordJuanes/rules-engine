package com.bootcamp.prft.rulesEngine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Table(name = "users")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private String email;

    @NotNull
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}