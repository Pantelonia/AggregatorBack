package com.example.Agregattor.repository;

import com.example.Agregattor.entity.Commit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommitRepository extends CrudRepository<Commit, String> {
    List<Commit> findAllByProjectId (Integer project_id);
}
