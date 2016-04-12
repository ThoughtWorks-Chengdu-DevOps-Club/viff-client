package io.viff.client.service.restService.request;

public class ViffRequest {
    private String projectID;
    private String tagName;
    private Integer buildNumber;
    private String targetTagName;
    private Integer targetBuildNumber;

    public ViffRequest(String projectID, String tagName, Integer buildNumber, String targetTagName, Integer targetBuildNumber) {
        this.projectID = projectID;
        this.tagName = tagName;
        this.buildNumber = buildNumber;
        this.targetTagName = targetTagName;
        this.targetBuildNumber = targetBuildNumber;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTagName() {
        return tagName;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public String getTargetTagName() {
        return targetTagName;
    }

    public Integer getTargetBuildNumber() {
        return targetBuildNumber;
    }

}
