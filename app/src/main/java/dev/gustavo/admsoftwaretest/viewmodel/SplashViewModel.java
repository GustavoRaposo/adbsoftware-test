package dev.gustavo.admsoftwaretest.viewmodel;

import static android.os.Looper.getMainLooper;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.localdatasource.response.SavePostListCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.PostService;
import dev.gustavo.admsoftwaretest.domain.GetPostList;
import dev.gustavo.admsoftwaretest.domain.GetPostListUseCase;
import dev.gustavo.admsoftwaretest.domain.SavePostList;
import dev.gustavo.admsoftwaretest.domain.SavePostListUseCase;

public class SplashViewModel extends ViewModel {
    private PostRepository postRepository;
    private GetPostListUseCase getPostListUseCase;
    private SavePostListUseCase savePostListUseCase;
    private MutableLiveData<List<Post>> _posts = new MutableLiveData<>();
    public LiveData<List<Post>> posts = _posts;
    private MutableLiveData<Boolean> _saving = new MutableLiveData<>();
    public LiveData<Boolean> saving = _saving;

    public SplashViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
        getPostListUseCase = new GetPostList(postRepository);
        savePostListUseCase = new SavePostList(postRepository);
    }

    public void getPostList() {
        getPostListUseCase.invoke(_posts);
    }

    public void savePostList(List<Post> posts) {
        savePostListUseCase.invoke(posts, _saving);
    }
}
