package com.example.Agregattor.Controllers;


import com.example.Agregattor.entity.DiffEntity;
import com.example.Agregattor.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiffController {
    @Autowired
    DiffService service;
    @GetMapping("/diff/commit/{commit_id}")
    Iterable<DiffEntity> getByCommitId(@PathVariable String commit_id){
        System.out.println("ckeck");
        return service.getAllDiff(commit_id);
    }
    @GetMapping("/diff/{id}")
    DiffEntity getDiff(@PathVariable Integer id){
        return service.getDiff(id);
    }



}
