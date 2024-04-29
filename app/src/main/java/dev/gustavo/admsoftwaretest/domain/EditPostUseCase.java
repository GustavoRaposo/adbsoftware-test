package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

public interface EditPostUseCase {
    void invoke(int userId, int id, String title, String body, MutableLiveData<Boolean> save);
}
