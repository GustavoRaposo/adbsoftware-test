package dev.gustavo.admsoftwaretest.data.localdatasource.entity;

import androidx.room.ColumnInfo;

public class NewPostEntity {
    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "body")
    private String body;

    public NewPostEntity(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
