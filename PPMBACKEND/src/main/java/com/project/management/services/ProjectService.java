package com.project.management.services;

import com.project.management.domain.Project;
import com.project.management.exceptions.ProjectIdException;
import com.project.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException(
          "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
    }
  }

  public Project findByProjectIdentifier(String projectIdentifier) {
    Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    if (project == null) {
      throw new ProjectIdException("Project Id '" + projectIdentifier + "' does not exist");
    }
    return projectRepository.findByProjectIdentifier(projectIdentifier);
  }

  public Iterable<Project> findAllProjects()
  {
      return projectRepository.findAll();
  }

  public void deleteProjectUsingIdentifier(String projectIdentifier)
  {
    Project project=projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
    if(project==null)
    {
      throw new ProjectIdException("Project Id '"+projectIdentifier+"' does not exist to be deleted");
    }
    projectRepository.delete(project);
  }

  
}
