package com.example.Agregattor.Controllers;



import com.example.Agregattor.entity.Commit;
import com.example.Agregattor.entity.Project;
import com.example.Agregattor.repository.CommitRepository;
import com.example.Agregattor.repository.DiffRepository;
import com.example.Agregattor.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class MakeJson {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CommitRepository commitRepository;
    @Autowired
    DiffRepository diffRepository;

    @CrossOrigin
    @GetMapping("/project/{id}")
    public JavaAggregator.Project getProject(@PathVariable Integer id) {
//        CommitsDiffs commitsDiffs = new CommitsDiffs();
//        ArrayList<CommitsDiffs> commitsDiffsArrayList =new ArrayList<CommitsDiffs>();
//
//        Project project = projectRepository.findById(id).get();
//
//        finalData.setProject(project);
//
//        Iterable<Commit> commits = commitRepository.findAllByProjectId(project.getId());
//        commits.forEach(commit -> {
//            Iterable<Diff> diffs = diffRepository.findByCommitId(commit.getId());
//            commitsDiffs.setCommit(commit);
//            commitsDiffs.setDiffs(diffs);
//            commitsDiffsArrayList.add(commitsDiffs);
//        });
//        finalData.setCommits(commitsDiffsArrayList);

        System.out.println(id);
      Project project = projectRepository.findById(id).get();
      System.out.println(project.getName());
        JavaAggregator.Project project1 = new JavaAggregator.Project(project);
        Iterable<Commit> commits = commitRepository.findAllByProjectId(id);
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
