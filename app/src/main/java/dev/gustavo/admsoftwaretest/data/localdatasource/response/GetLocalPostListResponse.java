package dev.gustavo.admsoftwaretest.data.localdatasource.response;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.model.Post;

public interface GetLocalPostListResponse {
    void onResponse(List<Post> entities);
}
