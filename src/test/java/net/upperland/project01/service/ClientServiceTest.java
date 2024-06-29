package net.upperland.project01.service;

import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.model.mapper.ClientDtoEntityMapper;
import net.upperland.project01.repositories.ClientRepository;
import net.upperland.project01.service.factory.GetClientServiceJpa;
import net.upperland.project01.service.factory.GetClientServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class ClientServiceTest {
    private ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

    private ClientDtoEntityMapper clientDtoEntityMapper = Mappers.getMapper(ClientDtoEntityMapper.class);

    @Mock
    private GetClientServiceRest getClientRest;

    @Mock
    private GetClientServiceJpa getClientJpa;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientService(clientRepository, clientDtoEntityMapper, getClientRest, getClientJpa);
    }

    @Test
    public void saveClient() {
        // given
        ClientDTO clientDTO = ClientDTO.builder().id(1).name("test").build();
        ClientEntity expectedEntity = clientDtoEntityMapper.DTOToEntity(clientDTO);
        given(clientRepository.save(any())).willReturn(expectedEntity);

        // when
        ClientEntity result = clientService.saveClient(clientDTO);

        // then
        then(result.getId()).isEqualTo(clientDTO.getId());
        then(result.getName()).isEqualTo(clientDTO.getName());
        then(result.getGender()).isEqualTo(clientDTO.getGender());
    }
}
