package JavaAggregator;

import java.util.List;

public class Project {
    private int id;
    private String description;
    private String name;
    private String created_at;
    private String web_url;
    private String avatar_url;
    private int creator_id;
    private Iterable<Commit> commits;

    public Project(com.example.Agregattor.entity.Project project){
        this.id = project.getId();
        this.description = project.getDescription();
        this.name = project.getName();
        this.created_at = project.getCreatedAt();
        this.web_url = project.getWebUrl();
        this.avatar_url = project.getAvatarUrl();
        this.creator_id = project.getUserId();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public Iterable<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Iterable<Commit> commits) {
        this.commits = commits;
    }
}
