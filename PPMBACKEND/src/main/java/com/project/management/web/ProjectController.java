package com.project.management.web;

import com.project.management.domain.Project;
import com.project.management.services.MapValidationErrorService;
import com.project.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/project")
public class ProjectController {

  @Autowired private ProjectService projectService;
  @Autowired private MapValidationErrorService mapValidationErrorService;

  @PostMapping(value = "")
  public ResponseEntity<?> createNewProject(
      @Valid @RequestBody Project project, BindingResult bindingResult) {

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
    if (errorMap != null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<>(project, HttpStatus.CREATED);
  }

  @GetMapping(value = "/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    Project project = projectService.findByProjectIdentifier(projectId);
    return new ResponseEntity<Project>(project, HttpStatus.OK);
  }

  @GetMapping(value = "/all")
  public Iterable<Project> getAllProjects() {
    return projectService.findAllProjects();
  }

  @DeleteMapping(value = "/{projectId}")
  public ResponseEntity<?> deleteProjectUsingProjectId(@PathVariable String projectId) {

    projectService.deleteProjectUsingIdentifier(projectId);

    return new ResponseEntity<String>(
        "Project with ID '" + projectId + "'is deleted", HttpStatus.OK);
  }
}
