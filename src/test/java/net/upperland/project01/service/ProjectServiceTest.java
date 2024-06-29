package net.upperland.project01.service;

import net.upperland.project01.model.entity.ProjectEntity;
import net.upperland.project01.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

public class ProjectServiceTest {
    private ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);

    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        projectService = new ProjectService(projectRepository);
    }

    @Test
    public void saveProject() {
        // given
        ProjectEntity projectDTO = ProjectEntity.builder().id(1).name("test").build();
        given(projectRepository.save(any())).willReturn(projectDTO);

        // when
        ProjectEntity result = projectService.saveProject(projectDTO);

        // then
        then(result.getId()).isEqualTo(projectDTO.getId());
        then(result.getName()).isEqualTo(projectDTO.getName());
    }

    @Test
    public void findProjectByName() throws Exception {
        // given
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(1)
                .name(name)
                .build();
        given(projectRepository.findByName(name)).willReturn(List.of(expectedEntity));

        // when
        ProjectEntity result = projectService.findProjectByName(name);

        // then
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void findProjectByNameNotFound() throws Exception {
        // given
        String name = "test";
        given(projectRepository.findByName(name)).willReturn(new ArrayList<>());

        // when / then
        assertThrows(Exception.class, () -> projectService.findProjectByName(name));
    }

    @Test
    public void findProjectById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(projectRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ProjectEntity result = projectService.findProjectById(id);

        // then
        then(result.getId()).isEqualTo(id);
    }

    @Test
    public void findProjectByIdNotFound() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        given(projectRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> projectService.findProjectById(id));
    }

    @Test
    public void findAll() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(projectRepository.findAll()).willReturn(List.of(expectedEntity));

        // when
        List<ProjectEntity> result = projectService.findAll();

        // then
        then(result.size()).isGreaterThan(0);
        then(result.get(0).getId()).isEqualTo(id);
    }

    @Test
    public void findAllNotFound() throws Exception {
        // given
        given(projectRepository.findAll()).willReturn(new ArrayList<>());

        // when
        List<ProjectEntity> result = projectService.findAll();

        // then
        then(result.size()).isEqualTo(0);
    }

    @Test
    public void updateProjectById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(projectRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        given(projectRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ProjectEntity result = projectService.updateProjectById(id, expectedEntity);

        // then
        then(result.getId()).isEqualTo(expectedEntity.getId());
        then(result.getName()).isEqualTo(expectedEntity.getName());
    }

    @Test
    public void updateProjectByIdInvalidId() {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity input = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(projectRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // when / then
        assertThrows(Exception.class, () -> projectService.updateProjectById(2, input));
    }


    @Test
    public void updateProjectByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity input = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(projectRepository.save(any())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArguments()[0];
            }
        });
        given(projectRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> projectService.updateProjectById(id, input));
    }

    @Test
    public void deleteProjectById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(projectRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ProjectEntity result = projectService.deleteProjectById(id);

        // then
        then(result.getId()).isEqualTo(id);
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void deleteProjectByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ProjectEntity expectedEntity = ProjectEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(projectRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> projectService.deleteProjectById(id));
    }

}
