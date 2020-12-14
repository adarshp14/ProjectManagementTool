package com.project.management.services;

import com.project.management.domain.Project;
import com.project.management.exceptions.ProjectIdException;
import com.project.management.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

  public Project saveOrUpdateProject(Project project) {
 try
 {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
     return projectRepository.save(project);
   }
 catch (Exception e)
     {
         throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
     }

  }
}