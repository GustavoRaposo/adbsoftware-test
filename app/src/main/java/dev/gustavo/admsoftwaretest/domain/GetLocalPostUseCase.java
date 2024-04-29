package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface GetLocalPostUseCase {
    void invoke(int id, MutableLiveData<Post> post);
}
