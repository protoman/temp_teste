package net.upperland.project01.repositories;


import net.upperland.project01.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// TODO: change to hibernate
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    public List<ClientEntity> findByName(String name);

}
