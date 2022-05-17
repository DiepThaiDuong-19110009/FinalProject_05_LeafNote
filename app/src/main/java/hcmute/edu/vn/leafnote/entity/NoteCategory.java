package hcmute.edu.vn.leafnote.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class NoteCategory implements Serializable {
    @PrimaryKey()
    @NonNull
    int id;
    String caterory;

    public NoteCategory() {
    }

    public NoteCategory(int id, String caterory) {
        this.id = id;
        this.caterory = caterory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaterory() {
        return caterory;
    }

    public void setCaterory(String caterory) {
        this.caterory = caterory;
    }
}
