package dev.gustavo.admsoftwaretest.data;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.localdatasource.response.DeletePostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.EditPostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostListResponse;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostResponse;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.NewPostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.SavePostListCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.response.GetPostResponse;

public interface PostRepository {
    void getPosts(GetPostResponse response);
    void getLocalPosts(GetLocalPostListResponse response);
    void getLocalPost(int id, GetLocalPostResponse response);
    void save(Post post, NewPostCallback callback);
    void saveAll(List<Post> posts, SavePostListCallback callback);
    void delete(Post post, DeletePostCallback callback);
    void update(int userId, int id, String title, String body, EditPostCallback callback);
}


