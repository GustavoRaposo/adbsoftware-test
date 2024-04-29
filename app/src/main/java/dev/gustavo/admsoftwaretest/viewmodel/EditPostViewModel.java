package dev.gustavo.admsoftwaretest.viewmodel;

import androidx.lifecycle.ViewModel;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.PostRepositoryImp;
import dev.gustavo.admsoftwaretest.data.localdatasource.PostDatabase;
import dev.gustavo.admsoftwaretest.data.network.PostService;

public class EditPostViewModel extends ViewModel {

    private PostRepository postRepository;

    public EditPostViewModel() {
        postRepository = new PostRepositoryImp(PostService.getInstance(), PostDatabase.getInstance().postDao());
    }
}
