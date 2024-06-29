package net.upperland.project01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ActivityEntity;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.model.entity.ProjectEntity;
import net.upperland.project01.service.ActivityService;
import net.upperland.project01.service.ClientService;
import net.upperland.project01.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/practice")
@RequiredArgsConstructor
public class PracticeController {

    private final ClientService clientService; // for auto-constructors, needs final
    private final ProjectService projectService;
    private final ActivityService activityService;

    private static final Logger logger = LoggerFactory.getLogger(PracticeController.class);

    // ============= CLIENT ============= //

    @GetMapping("client/name/{name}")
    public ResponseEntity<?> findClientByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok().body(clientService.findClientByName(name));
    }

    @GetMapping("client/id/{id}")
    public ResponseEntity<?> findClientById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(clientService.findClientById(id));
    }

    @GetMapping("client")
    public ResponseEntity<?> findAllClient() throws Exception {
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @PostMapping("client")
    // TODO: handle validation error and show message
    public ResponseEntity<ClientEntity> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(clientService.saveClient(clientDTO));
    }

    @PutMapping("client/{id}")
    // TODO: handle validation error and show message
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) throws Exception {
        return ResponseEntity.ok().body(clientService.updateClientById(id, clientDTO));
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<ClientEntity> deleteClientById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(clientService.deleteClientById(id));
    }

    @GetMapping("remote-client/{id}")
    public ResponseEntity<?> findRemoteClient(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(clientService.findRemoteClientByName(id));
    }

    // ============= PROJECT ============= //

    @GetMapping("project/name/{name}")
    public ResponseEntity<?> findProjectByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok().body(projectService.findProjectByName(name));
    }

    @GetMapping("project/id/{name}")
    public ResponseEntity<?> findProjectById(@PathVariable Integer name) throws Exception {
        return ResponseEntity.ok().body(projectService.findProjectById(name));
    }

    @GetMapping("project")
    public ResponseEntity<?> findAllProject() throws Exception {
        return ResponseEntity.ok().body(projectService.findAll());
    }

    @PostMapping("project")
    public ResponseEntity<ProjectEntity> addProject(@Valid @RequestBody ProjectEntity project) {
        return ResponseEntity.ok().body(projectService.saveProject(project));
    }

    @PutMapping("project/{id}")
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectEntity project) throws Exception {
        return ResponseEntity.ok().body(projectService.updateProjectById(id, project));
    }

    @DeleteMapping("project/{id}")
    public ResponseEntity<ProjectEntity> deleteProjectById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(projectService.deleteProjectById(id));
    }

    // ============= ACTIVITY ============= //

    @GetMapping("activity/{name}")
    public ResponseEntity<?> findActivityByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok().body(activityService.findActivityByName(name));
    }

    @GetMapping("activity/{id}")
    public ResponseEntity<?> findActivityById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(activityService.findById(id));
    }

    @GetMapping("activity")
    public ResponseEntity<?> findAllActivity() throws Exception {
        return ResponseEntity.ok().body(activityService.findAll());
    }


    @PostMapping("activity")
    public ResponseEntity<ActivityEntity> addActivity(@Valid @RequestBody ActivityEntity activity) {
        return ResponseEntity.ok().body(activityService.saveActivity(activity));
    }

    @PutMapping("activity/{id}")
    public ResponseEntity<ActivityEntity> updateActivity(@PathVariable Integer id, @Valid @RequestBody ActivityEntity activity) throws Exception {
        return ResponseEntity.ok().body(activityService.updateActivityById(id, activity));
    }

    @DeleteMapping("activity/{id}")
    public ResponseEntity<ActivityEntity> deleteActivityById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(activityService.deleteActivityById(id));
    }



}
