package com.example.Agregattor.Controllers;

import JavaAggregator.Aggregator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AcceptLink {

    @CrossOrigin
    @GetMapping(value = "/takeLink/", params = {"link"})
    Integer takeLink(@RequestParam String link) throws IOException {
        System.out.println(link);
        Aggregator aggregator = new Aggregator();
        System.out.println(aggregator.getProjectId(link));
    //        aggregator.Aggregate(link);




        return  222;

    }
}
