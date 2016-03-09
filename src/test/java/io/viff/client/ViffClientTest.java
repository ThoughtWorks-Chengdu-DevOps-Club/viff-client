package io.viff.client;

import io.viff.client.model.Resolution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ViffClientTest {

    private FirefoxDriver driver;
    private ViffClient viffClient;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        viffClient = new ViffClient("127.0.0.1", 123, "tag");
        viffClient.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testAddScreeshot() throws IOException {
        viffClient.addScreenshot(new Resolution(1208, 860));
        shouldSaveFileOnLocalDrive();
        shouldUploadAImage();
    }

    private void shouldSaveFileOnLocalDrive() {
        assertThat("shouldSaveFileOnLocalDrive", new File("temp.png").exists(), is(true));
    }

    private void shouldUploadAImage(){
        // TODO
    }
}