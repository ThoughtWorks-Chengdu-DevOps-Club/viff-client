package io.viff.client;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import io.viff.client.model.Resolution;
import okhttp3.ResponseBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;

import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ViffClientTest {

    private FirefoxDriver driver;
    private ViffClient viffClient;
    private Runner runner;

    @Before
    public void setUp() {
        HttpServer server = httpServer(12306);
        runner = runner(server);
        runner.start();

        driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        viffClient = new ViffClient("http://127.0.0.1:12306", 123, "tag");
        viffClient.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.close();
        runner.stop();
    }

    @Test
    public void testAddScreeshot() throws IOException {
        Response<ResponseBody> response = viffClient.addScreenshot(new Resolution(1208, 860));
        shouldSaveFileOnLocalDrive();
        shouldUploadAImage(response);
    }

    private void shouldSaveFileOnLocalDrive() {
        assertThat(new File("temp.png").exists(), is(true));
    }

    private void shouldUploadAImage(Response<ResponseBody> response){
        assertThat(response.isSuccess(), is(true));
    }
}