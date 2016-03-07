package io.viff.client.model;


public class DiffResult {
    private String originImageUrl;
    private String targetImageUrl;
    private String diffImageUrl;
    private boolean isSame;
    private double similarity;

    public String getOriginImageUrl() {
        return originImageUrl;
    }

    public void setOriginImageUrl(String originImageUrl) {
        this.originImageUrl = originImageUrl;
    }

    public String getTargetImageUrl() {
        return targetImageUrl;
    }

    public void setTargetImageUrl(String targetImageUrl) {
        this.targetImageUrl = targetImageUrl;
    }

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public String getDiffImageUrl() {
        return diffImageUrl;
    }

    public void setDiffImageUrl(String diffImageUrl) {
        this.diffImageUrl = diffImageUrl;
    }
}
