package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.entity.PostEntity;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostListResponse;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class GetLocalPostList implements GetLocalPostListUseCase{
    private PostRepository repository;

    public GetLocalPostList(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(MutableLiveData<List<Post>> posts) {
        repository.getLocalPosts(new GetLocalPostListResponse() {
            @Override
            public void onResponse(List<Post> list) {
                Handler mainHandler = new Handler(getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        posts.setValue(list);
                    }
                });
            }
        });
    }
}
