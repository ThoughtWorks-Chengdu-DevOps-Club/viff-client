package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.ViffService;
import io.viff.client.service.restService.ViffRestClientManager;
import io.viff.client.service.restService.ViffRestService;
import io.viff.client.service.restService.response.UploadResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.WebDriver;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;


public class ViffClient {

    private String projectID;
    private String currentTag;
    private final ViffRestClientManager viffRestClientManager;
    private String buildNum;
    private WebDriver driver;

    public ViffClient(String apiAddress, String projectID, String currentTag) {
        viffRestClientManager = new ViffRestClientManager(apiAddress);
        this.projectID = projectID;
        this.currentTag = currentTag;
    }

    public void setWebDriver(WebDriver driver) {

        this.driver = driver;
    }

    public void addScreenshot(Resolution resolution, String filename) throws IOException {
        if(driver == null) {
            // TODO read configure to generate web driver
            throw new RuntimeException("Need setting web driver before take screenshot!!");
        }

        uploadScreenshot(ViffService.takeScreenshot(resolution, driver), filename);
    }

    private void uploadScreenshot(File screenshot, String filename) throws IOException {
        ViffRestService viffRestService = viffRestClientManager.getViffRestService();

        MultipartBody.Part file = MultipartBody.Part.createFormData("file", filename, RequestBody.create(MediaType.parse("image/*"), screenshot));

        if (TextUtils.isEmpty(buildNum)) {
            Call<UploadResponse> call = viffRestService.uploadScreenshot(projectID, currentTag, file);
            Response<UploadResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Bad Request");
            }
            buildNum = String.valueOf(response.body().getBuildNumber());
        } else {
            Call<UploadResponse> call = viffRestService.uploadScreenshot(projectID, currentTag, buildNum, file);
            // TODO make it async
            // call.enqueue(new UploadCallback());
            Response<UploadResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Bad Request");
            }
        }

    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) {
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
