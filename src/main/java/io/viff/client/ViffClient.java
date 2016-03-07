package io.viff.client;

import io.viff.client.model.DiffResultWrapper;
import io.viff.client.model.Resolution;
import org.openqa.selenium.WebDriver;


public class ViffClient {

    private Resolution resolution;
    private int projectId;
    private String tag;

    public ViffClient(Resolution resolution, int projectId, String tag) {
        this.resolution = resolution;
        this.projectId = projectId;
        this.tag = tag;
    }

    public void setWebDriver(WebDriver driver) {

    }

    public void addScreenshot(){

    }

    public DiffResultWrapper viff(String targetTag, int targetBuildNumber){
        return null;
    }

    public DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias){
        return null;
    }

}
