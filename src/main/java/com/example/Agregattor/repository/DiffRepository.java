package com.example.Agregattor.repository;

import com.example.Agregattor.entity.DiffEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiffRepository extends CrudRepository<DiffEntity, Integer> {
    List<DiffEntity> findByCommitId(String commit_id);
}
