package io.viff.client.service.CallBack;


import io.viff.client.service.HTTPResponse.UploadResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadCallback implements Callback<UploadResponse> {
    @Override
    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
        if (!response.isSuccess()) {
            throw new RuntimeException("Bad Request!");
        }
    }

    @Override
    public void onFailure(Call<UploadResponse> call, Throwable t) {

    }
}
