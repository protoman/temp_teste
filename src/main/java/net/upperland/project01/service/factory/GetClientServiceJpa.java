package net.upperland.project01.service.factory;

import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.model.mapper.ClientDtoEntityMapper;
import net.upperland.project01.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetClientServiceJpa implements GetClientInterface {
    private final ClientRepository clientRepository;
    private final ClientDtoEntityMapper clientDtoEntityMapper;

    @Override
    public ClientDTO findClientById(Integer id) throws Exception {
        Optional<ClientEntity> result = clientRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Object not found for the given id.");
        }
        return clientDtoEntityMapper.EntityToDTO(result.get());
    }


}
