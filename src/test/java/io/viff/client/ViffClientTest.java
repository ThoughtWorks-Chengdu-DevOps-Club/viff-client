package io.viff.client;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import io.viff.client.model.Resolution;
import io.viff.client.service.restService.response.UploadResponse;
import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.runner;

public class ViffClientTest {

    private ViffClient viffClient;
    private Runner runner;
    private final int RESOLUTION_HEIGHT = 860;
    private final int RESOLUTION_WIDTH = 1208;
    private HttpServer server;

    @Before
    public void setUp() {
        server = httpServer();
        runner = runner(server);
        runner.start();

        viffClient = new ViffClient("http://127.0.0.1:"+server.port(), "testProject", "tag");
    }

    @After
    public void tearDown() {
        runner.stop();
        viffClient = null;
    }

    @Test
    public void testAddScreenshot() throws IOException {
        FirefoxDriver driver = new FirefoxDriver();
        driver.get("http://www.baidu.com");
        viffClient.setWebDriver(driver);

        URL url = this.getClass().getClassLoader().getResource("uploadResponse.json");
        assert url != null;
        server.post(match(uri("/file/upload/.*"))).response(file(url.getPath()));
        viffClient.addScreenshot(new Resolution(RESOLUTION_WIDTH, RESOLUTION_HEIGHT), "test");
        driver.close();
    }


    @Test
    public void testViff() throws IOException {
        URL url = this.getClass().getClassLoader().getResource("viffResponse.json");
        assert url != null;
        server.post(by(uri("/viff"))).response(file(url.getPath()));
        viffClient.viff("dev", 2);
    }
}