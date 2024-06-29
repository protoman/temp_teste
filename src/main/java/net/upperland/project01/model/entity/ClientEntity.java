package net.upperland.project01.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import net.upperland.project01.model.enums.ClientGender;

import java.sql.Timestamp;

@Entity
@Table(name = "client")
@Data
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Timestamp created_at;

    private Timestamp updated_at;

    @Valid
    @NotBlank(message = "Name cannot be empty")
    private String name;

    private ClientGender gender;
}
