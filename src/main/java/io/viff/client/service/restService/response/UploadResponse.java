package io.viff.client.service.restService.response;


public class UploadResponse {
    private String url;
    private String tag;
    private String project;
    private Integer buildNumber;


    public String getUrl() {
        return url;
    }


    public String getTag() {
        return tag;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public String getProject() {
        return project;
    }
}
