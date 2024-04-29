package dev.gustavo.admsoftwaretest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.network.PostService;
import dev.gustavo.admsoftwaretest.domain.SavePost;
import dev.gustavo.admsoftwaretest.domain.SavePostUseCase;

public class NewPostViewModel extends ViewModel {
    private MutableLiveData<Boolean> _save = new MutableLiveData<>();
    public LiveData<Boolean> save = _save;

    private SavePostUseCase savePostUseCase;
    private PostRepository postRepository;

    public NewPostViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
        savePostUseCase = new SavePost(postRepository);
    }

    public void save(int userId, String title, String body){
        savePostUseCase.invoke(userId, title, body, _save);
    }

}
