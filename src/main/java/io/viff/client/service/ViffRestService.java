package io.viff.client.service;

import io.viff.client.service.HTTPResponse.UploadResponse;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface ViffRestService {
    @Multipart
    @POST("/upload/{projectID}/{tag}")
    Call<UploadResponse> uploadScreenshot(@Path("projectID") String projectID, @Path("tag") String tag, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("/upload/{projectID}/{tag}/{buildNumber}")
    Call<UploadResponse> uploadScreenshotWithBuildNumber(@Path("projectID") String projectID, @Path("tag") String tag, @Part("buildNumber") String buildNumber, @PartMap Map<String, RequestBody> map);

    @GET("/compare")
    Call<ResponseBody> viffDiffrent(@Query("from") String from, @Query("to") String to);
}
