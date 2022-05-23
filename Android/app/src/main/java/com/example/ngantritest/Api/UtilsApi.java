package com.example.ngantritest.Api;

public class UtilsApi {
    public static final String BASE_URL_API = "http://192.168.100.34/Project%20Ngantri%202/Web/API-android/API/";

    //DEKLARASI INTERFACE BaseAPIService
    public static BaseApiService getApiService() {
        return AndroidClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
