package net.upperland.project01.repositories;

import net.upperland.project01.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Integer>  {
    public List<ActivityEntity> findByName(String name);
}
