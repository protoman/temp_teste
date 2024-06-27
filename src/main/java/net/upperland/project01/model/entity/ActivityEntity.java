package net.upperland.project01.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "activity")
@Data
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Timestamp created_at;

    private Timestamp updated_at;

    @Valid
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ClientEntity project;
}
