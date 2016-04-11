package io.viff.client.service.restService.response;


import io.viff.client.service.restService.request.ViffRequest;

import java.util.List;

public class ViffResponse {
    private ViffRequest metaData;

    private List<ViffItemResponse> viffResult;

    public ViffRequest getMetaData() {
        return metaData;
    }

    public void setMetaData(ViffRequest metaData) {
        this.metaData = metaData;
    }

    public List<ViffItemResponse> getViffResult() {
        return viffResult;
    }

    public void setViffResult(List<ViffItemResponse> viffResult) {
        this.viffResult = viffResult;
    }
}
