package net.upperland.project01.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import net.upperland.project01.model.enums.ClientGender;

import java.sql.Timestamp;

@Data
public class ClientDTO {

    private Integer id;

    private Timestamp created_at;

    private Timestamp updated_at;

    @Valid
    @NotBlank(message = "Name cannot be empty") // notEmpty is for collections
    private String name;

    private ClientGender gender;
}
