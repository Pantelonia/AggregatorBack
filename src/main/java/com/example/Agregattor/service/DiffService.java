package com.example.Agregattor.service;

import com.example.Agregattor.entity.Diff;
import com.example.Agregattor.repository.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiffService {
    @Autowired
    private DiffRepository diffRepository;

    public Iterable<Diff> getAllDiff(String commit_id){
        return diffRepository.findByCommitId(commit_id);
    }

    public Diff getDiff(Integer id){
        return diffRepository.findById(id).get();
    }
}
