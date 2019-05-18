package com.example.Agregattor.Controllers;

import JavaAggregator.Aggregator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AcceptLink {

    @CrossOrigin
    @GetMapping(value = "/takeLink/", params = {"link"})
    String takeLink(@RequestParam String link) throws IOException {
        System.out.println(link);
        Aggregator aggregator = new Aggregator();
        aggregator.Aggregate(link);


//        Process process = new ProcessBuilder("C:\\Users\\User\\AppData\\Roaming\\uTorrent\\uTorrent.exe"
////                ,"param1"
//        ).start();

        return  "back";
    }
}
