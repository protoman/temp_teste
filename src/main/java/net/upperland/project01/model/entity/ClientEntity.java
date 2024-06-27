package net.upperland.project01.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import net.upperland.project01.model.enums.ClientGender;

import java.sql.Timestamp;

@Entity
@Table(name = "client")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Timestamp created_at;

    private Timestamp updated_at;

    private String name;

    private ClientGender gender;
}
