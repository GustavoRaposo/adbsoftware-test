package dev.gustavo.admsoftwaretest.data.network.response;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface GetPostResponse {
    void onResponse(List<Post> response);

    void onFailure(Throwable throwable);
}
