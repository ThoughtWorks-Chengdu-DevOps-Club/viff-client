package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.ViffRestClientManager;
import io.viff.client.service.ViffRestService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import retrofit2.Call;
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

    public ViffClient(String apiAddress, String projectID, String currentTag) {
        viffRestClientManager = new ViffRestClientManager(apiAddress);
        this.projectID = projectID;
        this.currentTag = currentTag;
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Response<ResponseBody> addScreenshot(Resolution resolution, String filename) throws IOException {
        if(driver == null) {
            // TODO read configure to generate web driver
            throw new RuntimeException("Need setting web driver before take screenshot!!");
        }

        File screenshot = takeScreenshot(resolution);
        return uploadScreenshot(screenshot, filename);
    }


    private File takeScreenshot(Resolution resolution) throws IOException {
        driver.manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot;
    }

    private Response<ResponseBody> uploadScreenshot(File screenshot, String filename) throws IOException {
        ViffRestService viffRestService = viffRestClientManager.getViffRestService();

        RequestBody file = RequestBody.create(MediaType.parse("image/*"), screenshot);
        Map<String, RequestBody> map= new HashMap<String, RequestBody>();
        map.put(String.format("file\"; filename=\"%s", filename), file);

        Call<ResponseBody> call = viffRestService.uploadScreenshot(projectID, currentTag, map);
        Response<ResponseBody> response = call.execute();

        if(!response.isSuccess()){
            throw new RuntimeException("Bad Request");
        }
        // TODO save build num and save

        return response;
    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) {
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
