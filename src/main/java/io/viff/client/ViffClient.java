package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import io.viff.client.service.ViffRestClientManager;
import io.viff.client.service.ViffRestService;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
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

    private int projectId;
    private String tag;
    private WebDriver driver;
    private final ViffRestClientManager viffRestClientManager;

    public ViffClient(String apiAddress, int projectId, String tag) {
        viffRestClientManager = new ViffRestClientManager(apiAddress);
        this.projectId = projectId;
        this.tag = tag;
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void addScreenshot(Resolution resolution) throws IOException {
        if(driver == null) {
            throw new RuntimeException("Need setting web driver before take screenshot!!");
        }

        takeScreenshot();
        uploadScreenshot();
    }


    private void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("temp.png"));
    }

    private void uploadScreenshot() throws IOException {
        ViffRestService viffRestService = viffRestClientManager.getViffRestService();
        Map<String, RequestBody> map= new HashMap<String, RequestBody>();
        Call<ResponseBody> call = viffRestService.uploadScreenshot(map);
        Response<ResponseBody> response = call.execute();
        if(!response.isSuccess()){
            throw new RuntimeException("Bad Request");
        }
    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber) {
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
