package net.upperland.project01.service;

import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.model.mapper.ClientDtoEntityMapper;
import net.upperland.project01.repositories.ClientRepository;
import net.upperland.project01.service.factory.GetClientServiceJpa;
import net.upperland.project01.service.factory.GetClientServiceRest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This shows how we can use factory to have different implementations for data-fetch. One can use a database and other a rest client to another microservice.
// Also, this converts the entity to a DTO as a good practice that allows you to expose only the data you want to expose in the rest results json.
// Other services, due to this test's time constraints, will be simplified and use a more direct approach.

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDtoEntityMapper clientDtoEntityMapper;
    private final GetClientServiceRest getClientRest;
    private final GetClientServiceJpa getClientJpa;

    public ClientEntity saveClient(ClientDTO clientDTO) {
        ClientEntity entity = clientDtoEntityMapper.DTOToEntity(clientDTO);
        entity.setCreated_at(Timestamp.from(Instant.now()));
        return clientRepository.save(entity);
    }

    public ClientDTO findClientByName(String name) {
        List<ClientEntity> result = clientRepository.findByName(name);
        // TODO - handle errors like empty list
        return clientDtoEntityMapper.EntityToDTO(result.get(0));
    }

    public ClientDTO findClientById(Integer id) throws Exception {
        return getClientJpa.findClientById(id);
    }

    public ClientEntity updateClientById(Integer id, ClientDTO clientDTO) throws Exception {
        if (!Objects.equals(id, clientDTO.getId())) {
            throw new Exception("Invalid id does not match the value from body.");
        }
        // TODO: ensure we use the correct id, and not empty
        ClientEntity entity = clientDtoEntityMapper.DTOToEntity(clientDTO);
        entity.setUpdated_at(Timestamp.from(Instant.now()));
        return clientRepository.save(entity);
    }

    public ClientEntity deleteClientById(Integer id) throws Exception {
        Optional<ClientEntity> entity = clientRepository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Object not found for the given id.");
        }
        ClientEntity client = entity.get();
        clientRepository.delete(client);
        return client;
    }

    public ClientDTO findRemoteClientByName(Integer id) throws Exception {
        return getClientRest.findClientById(id);
    }


}
