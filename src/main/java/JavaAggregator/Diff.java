package JavaAggregator;

public class Diff {
    private String old_path;
    private String new_path;
    private boolean new_file;
    private boolean renamed_file;
    private boolean deleted_file;
    private String diff;

    public String getOld_path() {
        return old_path;
    }

    public void setOld_path(String old_path) {
        this.old_path = old_path;
    }

    public String getNew_path() {
        return new_path;
    }

    public void setNew_path(String new_path) {
        this.new_path = new_path;
    }

    public boolean isNew_file() {
        return new_file;
    }

    public void setNew_file(boolean new_file) {
        this.new_file = new_file;
    }

    public boolean isRenamed_file() {
        return renamed_file;
    }

    public void setRenamed_file(boolean renamed_file) {
        this.renamed_file = renamed_file;
    }

    public boolean isDeleted_file() {
        return deleted_file;
    }

    public void setDeleted_file(boolean deleted_file) {
        this.deleted_file = deleted_file;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }
}
