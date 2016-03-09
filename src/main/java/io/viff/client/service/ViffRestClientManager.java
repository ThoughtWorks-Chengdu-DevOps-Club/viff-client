package io.viff.client.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViffRestClientManager {
    private ViffRestClientManager instance;
    private Retrofit retrofit;
    private ViffRestService viffRestService;

    private ViffRestClientManager() {
    }

    public ViffRestClientManager(String apiAdrress) {
        retrofit = new Retrofit.Builder()
                .baseUrl(apiAdrress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ViffRestService getViffRestService() {
        if(viffRestService == null) {
            viffRestService = retrofit.create(ViffRestService.class);
        }
        return viffRestService;
    }

}
