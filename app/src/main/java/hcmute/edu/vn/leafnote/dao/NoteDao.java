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

    @Query("SELECT * FROM Note WHERE user_id=:id")
    List<Note> getAllByUserid(int id);


    @Query("SELECT * FROM Note WHERE category_id=1 AND user_id=:id")
    List<Note> getAllText(int id);

    @Query("SELECT * FROM Note WHERE category_id=2 AND user_id=:id")
    List<Note> getAllPhto(int id);

    @Query("SELECT * FROM Note WHERE category_id=3 AND user_id=:id")
    List<Note> getAllAudio(int id);

    @Query("SELECT *FROM Note WHERE (title LIKE '%'|| :search ||'%' OR content LIKE '%'|| :search ||'%') AND category_id=1 AND user_id=:id")
    List<Note>searchNote(String search, int id);

    @Query("SELECT *FROM Note WHERE (title LIKE '%'|| :search ||'%') AND category_id=2 AND user_id =:id")
    List<Note>searchNotePhoto(String search,int id);

    @Query("SELECT *FROM Note WHERE (title LIKE '%'|| :search ||'%') AND category_id=3 AND user_id=:id")
    List<Note>searchNoteAudio(String search, int id);

    @Query("SELECT * FROM Note WHERE category_id=1 AND user_id=:id ORDER BY id DESC LIMIT 2")
    List<Note> getLastRow(int id);

    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);
}
