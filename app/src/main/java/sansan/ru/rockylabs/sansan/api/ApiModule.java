package sansan.ru.rockylabs.sansan.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;

/**
 * Created by Zinnur on 19.12.16.
 */

public final class ApiModule {
    private static final boolean ENABLE_LOG = true;
    private static final boolean ENABLE_AUTH = true;
    private static final String AUTH_64 = "***";

    private ApiModule(){}

    public static ApiInterface getApiInterface(String url){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor tokenInterceptor = chain -> {
            String token = AuthPrefs.getToken();
            if (token == null){
                token = "empty";
            }
            Request request = chain.request().newBuilder().addHeader("Authorization", token).build();
            return chain.proceed(request);
        };

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
                .build();






        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }



}