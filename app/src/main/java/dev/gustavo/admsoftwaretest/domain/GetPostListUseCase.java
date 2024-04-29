package dev.gustavo.admsoftwaretest.domain;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface GetPostListUseCase {
    void invoke(MutableLiveData<List<Post>> list);
}
