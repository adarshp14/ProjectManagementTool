package com.project.management.repositories;

import com.project.management.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {

  Backlog findByProjectIdentifier(String projectIdentifier);
}
