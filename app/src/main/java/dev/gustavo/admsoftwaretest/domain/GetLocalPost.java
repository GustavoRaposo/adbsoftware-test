package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.GetLocalPostResponse;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class GetLocalPost implements GetLocalPostUseCase {
    private PostRepository repository;

    public GetLocalPost(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(int id, MutableLiveData<Post> post) {
        repository.getLocalPost(id, new GetLocalPostResponse() {
            @Override
            public void onResponse(Post result) {
                Handler mainHandler = new Handler(getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        post.setValue(result);
                    }
                });
            }
        });
    }
}
