package hcmute.edu.vn.leafnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import hcmute.edu.vn.leafnote.entity.Users;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<Users> getAll();

    @Query("SELECT * FROM users WHERE id =:id")
    List<Users> getUserById(int id);
    // login
    @Query("SELECT * FROM users WHERE username =:name and password=:pass")
    Users Login(String name, String pass);
    // tìm user theo username
    @Query("SELECT * FROM users WHERE username=:name")
    Users FindUserByUserName(String name);
    // tìm user theo email
    @Query("SELECT * FROM users WHERE email=:email")
    Users FindUserByEmail(String email);

    @Insert
    void insert(Users user);
    @Update
    void update(Users user);
    @Delete
    void delete(Users user);
}
