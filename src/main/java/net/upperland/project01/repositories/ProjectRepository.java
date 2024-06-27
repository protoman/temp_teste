package net.upperland.project01.repositories;

import net.upperland.project01.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>  {
    public List<ProjectEntity> findByName(String name);
}
