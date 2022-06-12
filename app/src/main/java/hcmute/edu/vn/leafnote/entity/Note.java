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

@Entity(foreignKeys = {
        @ForeignKey(entity = Users.class, parentColumns = "id", childColumns = "user_id")}
)// foreign key
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="user_id")
    int userId;
    int categoryId;
    String title;
    String content;
    String created_date;
    String created_time;
    boolean checked;

    public Note() {
    }

    public Note(int userId, int categoryId, String title, String content, String created_date, String created_time, boolean checked) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.created_time = created_time;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

