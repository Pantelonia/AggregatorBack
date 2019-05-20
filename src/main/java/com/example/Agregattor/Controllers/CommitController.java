package com.example.Agregattor.Controllers;

import com.example.Agregattor.entity.Commit;
import com.example.Agregattor.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommitController {
    @Autowired
    CommitRepository commitRepository;
    @GetMapping("/commit/{id}")
    Commit getCommit(@PathVariable String id){
        return  commitRepository.findById(id).get() ;
    }
}
