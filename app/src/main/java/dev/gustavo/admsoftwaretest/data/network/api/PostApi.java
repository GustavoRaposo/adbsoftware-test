package dev.gustavo.admsoftwaretest.data.network.api;

import java.util.List;

import dev.gustavo.admsoftwaretest.data.model.Post;
import dev.gustavo.admsoftwaretest.data.network.response.PostResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {
    @GET("posts")
    Call<PostResponse[]> getPosts();
}
