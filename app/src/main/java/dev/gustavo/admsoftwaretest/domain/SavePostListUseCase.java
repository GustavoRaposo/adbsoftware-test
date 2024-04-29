package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.localdatasource.response.SavePostListCallback;
import dev.gustavo.admsoftwaretest.data.model.Post;

public interface SavePostListUseCase {
    void invoke(List<Post> postList, MutableLiveData<Boolean> saving);
}
