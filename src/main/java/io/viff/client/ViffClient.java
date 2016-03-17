package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.CallBack.UploadCallback;
import io.viff.client.service.HTTPResponse.UploadResponse;
import io.viff.client.service.ViffRestClientManager;
import io.viff.client.service.ViffRestService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ViffClient {

    private String projectID;
    private String currentTag;
    private WebDriver driver;
    private final ViffRestClientManager viffRestClientManager;
    private String buildNum;

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

        File screenshot = takeScreenshot(resolution);
        uploadScreenshot(screenshot, filename);
    }


    private File takeScreenshot(Resolution resolution) throws IOException {
        driver.manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("temp.png"));
        return screenshot;
    }

    private void uploadScreenshot(File screenshot, String filename) throws IOException {
        ViffRestService viffRestService = viffRestClientManager.getViffRestService();

        Map<String, RequestBody> map = generateMultipleFile(screenshot, filename);

        if (TextUtils.isEmpty(buildNum)) {
            Call<UploadResponse> call = viffRestService.uploadScreenshot(projectID, currentTag, map);
            Response<UploadResponse> response = call.execute();
            if (!response.isSuccess()) {
                throw new RuntimeException("Bad Request");
            }
            buildNum = String.valueOf(response.body().getBuildNumber());
        } else {
            Call<UploadResponse> call = viffRestService.uploadScreenshotWithBuildNumber(projectID, currentTag, buildNum, map);
            call.enqueue(new UploadCallback());
        }

    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) {
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

    private Map<String, RequestBody> generateMultipleFile(File screenshot, String filename) {
        RequestBody file = RequestBody.create(MediaType.parse("image/*"), screenshot);
        Map<String, RequestBody> map= new HashMap<String, RequestBody>();
        map.put(String.format("file\"; filename=\"%s", filename), file);
        return map;
    }

}
