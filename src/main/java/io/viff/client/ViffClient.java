package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.ViffRestClientManager;
import io.viff.client.service.ViffRestService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
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

    private int projectID;
    private String tag;
    private WebDriver driver;
    private final ViffRestClientManager viffRestClientManager;

    public ViffClient(String apiAddress, int projectID, String tag) {
        viffRestClientManager = new ViffRestClientManager(apiAddress);
        this.projectID = projectID;
        this.tag = tag;
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Response<ResponseBody> addScreenshot(Resolution resolution) throws IOException {
        if(driver == null) {
            throw new RuntimeException("Need setting web driver before take screenshot!!");
        }

        File screenshot = takeScreenshot(resolution);
        return uploadScreenshot(screenshot);
    }


    private File takeScreenshot(Resolution resolution) throws IOException {
        driver.manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("temp.png"));
        return screenshot;
    }

    private Response<ResponseBody> uploadScreenshot(File screenshot) throws IOException {
        RequestBody file = RequestBody.create(MediaType.parse("Picture"), screenshot);
        ViffRestService viffRestService = viffRestClientManager.getViffRestService();
        Map<String, RequestBody> map= new HashMap<String, RequestBody>();
        map.put("Test", file);
        Call<ResponseBody> call = viffRestService.uploadScreenshot(map);
        Response<ResponseBody> response = call.execute();

        if(!response.isSuccess()){
            throw new RuntimeException("Bad Request");
        }
        return response;
    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) {
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
