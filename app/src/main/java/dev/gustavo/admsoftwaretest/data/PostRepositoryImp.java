package dev.gustavo.admsoftwaretest.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.gustavo.admsoftwaretest.data.localdatasource.dao.PostDao;
import dev.gustavo.admsoftwaretest.data.localdatasource.entity.NewPostEntity;
import dev.gustavo.admsoftwaretest.data.localdatasource.entity.PostEntity;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.DeletePostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.EditPostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostListResponse;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostResponse;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.NewPostCallback;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.SavePostListCallback;
import dev.gustavo.admsoftwaretest.data.network.api.PostApi;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.response.GetPostResponse;
import dev.gustavo.admsoftwaretest.data.network.response.PostResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepositoryImp implements PostRepository {

    private PostApi service;
    private PostDao dao;

    public PostRepositoryImp(PostApi service, PostDao dao) {
        this.service = service;
        this.dao = dao;
    }

    @Override
    public void getPosts(GetPostResponse postsResponse) {

        service.getPosts().enqueue(new Callback<PostResponse[]>() {
            @Override
            public void onResponse(Call<PostResponse[]> call, Response<PostResponse[]> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Post> posts = mapPostResponseToPost(response.body());
                        postsResponse.onResponse(posts);
                    } else postsResponse.onFailure(new Throwable("Response body is null"));
                }
            }

            @Override
            public void onFailure(Call<PostResponse[]> call, Throwable throwable) {
                postsResponse.onFailure(throwable);
            }
        });
    }

    @Override
    public void getLocalPosts(GetLocalPostListResponse response) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<PostEntity> entities = dao.getAll();
                List<Post> posts = mapPostEntityToPost(entities);
                response.onResponse(posts);
            }
        });
    }

    @NonNull
    private List<Post> mapPostEntityToPost(List<PostEntity> entities) {
        List<Post> posts = new ArrayList<>();
        for (PostEntity entity : entities) {
            posts.add(entity.toPost());
        }
        return posts;
    }

    @NonNull
    private List<Post> mapPostResponseToPost(PostResponse[] responses) {
        List<Post> posts = new ArrayList<>();
        for (PostResponse response : responses) {
            posts.add(response.toPost());
        }
        return posts;
    }

    private List<PostEntity> mapPostToPostEntity(List<Post> posts) {
        List<PostEntity> entities = new ArrayList<>();
        for (Post item : posts) {
            entities.add(new PostEntity(item.getUserId(), item.getId(), item.getTitle(), item.getBody()));
        }
        return entities;
    }

    @Override
    public void save(Post post, NewPostCallback callback) {
        NewPostEntity entity = new NewPostEntity(post.getUserId(), post.getTitle(), post.getBody());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                dao.newPost(entity);
                callback.onResponse();
            }
        });
    }

    @Override
    public void saveAll(List<Post> posts, SavePostListCallback callback) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<PostEntity> entities = mapPostToPostEntity(posts);
                for (PostEntity item:
                     entities) {
                    dao.newPost(item);
                }
                callback.onResponse();
            }
        });
    }

    @Override
    public void getLocalPost(int id, GetLocalPostResponse response) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                PostEntity entity = dao.findByID(id);
                response.onResponse(entity.toPost());
            }
        });
    }

    @Override
    public void delete(Post post, DeletePostCallback callback) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                PostEntity entity = new PostEntity(post.getUserId(), post.getId(), post.getTitle(), post.getBody());
                dao.deletePost(entity);
                callback.onResponse();
            }
        });
    }

    @Override
    public void update(int userId, int id, String title, String body, EditPostCallback callback) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                PostEntity entity = new PostEntity(userId, id, title, body);
                dao.updatePost(entity);
                callback.onResponse();
            }
        });
    }
}
