package dev.gustavo.admsoftwaretest.data.localdatasource.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dev.gustavo.admsoftwaretest.data.model.Post;

@Entity(tableName = "post")
public class PostEntity {
    @ColumnInfo(name = "user_id")
    private int userId;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "body")
    private String body;

    public PostEntity(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post toPost() {
        return new Post(userId, id, title, body);
    }
}
