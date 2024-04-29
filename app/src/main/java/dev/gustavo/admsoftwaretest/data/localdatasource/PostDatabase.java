package dev.gustavo.admsoftwaretest.data.localdatasource;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dev.gustavo.admsoftwaretest.data.localdatasource.dao.PostDao;
import dev.gustavo.admsoftwaretest.data.localdatasource.entity.PostEntity;

@Database(entities = {PostEntity.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    private static PostDatabase postDatabase = null;
    public abstract PostDao postDao();

    public static PostDatabase getInstance(Context context){
        if(postDatabase == null){
            postDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PostDatabase.class,
                    "adm-test-database"
                    ).build();
        }
        return postDatabase;
    }

    public static PostDatabase getInstance(){
        return postDatabase;
    }
}
