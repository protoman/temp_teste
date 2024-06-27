package net.upperland.project01.model.mapper;

import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientDtoEntityMapper {

    @Mapping(target = "id", source = "client.id")
    @Mapping(target = "created_at", source = "client.created_at")
    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "gender", source = "client.gender")
    ClientEntity DTOToEntity(ClientDTO client);

    @Mapping(target = "id", source = "client.id")
    @Mapping(target = "created_at", source = "client.created_at")
    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "gender", source = "client.gender")
    ClientDTO EntityToDTO(ClientEntity client);
}
