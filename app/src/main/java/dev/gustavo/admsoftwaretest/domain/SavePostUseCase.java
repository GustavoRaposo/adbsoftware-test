package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface SavePostUseCase {
    void invoke(int userId, String title, String body, MutableLiveData<Boolean> done);
}
