package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.EditPostCallback;

public class EditPost implements EditPostUseCase{
    private PostRepository repository;

    public EditPost(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(int userId, int id, String title, String body, MutableLiveData<Boolean> save) {
        repository.update(userId, id, title, body, new EditPostCallback(){
            @Override
            public void onResponse() {
                Handler mainHandler = new Handler(getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        save.setValue(true);
                    }
                });
            }
        });
    }
}
