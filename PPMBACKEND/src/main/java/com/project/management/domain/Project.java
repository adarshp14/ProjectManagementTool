package com.project.management.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @NotBlank(message = "Project name cannot be blank")
  private String projectName;


  @Size(min = 4, max = 5, message = "Please use between 4 to 5 characters")
  @Column(updatable = false, unique = true)
  @NotBlank(message = "Project identifier cannot be blank")
  private String projectIdentifier;

  @NotBlank(message = "Project description cannot be blank")
  private String description;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date startDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date endDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  @Column(updatable = false)
  private Date createDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date updateDate;

  @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "project")
  private Backlog backlog;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  public Project() {}

  @PrePersist
  protected void onCreate() {
    this.createDate = new Date();
  }

  @PostPersist
  protected void onUpdate() {
    this.updateDate = new Date();
  }
}
