package dev.gustavo.admsoftwaretest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.PostService;
import dev.gustavo.admsoftwaretest.domain.DeletePost;
import dev.gustavo.admsoftwaretest.domain.DeletePostUseCase;
import dev.gustavo.admsoftwaretest.domain.GetLocalPost;
import dev.gustavo.admsoftwaretest.domain.GetLocalPostUseCase;

public class PostViewModel extends ViewModel {
    private MutableLiveData<Post> _post = new MutableLiveData<>();
    public LiveData<Post> post = _post;
    private MutableLiveData<Boolean> _delete = new MutableLiveData<>();
    public LiveData<Boolean> delete = _delete;
    private GetLocalPostUseCase getLocalPostUseCase;
    private DeletePostUseCase deletePostUseCase;
    private PostRepository postRepository;

    public PostViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
        getLocalPostUseCase = new GetLocalPost(postRepository);
        deletePostUseCase = new DeletePost(postRepository);
    }

    public void getPost(int id) {
        getLocalPostUseCase.invoke(id, _post);
    }

    public void deletePost(Post post){
        deletePostUseCase.invoke(post, _delete);
    }
}
