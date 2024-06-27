package net.upperland.project01.service;

import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.entity.ActivityEntity;
import net.upperland.project01.repositories.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityEntity saveActivity(ActivityEntity activity) {
        activity.setCreated_at(Timestamp.from(Instant.now()));
        return activityRepository.save(activity);
    }

    public ActivityEntity findActivityByName(String name) throws Exception {
        List<ActivityEntity> result = activityRepository.findByName(name);
        if (CollectionUtils.isEmpty(result)) {
            throw new Exception("Activity not found");
        }
        return result.get(0);
    }

    public ActivityEntity findActivityById(Integer id) throws Exception {
        Optional<ActivityEntity> result = activityRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Activity not found");
        }
        return result.get();
    }

    public ActivityEntity updateActivityById(Integer id, ActivityEntity activity) throws Exception {
        Optional<ActivityEntity> result = activityRepository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Activity not found");
        }
        activity.setUpdated_at(Timestamp.from(Instant.now()));
        return activityRepository.save(activity);
    }

    public ActivityEntity deleteActivityById(Integer id) throws Exception {
        Optional<ActivityEntity> entity = activityRepository.findById(id);
        if (entity.isEmpty()) {
            throw new Exception("Object not found for the given id.");
        }
        ActivityEntity activity = entity.get();
        activityRepository.delete(activity);
        return activity;
    }

}
