package io.viff.client.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface ViffRestService {
    @Multipart
    @POST("/upload")
    Call<ResponseBody> uploadScreenshot(@PartMap Map<String, RequestBody> map);

    @GET("/compare")
    Call<ResponseBody> viffDiffrent(@Query("from") String from, @Query("to") String to);
}
