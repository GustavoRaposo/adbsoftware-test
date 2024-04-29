package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.DeletePostCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class DeletePost implements DeletePostUseCase{
    private PostRepository repository;

    public DeletePost(PostRepository repository) {
        this.repository = repository;
    }


    @Override
    public void invoke(Post post, MutableLiveData<Boolean> done) {
        repository.delete(post, new DeletePostCallback() {
            @Override
            public void onResponse() {
                Handler mainHandler = new Handler(getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        done.setValue(true);
                    }
                });
            }
        });
    }
}
