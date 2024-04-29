package dev.gustavo.admsoftwaretest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.network.PostService;
import dev.gustavo.admsoftwaretest.domain.EditPost;
import dev.gustavo.admsoftwaretest.domain.EditPostUseCase;

public class EditPostViewModel extends ViewModel {

    private PostRepository postRepository;
    private EditPostUseCase editPostUseCase;
    private MutableLiveData<Boolean> _save = new MutableLiveData<>();
    public LiveData<Boolean> save = _save;

    public EditPostViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
        editPostUseCase = new EditPost(postRepository);
    }

    public void edit(int userId, int id, String trim, String trim1) {
        editPostUseCase.invoke(userId, id, trim, trim1, _save);
    }
}
