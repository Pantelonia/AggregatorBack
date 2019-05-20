package com.example.Agregattor.repository;

import com.example.Agregattor.entity.Diff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiffRepository extends CrudRepository<Diff, Integer> {
    List<Diff> findByCommitId(String commit_id);
}
