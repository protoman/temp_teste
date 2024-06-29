package net.upperland.project01.service;

import net.upperland.project01.model.entity.ActivityEntity;
import net.upperland.project01.repositories.ActivityRepository;
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


public class ActivityServiceTest {

    private ActivityRepository activityRepository = Mockito.mock(ActivityRepository.class);

    private ActivityService activityService;

    @BeforeEach
    public void setUp() {
        activityService = new ActivityService(activityRepository);
    }

    @Test
    public void saveActivity() {
        // given
        ActivityEntity activityDTO = ActivityEntity.builder().id(1).name("test").build();
        given(activityRepository.save(any())).willReturn(activityDTO);

        // when
        ActivityEntity result = activityService.saveActivity(activityDTO);

        // then
        then(result.getId()).isEqualTo(activityDTO.getId());
        then(result.getName()).isEqualTo(activityDTO.getName());
    }

    @Test
    public void findActivityByName() throws Exception {
        // given
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(1)
                .name(name)
                .build();
        given(activityRepository.findByName(name)).willReturn(List.of(expectedEntity));

        // when
        ActivityEntity result = activityService.findActivityByName(name);

        // then
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void findActivityByNameNotFound() throws Exception {
        // given
        String name = "test";
        given(activityRepository.findByName(name)).willReturn(new ArrayList<>());

        // when / then
        assertThrows(Exception.class, () -> activityService.findActivityByName(name));
    }

    @Test
    public void findActivityById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(activityRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ActivityEntity result = activityService.findById(id);

        // then
        then(result.getId()).isEqualTo(id);
    }

    @Test
    public void findActivityByIdNotFound() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        given(activityRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> activityService.findById(id));
    }

    @Test
    public void findAll() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(activityRepository.findAll()).willReturn(List.of(expectedEntity));

        // when
        List<ActivityEntity> result = activityService.findAll();

        // then
        then(result.size()).isGreaterThan(0);
        then(result.get(0).getId()).isEqualTo(id);
    }

    @Test
    public void findAllNotFound() throws Exception {
        // given
        given(activityRepository.findAll()).willReturn(new ArrayList<>());

        // when
        List<ActivityEntity> result = activityService.findAll();

        // then
        then(result.size()).isEqualTo(0);
    }

    @Test
    public void updateActivityById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(activityRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        given(activityRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ActivityEntity result = activityService.updateActivityById(id, expectedEntity);

        // then
        then(result.getId()).isEqualTo(expectedEntity.getId());
        then(result.getName()).isEqualTo(expectedEntity.getName());
    }

    @Test
    public void updateActivityByIdInvalidId() {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity input = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(activityRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // when / then
        assertThrows(Exception.class, () -> activityService.updateActivityById(2, input));
    }


    @Test
    public void updateActivityByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity input = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        when(activityRepository.save(any())).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return invocation.getArguments()[0];
            }
        });
        given(activityRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> activityService.updateActivityById(id, input));
    }

    @Test
    public void deleteActivityById() throws Exception {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(activityRepository.findById(id)).willReturn(java.util.Optional.ofNullable(expectedEntity));

        // when
        ActivityEntity result = activityService.deleteActivityById(id);

        // then
        then(result.getId()).isEqualTo(id);
        then(result.getName()).isEqualTo(name);
    }

    @Test
    public void deleteActivityByIdNotFound() {
        // given
        Integer id = 1;
        String name = "test";
        ActivityEntity expectedEntity = ActivityEntity.builder()
                .id(id)
                .name(name)
                .build();
        given(activityRepository.findById(id)).willReturn(java.util.Optional.empty());

        // when / then
        assertThrows(Exception.class, () -> activityService.deleteActivityById(id));
    }

}
