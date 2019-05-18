package JavaAggregator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aggregator {
    private static String privateToken = "/?private_token=-ntY9o1t58hUeszxN_LG";


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

    public  void Aggregate(String args) throws IOException {
        connection = getConnection();
        BufferedReader br = null;
//        String link = "https://gitlab.com/siemens/gitlab-build-images";
//        String link = "https://gitlab.com/siemens/omnibus-gitlab";
        // TODO: send arg "link" to url
        URL url = new URL(args);
        int projectId = 1;
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Project ID")) {
                    projectId = Integer.parseInt(line.substring(12));
                    break;
                }
            }
        } catch (Exception e) {
        }


        //Getting project's info
        Gson g = new Gson();
        Project project = g.fromJson(getJson("https://gitlab.com/api/v4/projects/" + projectId + privateToken), Project.class);
        // ADD TO DATABASE!


        //Getting project's creator info
        User creator = g.fromJson(getJson("https://gitlab.com/api/v4/users/" + project.getCreator_id() + privateToken), User.class);
        try {
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement("INSERT INTO \"User\" (id, name, username, web_url, avatar_url) values (?,?,?,?,?)");
            stmt.setInt(1, creator.getId());
            stmt.setString(2, creator.getName());
            stmt.setString(3, creator.getUsername());
            stmt.setString(4, creator.getWeb_url());
            stmt.setString(5, creator.getAvatar_url());
            stmt.execute();
            stmt = connection.prepareStatement("INSERT INTO \"Project\" (id, name, description, user_id, created_at, web_url, avatar_url) values (?,?,?,?,?,?,?)");
            stmt.setInt(1, project.getId());
            stmt.setString(2, project.getName());
            stmt.setString(3, project.getDescription());
            stmt.setInt(4, project.getCreator_id());
            stmt.setString(5, project.getCreated_at());
            stmt.setString(6, project.getWeb_url());
            stmt.setString(7, project.getAvatar_url());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCommits("https://gitlab.com/api/v4/projects/" + projectId + "/repository/commits", projectId);
    }

    public static void getCommits(String link, int projectId) {

        System.out.println((link));

        //GETTING INFO ABOUT LAST 20 COMMITS OF THE PROJECT
        Gson g = new Gson();
        Commit[] coms = g.fromJson(getJson(link + privateToken), Commit[].class);


        //every commit
        for (Commit commit : coms) {
            System.out.println("\n\n");


            //GETTING AFFECTED BRANCHES
            String branches = "";
            AffectedBranch[] affectedBranches = g.fromJson(getJson(link + "/" + commit.getId() + "/refs" + privateToken), AffectedBranch[].class);
            for (AffectedBranch ab : affectedBranches) {
                if (ab.getType().equals("branch")) {
                    branches += (ab.getName()) + "\n";
                }
            }
            // ADD TO DATABASE!
            System.out.println(branches);
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO \"Commit\" (id, title, created_at, message, project_id, author_name, branches) values (?,?,?,?,?,?,?)");
                stmt.setString(1, commit.getId());
                stmt.setString(2, commit.getTitle());
                stmt.setString(3, commit.getCreated_at());
                stmt.setString(4, commit.getMessage());
                stmt.setInt(5, projectId);
                stmt.setString(6, commit.getAuthor_name());
                stmt.setString(7, branches);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Getting DIFF
            Diff[] diffs = g.fromJson(getJson(link + "/" + commit.getId() + "/diff"), Diff[].class);
            for (Diff diff : diffs) {
                try {
                    PreparedStatement stmt =  connection.prepareStatement("INSERT INTO \"Diff\" (old_path, new_path, new_file, renamed_file, deleted_file, diff, commit_id) values (?,?,?,?,?,?,?)");
                    stmt.setString(1, diff.getOld_path() );
                    stmt.setString(2, diff.getNew_path());
                    stmt.setBoolean(3, diff.isNew_file());
                    stmt.setBoolean(4, diff.isRenamed_file());
                    stmt.setBoolean(5, diff.isDeleted_file());
                    stmt.setString(6, diff.getDiff());
                    stmt.setString(7, commit.getId());
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static String getJson(String link) {
        URL url = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            url = new URL(link);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
