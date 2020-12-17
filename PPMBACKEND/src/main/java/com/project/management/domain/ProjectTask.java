package com.project.management.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false,unique = true)
  private String projectSequence;

  @NotBlank(message = "Please include a project summary")
  private String summary;

  private String acceptanceCriteria;
  private String status;
  private Integer priority;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date dueDate;
  // ManyToOne with Backlog
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  @Column(updatable = false)
  private String projectIdentifier;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date createdDate;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date updatedDate;

  public ProjectTask() {}

  @PrePersist
  protected void onCreate() {
    this.createdDate = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedDate = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getProjectSequence() {
    return projectSequence;
  }

  public void setProjectSequence(String projectSequence) {
    this.projectSequence = projectSequence;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(String acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  @Override
  public String toString() {
    return "ProjectTask{"
        + "id="
        + id
        + ", projectSequence='"
        + projectSequence
        + '\''
        + ", summary='"
        + summary
        + '\''
        + ", acceptanceCriteria='"
        + acceptanceCriteria
        + '\''
        + ", status='"
        + status
        + '\''
        + ", priority="
        + priority
        + ", dueDate="
        + dueDate
        + ", projectIdentifier='"
        + projectIdentifier
        + '\''
        + ", createdDate="
        + createdDate
        + ", updatedDate="
        + updatedDate
        + '}';
  }
}
