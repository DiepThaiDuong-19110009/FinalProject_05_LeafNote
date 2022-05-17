package hcmute.edu.vn.leafnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import hcmute.edu.vn.leafnote.entity.NoteCategory;
import hcmute.edu.vn.leafnote.entity.Users;

@Dao
public interface NoteCategoryDao {

    @Insert
    void insert(NoteCategory category);
    @Update
    void update(NoteCategory category);
    @Delete
    void delete(NoteCategory category);
}
