package net.upperland.project01.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "project")
@Data
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Timestamp created_at;

    private Timestamp updated_at;

    private String name;

}
