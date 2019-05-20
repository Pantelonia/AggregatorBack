package com.example.Agregattor.entity;

import javax.persistence.*;

@Entity
@Table(name = "Diff")
public class Diff {
    private int id;
    private String oldPath;
    private String newPath;
    private boolean newFile;
    private boolean renamedFile;
    private boolean deletedFile;
    private String diff;
    private String commitId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "old_path")
    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    @Basic
    @Column(name = "new_path")
    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    @Basic
    @Column(name = "new_file")
    public boolean isNewFile() {
        return newFile;
    }

    public void setNewFile(boolean newFile) {
        this.newFile = newFile;
    }

    @Basic
    @Column(name = "renamed_file")
    public boolean isRenamedFile() {
        return renamedFile;
    }

    public void setRenamedFile(boolean renamedFile) {
        this.renamedFile = renamedFile;
    }

    @Basic
    @Column(name = "deleted_file")
    public boolean isDeletedFile() {
        return deletedFile;
    }

    public void setDeletedFile(boolean deletedFile) {
        this.deletedFile = deletedFile;
    }

    @Basic
    @Column(name = "diff")
    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    @Basic
    @Column(name = "commit_id")
    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diff that = (Diff) o;

        if (id != that.id) return false;
        if (newFile != that.newFile) return false;
        if (renamedFile != that.renamedFile) return false;
        if (deletedFile != that.deletedFile) return false;
        if (oldPath != null ? !oldPath.equals(that.oldPath) : that.oldPath != null) return false;
        if (newPath != null ? !newPath.equals(that.newPath) : that.newPath != null) return false;
        if (diff != null ? !diff.equals(that.diff) : that.diff != null) return false;
        if (commitId != null ? !commitId.equals(that.commitId) : that.commitId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (oldPath != null ? oldPath.hashCode() : 0);
        result = 31 * result + (newPath != null ? newPath.hashCode() : 0);
        result = 31 * result + (newFile ? 1 : 0);
        result = 31 * result + (renamedFile ? 1 : 0);
        result = 31 * result + (deletedFile ? 1 : 0);
        result = 31 * result + (diff != null ? diff.hashCode() : 0);
        result = 31 * result + (commitId != null ? commitId.hashCode() : 0);
        return result;
    }
}
