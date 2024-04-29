package dev.gustavo.admsoftwaretest.domain;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dev.gustavo.admsoftwaretest.data.PostRepository;
import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.response.GetPostResponse;

public class GetPostList implements GetPostListUseCase {

    private PostRepository repository;

    public GetPostList(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invoke(MutableLiveData<List<Post>> list) {
        repository.getPosts(new GetPostResponse() {
            @Override
            public void onResponse(List<Post> response) {
                list.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                list.setValue(new ArrayList<>());
            }
        });
    }
}
