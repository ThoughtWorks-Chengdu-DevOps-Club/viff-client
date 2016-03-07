package io.viff.client;

import java.util.List;

/**
 * Created by tbzhang on 3/7/16.
 */
public class DiffResultWrapper {
    private String currentTag;
    private String currentBuildNumber;
    private String targetTag;
    private String targetBuildNumber;

    private List<DiffResult> resultList;

    public String getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(String currentTag) {
        this.currentTag = currentTag;
    }

    public String getCurrentBuildNumber() {
        return currentBuildNumber;
    }

    public void setCurrentBuildNumber(String currentBuildNumber) {
        this.currentBuildNumber = currentBuildNumber;
    }

    public String getTargetTag() {
        return targetTag;
    }

    public void setTargetTag(String targetTag) {
        this.targetTag = targetTag;
    }

    public String getTargetBuildNumber() {
        return targetBuildNumber;
    }

    public void setTargetBuildNumber(String targetBuildNumber) {
        this.targetBuildNumber = targetBuildNumber;
    }

    public List<DiffResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<DiffResult> resultList) {
        this.resultList = resultList;
    }
}
