package dev.gustavo.admsoftwaretest.data.network;

import dev.gustavo.admsoftwaretest.data.network.api.PostApi;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostService {
    private static PostApi service = null;
    public static PostApi getInstance(){
        if(service == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(PostApi.class);
        }
        return service;
    }
}
