package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ViffClient {

    private int projectId;
    private String tag;
    private WebDriver driver;

    public ViffClient(int projectId, String tag) {
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

    private void uploadScreenshot() {
        // TODO upload
    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber){
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
