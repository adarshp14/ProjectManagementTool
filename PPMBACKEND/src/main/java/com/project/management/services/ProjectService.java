package com.project.management.services;

import com.project.management.domain.Backlog;
import com.project.management.domain.Project;
import com.project.management.exceptions.ProjectIdException;
import com.project.management.repositories.BacklogRepository;
import com.project.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired private ProjectRepository projectRepository;

  @Autowired private BacklogRepository backlogRepository;

  public Project saveOrUpdateProject(Project project) {
    String projectIdentifier = project.getProjectIdentifier().toUpperCase();
    try {
      project.setProjectIdentifier(projectIdentifier);
      if(project.getId()==null)
      {
        Backlog backlog=new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(projectIdentifier);
      }
      if(project.getId()!=null)
      {
        project.setBacklog(
            backlogRepository.findByProjectIdentifier(projectIdentifier));
      }
      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException(
          "Project ID '" + projectIdentifier + "' already exists");
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

  public Project updateProject(Project updatedProject){

    updatedProject.setProjectIdentifier(updatedProject.getProjectIdentifier().toUpperCase());

    Project oldProject = projectRepository.findByProjectIdentifier(updatedProject.getProjectIdentifier());
    if(oldProject == null){
      throw new ProjectIdException(String.format("Cannot update project as Project ID: %s does not exist", updatedProject.getProjectIdentifier()));
    }

    updatedProject.setId(oldProject.getId());
    return projectRepository.save(updatedProject);

  }

  public Project findProjectByIdentifier(String projectId, String username){

    //Only want to return the project if the user looking for it is the owner

    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

    if(project == null){
      throw new ProjectIdException("Project ID '"+projectId+"' does not exist");

    }



    return project;
  }

  
}
