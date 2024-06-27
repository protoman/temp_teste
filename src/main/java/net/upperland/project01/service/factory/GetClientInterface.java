package net.upperland.project01.service.factory;

import net.upperland.project01.model.dto.ClientDTO;

public interface GetClientInterface {
    public ClientDTO findClientById(Integer id) throws Exception;
}
