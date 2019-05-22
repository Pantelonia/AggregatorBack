package com.example.Agregattor.Controllers;

import JavaAggregator.Aggregator;
import JavaAggregator.Project;
import com.example.Agregattor.entity.Commit;
import com.example.Agregattor.repository.CommitRepository;
import com.example.Agregattor.repository.DiffRepository;
import com.example.Agregattor.repository.ProjectRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class AcceptLink {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommitRepository commitRepository;
    @Autowired
    DiffRepository diffRepository;

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection("jdbc:postgresql://192.168.10.99:5432/studs", "s242274", "bld868");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:9999/studs", "s242274", "bld868");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    @CrossOrigin
    @GetMapping(value = "/takeLink/", params = {"link", "crawler"})
    Project takeLink(@RequestParam String link, @RequestParam String crawler) throws IOException, InterruptedException {
        connection = getConnection();

        try {
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("delete from \"diff\"");
            stmt.execute();

            stmt = connection.prepareStatement("delete from \"commit\"");
            stmt.execute();

            stmt = connection.prepareStatement("delete from \"project\"");
            stmt.execute();

            stmt = connection.prepareStatement("delete from \"user\"");

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(link);
        Aggregator aggregator = new Aggregator();
        Integer projectId = aggregator.getProjectId(link);
        System.out.println(crawler);

        if (crawler.equals("Java")) {

            aggregator.Aggregate(link);

        } else {
            System.out.println(crawler);
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("C:\\Users\\User\\Desktop\\SharpAggregator\\GitLabCrawler.exe " + projectId + " 20");
            p.waitFor();
//            try {
//                Thread.sleep(17000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

//        System.out.println(projectId);
//        MakeJson makeJson = new MakeJson();
//        Project data =  makeJson.getProject(projectId);
        com.example.Agregattor.entity.Project project = projectRepository.findById(projectId).get();
        System.out.println(project.getName());
        JavaAggregator.Project project1 = new JavaAggregator.Project(project);
        Iterable<Commit> commits = commitRepository.findAllByProjectId(projectId);
        ArrayList<JavaAggregator.Commit> commitArrayList = new ArrayList<JavaAggregator.Commit>();
        commits.forEach(commit -> {
            JavaAggregator.Commit commit1 = new JavaAggregator.Commit(commit);
            commit1.setDiffs(diffRepository.findByCommitId(commit1.getId()));
            commitArrayList.add(commit1);
        });
        project1.setCommits(commitArrayList);

        return project1;

    }
}
