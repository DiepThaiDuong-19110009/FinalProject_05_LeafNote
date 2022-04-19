package hcmute.edu.vn.leafnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import hcmute.edu.vn.leafnote.dao.UserDao;
import hcmute.edu.vn.leafnote.entity.Note;
import hcmute.edu.vn.leafnote.entity.Users;

@Database(entities = {Users.class, Note.class},version=1)
public abstract class DatabaseConnection extends RoomDatabase {
    private static final String DATABASE_NAME = "leafnote.db";
    private static DatabaseConnection instance;
    public static synchronized DatabaseConnection getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),DatabaseConnection.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();

}
