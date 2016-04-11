package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.ViffService;
import io.viff.client.service.restService.ViffRestClientManager;
import io.viff.client.service.restService.ViffRestService;
import io.viff.client.service.restService.request.ViffRequest;
import io.viff.client.service.restService.response.UploadResponse;
import io.viff.client.service.restService.response.ViffResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.openqa.selenium.WebDriver;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;


public class ViffClient {

    private String projectID;
    private String currentTag;
    private int buildNum;
    private WebDriver driver;
    private final ViffRestService viffRestService;

    public ViffClient(String apiAddress, String projectID, String currentTag) {
        ViffRestClientManager viffRestClientManager = new ViffRestClientManager(apiAddress);
        viffRestService = viffRestClientManager.getViffRestService();
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


        MultipartBody.Part file = MultipartBody.Part.createFormData("file", filename, RequestBody.create(MediaType.parse("image/*"), screenshot));

        if (buildNum == 0) {
            Call<UploadResponse> call = viffRestService.uploadScreenshot(projectID, currentTag, file);
            Response<UploadResponse> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Bad Request");
            }
            buildNum = response.body().getBuildNumber();
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

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) throws IOException {
        ViffRequest viffRequest = new ViffRequest(projectID, currentTag, buildNum, targetTag, targetBuildNumber);
        Call<ViffResponse> viff = viffRestService.viff(viffRequest);
        Response<ViffResponse> response = viff.execute();
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
