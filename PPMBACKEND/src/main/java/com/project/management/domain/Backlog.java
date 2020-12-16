package com.project.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer PTSequence = 0;
  private String projectIdentifier;

  // OneToOne with Projects
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "project_id", nullable = false)
  @JsonIgnore
  private Project project;

  // OneToMany projectTasks
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
  private List<ProjectTask> projectTasks = new ArrayList<>();

  public Project getProject() {
    return project;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPTSequence() {
    return PTSequence;
  }

  public void setPTSequence(Integer PTSequence) {
    this.PTSequence = PTSequence;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public List<ProjectTask> getProjectTasks() {
    return projectTasks;
  }

  public void setProjectTasks(List<ProjectTask> projectTasks) {
    this.projectTasks = projectTasks;
  }

}
