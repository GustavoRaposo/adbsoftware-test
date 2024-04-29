package dev.gustavo.admsoftwaretest.domain;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.SavePostListCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;

public class SavePostList implements SavePostListUseCase {
    private PostRepository repository;

    public SavePostList(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(List<Post> postList, MutableLiveData<Boolean> saving) {
        if (postList != null && !postList.isEmpty()) {
            repository.saveAll(postList, new SavePostListCallback() {
                @Override
                public void onResponse() {
                    Handler mainHandler = new Handler(getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            saving.setValue(true);
                        }
                    });
                }
            });
        }
    }
}
