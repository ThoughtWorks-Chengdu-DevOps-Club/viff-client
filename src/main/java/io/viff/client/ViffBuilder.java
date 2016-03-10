package io.viff.client;

import org.openqa.selenium.WebDriver;

public class ViffBuilder {
    private ViffClient viffClient;
    private String apiAdrress;
    private int projectID;
    private String tag;
    private WebDriver driver;

    public ViffClient create() {
        ViffClient viffClient = new ViffClient(apiAdrress, projectID, tag);
        viffClient.setWebDriver(driver);
        return viffClient;
    }

    public ViffBuilder setApiAdrress(String  apiAdrress) {
        this.apiAdrress = apiAdrress;
        return this;
    }

    public ViffBuilder setProjectID(int projectID){
        this.projectID = projectID;
        return this;
    }

    public ViffBuilder setTag(String tag){
        this.tag = tag;
        return this;
    }

    public ViffBuilder setWebDriver(WebDriver driver){
        this.driver = driver;
        return this;
    }
}
