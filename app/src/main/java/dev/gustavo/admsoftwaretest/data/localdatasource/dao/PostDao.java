package dev.gustavo.admsoftwaretest.data.localdatasource.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.localdatasource.entity.NewPostEntity;
import dev.gustavo.admsoftwaretest.data.localdatasource.entity.PostEntity;

@Dao
public interface PostDao {
    @Query("SELECT * FROM post")
    List<PostEntity> getAll();

    @Query("SELECT * FROM post WHERE id = :id")
    PostEntity findByID(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void newPost(PostEntity postEntity);

    @Insert(entity = PostEntity.class,
            onConflict = OnConflictStrategy.IGNORE)
    void newPost(NewPostEntity newPostEntity);

    @Delete
    void deletePost(PostEntity postEntity);

    @Update
    void updatePost(PostEntity postEntity);
}
