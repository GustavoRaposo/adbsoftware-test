package dev.gustavo.admsoftwaretest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.PostService;
import dev.gustavo.admsoftwaretest.domain.GetLocalPostList;
import dev.gustavo.admsoftwaretest.domain.GetLocalPostListUseCase;
import dev.gustavo.admsoftwaretest.domain.GetPostList;
import dev.gustavo.admsoftwaretest.domain.GetPostListUseCase;
import dev.gustavo.admsoftwaretest.domain.SavePost;
import dev.gustavo.admsoftwaretest.domain.SavePostList;
import dev.gustavo.admsoftwaretest.domain.SavePostListUseCase;
import dev.gustavo.admsoftwaretest.domain.SavePostUseCase;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Post>> _posts = new MutableLiveData<>();

    public LiveData<List<Post>> posts = _posts;

    private GetLocalPostListUseCase getLocalPostListUseCase;
    private PostRepository postRepository;

    public MainViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
        getLocalPostListUseCase = new GetLocalPostList(postRepository);
    }

    public void getPostList() {
        getLocalPostListUseCase.invoke(_posts);
    }
}
