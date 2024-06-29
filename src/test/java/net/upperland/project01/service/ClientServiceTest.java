package net.upperland.project01.service;

import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.model.enums.ClientGender;
import net.upperland.project01.model.mapper.ClientDtoEntityMapper;
import net.upperland.project01.repositories.ClientRepository;
import net.upperland.project01.service.factory.GetClientServiceJpa;
import net.upperland.project01.service.factory.GetClientServiceRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class ClientServiceTest {
    private ClientRepository clientRepository = Mockito.mock(ClientRepository.class);

    private ClientDtoEntityMapper clientDtoEntityMapper = Mappers.getMapper(ClientDtoEntityMapper.class);

    @Mock
    private GetClientServiceRest getClientRest;

    private GetClientServiceJpa getClientJpa;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        getClientJpa = new GetClientServiceJpa(clientRepository, clientDtoEntityMapper);
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
        // TODO: test created date

    }

    @Test
    public void findClientByName() throws Exception {
        // given
        String name = "test";
        ClientEntity expectedEntity = ClientEntity.builder()
                .id(1)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        given(clientRepository.findByName(name)).willReturn(List.of(expectedEntity));

        // when
        ClientDTO result = clientService.findClientByName(name);

        // then
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void findClientByNameNotFound() throws Exception {
        // given
        String name = "test";
        given(clientRepository.findByName(name)).willReturn(new ArrayList<>());

        // when / then
        assertThrows(Exception.class, () -> clientService.findClientByName(name));
    }

    @Test
    public void findClientById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ClientEntity expectedEntity = ClientEntity.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        given(clientRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ClientDTO result = clientService.findClientById(id);

        // then
        then(result.getId()).isEqualTo(id);
    }

    @Test
    public void findClientByIdNotFound() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        given(clientRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> clientService.findClientById(id));
    }

    @Test
    public void findAll() {
        // given
        Integer id = 1;
        String name = "test";
        ClientEntity expectedEntity = ClientEntity.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        given(clientRepository.findAll()).willReturn(List.of(expectedEntity));

        // when
        List<ClientEntity> result = clientService.findAll();

        // then
        then(result.size()).isGreaterThan(0);
        then(result.get(0).getId()).isEqualTo(id);
    }

    @Test
    public void findAllNotFound() {
        // given
        given(clientRepository.findAll()).willReturn(new ArrayList<>());

        // when
        List<ClientEntity> result = clientService.findAll();

        // then
        then(result.size()).isEqualTo(0);
    }

    @Test
    public void updateClientById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ClientDTO input = ClientDTO.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        when(clientRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        ClientEntity expectedEntity = clientDtoEntityMapper.DTOToEntity(input);
        given(clientRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ClientEntity result = clientService.updateClientById(id, input);

        // then
        then(result.getId()).isEqualTo(input.getId());
        then(result.getName()).isEqualTo(input.getName());
    }

    @Test
    public void updateClientByIdInvalidId() {
        // given
        Integer id = 1;
        String name = "test";
        ClientDTO input = ClientDTO.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        when(clientRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // when / then
        assertThrows(Exception.class, () -> clientService.updateClientById(2, input));
    }


    @Test
    public void updateClientByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ClientDTO input = ClientDTO.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        when(clientRepository.save(any())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArguments()[0];
            }
        });
        given(clientRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> clientService.updateClientById(id, input));
    }

    @Test
    public void deleteClientById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ClientEntity expectedEntity = ClientEntity.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        given(clientRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ClientEntity result = clientService.deleteClientById(id);

        // then
        then(result.getId()).isEqualTo(id);
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void deleteClientByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ClientEntity expectedEntity = ClientEntity.builder()
                .id(id)
                .name(name)
                .gender(ClientGender.FEMALE)
                .build();
        given(clientRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> clientService.deleteClientById(id));
    }

}
