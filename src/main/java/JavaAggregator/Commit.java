package JavaAggregator;

import com.example.Agregattor.entity.Diff;

import java.util.ArrayList;
import java.util.List;

public class Commit {
    private String id;
    private String title;
    private String created_at;
    private String message;
    private String author_name;
    private Iterable<Diff> diffs;
   public Commit(com.example.Agregattor.entity.Commit commit){
        this.id = commit.getId();
        this.title = commit.getTitle();
        this.created_at = commit.getCreatedAt();
        this.message = commit.getMessage();
        this.author_name = commit.getAuthorName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Iterable<Diff> getDiffs() {
        return diffs;
    }

    public void setDiffs(Iterable<Diff> diffs) {
        this.diffs = diffs;
    }
}


