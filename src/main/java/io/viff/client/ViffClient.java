package io.viff.client;

/**
 * Created by tbzhang on 3/7/16.
 */
public interface ViffClient {
    void addScreenshot();
    DiffResultWrapper viff(String targetTag, int targetBuildNumber);
    DiffResultWrapper viff(String targetTag, String targetBuildNumberAlias);

}
