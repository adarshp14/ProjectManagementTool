package com.project.management.web;

import com.project.management.domain.Project;
import com.project.management.services.MapValidationErrorService;
import com.project.management.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/project")
public class ProjectController {

  @Autowired private ProjectService projectService;
  @Autowired private MapValidationErrorService mapValidationErrorService;

  @PostMapping(value = "")
  public ResponseEntity<?> createNewProject(
      @Valid @RequestBody Project project, BindingResult bindingResult) {

    ResponseEntity<?> errorMap=mapValidationErrorService.MapValidationService(bindingResult);
    if(errorMap!=null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);

    return new ResponseEntity<>(project, HttpStatus.CREATED);
  }
}
