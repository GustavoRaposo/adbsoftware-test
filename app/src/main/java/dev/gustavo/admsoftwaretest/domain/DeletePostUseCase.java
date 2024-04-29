package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface DeletePostUseCase {
    void invoke(Post post, MutableLiveData<Boolean> done);
}
