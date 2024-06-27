package net.upperland.project01.service;

import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.entity.ProjectEntity;
import net.upperland.project01.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectEntity saveProject(ProjectEntity project) {
        project.setCreated_at(Timestamp.from(Instant.now()));
        return projectRepository.save(project);
    }

    public ProjectEntity findProjectByName(String name) throws Exception {
        List<ProjectEntity> result = projectRepository.findByName(name);
        if (CollectionUtils.isEmpty(result)) {
            throw new Exception("Project not found");
        }
        return result.get(0);
    }

    public ProjectEntity findProjectById(Integer id) throws Exception {
        Optional<ProjectEntity> result = projectRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Project not found");
        }
        return result.get();
    }

    public ProjectEntity updateProjectById(Integer id, ProjectEntity project) throws Exception {
        Optional<ProjectEntity> result = projectRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Project not found");
        }
        project.setUpdated_at(Timestamp.from(Instant.now()));
        return projectRepository.save(project);
    }

    public ProjectEntity deleteProjectById(Integer id) throws Exception {
        Optional<ProjectEntity> entity = projectRepository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Object not found for the given id.");
        }
        ProjectEntity project = entity.get();
        projectRepository.delete(project);
        return project;
    }

}
