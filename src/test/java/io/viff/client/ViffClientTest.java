package io.viff.client;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import io.viff.client.model.Resolution;
import io.viff.client.service.HTTPResponse.UploadResponse;
import okhttp3.ResponseBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import retrofit2.Response;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.runner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ViffClientTest {

    private FirefoxDriver driver;
    private ViffClient viffClient;
    private Runner runner;
    private final static int RESOLUTION_HEIGHT = 860;
    private final static int RESOLUTION_WIDTH = 1208;
    private HttpServer server;

    @Before
    public void setUp() {
        server = httpServer();
        runner = runner(server);
        runner.start();

        driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        viffClient = new ViffClient("http://127.0.0.1:"+server.port(), "testProject", "tag");
        viffClient.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.close();
        runner.stop();
    }

    @Test
    public void testAddScreeshot() throws IOException {
        server.post(match(uri("/upload/.*"))).response(toJson(new UploadResponse()));
        Response<UploadResponse> response = viffClient.addScreenshot(new Resolution(RESOLUTION_WIDTH, RESOLUTION_HEIGHT), "test");
        shouldSaveFileOnLocalDrive();
        shouldBe1208With();
        shouldUploadAImage(response);
    }

    private void shouldSaveFileOnLocalDrive() {
        assertThat(new File("temp.png").exists(), is(true));
    }

    private void shouldBe1208With() throws IOException {
        BufferedImage image = ImageIO.read(new File("temp.png"));
        assertThat(image.getWidth(), is(RESOLUTION_WIDTH));
    }

    private void shouldUploadAImage(Response<UploadResponse> response){
        assertThat(response.isSuccess(), is(true));
    }
}