package com.example.appframe.common.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @FileName: com.example.appframe.common.network.NetworkClient.java
 * @author: Alien
 * @date: 2016/10/20
 */
public class NetworkClient {
    public static  String baseUrl="https://www.baidu.com/";
    private NetApi netApi;
    private static NetworkClient networkClient;
    public static  NetworkClient getInstance(){
        if (networkClient==null){
            networkClient=new NetworkClient();
        }
        return networkClient;
    }
    private NetworkClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        netApi=retrofit.create(NetApi.class);
    }
}
