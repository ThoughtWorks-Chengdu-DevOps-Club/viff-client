package io.viff.client.service;


import io.viff.client.model.Resolution;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class ViffService {

    public static File takeScreenshot(Resolution resolution, WebDriver driver) {
        driver.manage().window().setSize(new Dimension(resolution.getWidth(), resolution.getHeight()));
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
}
