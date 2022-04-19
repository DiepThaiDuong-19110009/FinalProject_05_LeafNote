package hcmute.edu.vn.leafnote.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity(foreignKeys =
@ForeignKey(entity = Users.class,parentColumns = "id",childColumns = "user_id"))
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="user_id")
    int userId;
    String title;
    String content;
    String created_at;
    String updated_at;
    boolean checked;

    public Note() {
    }

    public Note(int userId, String title, String content, String created_at, String updated_at, boolean checked) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.checked = checked;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", checked=" + checked +
                '}';
    }
}

