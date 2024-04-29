package dev.gustavo.admsoftwaretest.data.localdatasource.response;

import dev.gustavo.admsoftwaretest.data.localdatasource.entity.PostEntity;
import dev.gustavo.admsoftwaretest.data.model.Post;

public interface GetLocalPostResponse {
    void onResponse(Post entity);
}
