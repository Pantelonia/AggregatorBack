package com.example.Agregattor.Controllers;

import JavaAggregator.Aggregator;
import JavaAggregator.Project;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AcceptLink {

    @CrossOrigin
    @GetMapping(value = "/takeLink/", params = {"link", "crawler"})
    Integer takeLink(@RequestParam String link, @RequestParam String crawler) throws IOException {
        System.out.println(link);
        Aggregator aggregator = new Aggregator();
        int projectId = aggregator.getProjectId(link);
        if (crawler.equals("Java")) {

            aggregator.Aggregate(link);

        } else {
            System.out.println(crawler);
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("C:\\Users\\User\\Desktop\\SharpAggregator\\GitLabCrawler.exe 4896664 20");
        }

        MakeJson makeJson = new MakeJson();

        return 1;

    }
}
