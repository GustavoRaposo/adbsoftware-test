package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.NewPostCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class SavePost implements SavePostUseCase{

    private PostRepository repository;

    public SavePost(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(int userId, String title, String body, MutableLiveData<Boolean> done) {
        Post post = new Post(userId, title, body);
        if(userId <= 0 | title == null | body == null){
            done.setValue(false);
            return;
        }
        repository.save(post, new NewPostCallback() {
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
