package io.viff.client;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import io.viff.client.model.Resolution;
import io.viff.client.service.restService.response.UploadResponse;
import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.runner;

public class ViffClientTest {

    private static FirefoxDriver driver;
    private static ViffClient viffClient;
    private static Runner runner;
    private final static int RESOLUTION_HEIGHT = 860;
    private final static int RESOLUTION_WIDTH = 1208;
    private static HttpServer server;

    @BeforeClass
    public static void setUp() {
        server = httpServer();
        runner = runner(server);
        runner.start();

        driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        viffClient = new ViffClient("http://127.0.0.1:"+server.port(), "testProject", "tag");
        viffClient.setWebDriver(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
        runner.stop();
        viffClient = null;
    }

    @Test
    public void testAddScreenshot() throws IOException {
        server.post(match(uri("/upload/.*"))).response(toJson(new UploadResponse()));
        viffClient.addScreenshot(new Resolution(RESOLUTION_WIDTH, RESOLUTION_HEIGHT), "test");
    }


    @Test
    public void testViff() {
        server.post(by(uri("/viff"))).response("ok");
        viffClient.viff("dev", "latest");
    }
}