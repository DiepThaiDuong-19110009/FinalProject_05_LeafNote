package hcmute.edu.vn.leafnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;



import java.util.List;

import hcmute.edu.vn.leafnote.entity.Note;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    List<Note> getAll();

    @Query("SELECT *FROM Note")
    List<Note>searchNote();
    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);
}
