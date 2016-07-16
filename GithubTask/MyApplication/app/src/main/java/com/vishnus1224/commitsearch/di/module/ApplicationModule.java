package com.vishnus1224.commitsearch.di.module;

import android.app.Application;

import com.vishnus1224.commitsearch.webservice.GithubWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vishnu on 7/16/2016.
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * Provide a single application instance wherever requested.
     * @return Single instance of the current application.
     */
    @Provides @Singleton
    Application provideApplication(){

        return application;
    }

    /**
     * Provides a single instance of OkHttpClient throughout the application.
     * @return Instance of OkHttpClient.
     */
    @Provides @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }

    /**
     * Provides a single instance of GSON converter factory required by retrofit to map JSON string to JAVA objects.
     * @return Instance of GSONConverterFactory.
     */
    @Provides @Singleton
    Converter.Factory provideGSONConverterFactory(){
        return GsonConverterFactory.create();
    }

    /**
     * Provides a single instance of RxJavaCallAdapterFactory needed by retrofit to return Observables.
     * @return RxJavaCallAdapterFactory instance.
     */
    @Provides @Singleton
    CallAdapter.Factory provideRxCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    /**
     * Provides a single instance of Retrofit to be used throughout the application.
     * @param okHttpClient OkHttpClient instance.
     * @param converterFactory ConverterFactory instance.
     * @param callAdapterFactory CallAdapterFactory instance.
     * @return Retrofit instance.
     */
    @Provides @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory){
        return new Retrofit.Builder()
                .baseUrl(GithubWebService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    /**
     * Provides Github WebService instance for interacting with the Github API.
     * @param retrofit Retrofit instance.
     * @return Github WebService instance.
     */
    @Provides @Singleton
    GithubWebService provideGithubWebService(Retrofit retrofit){

        return retrofit.create(GithubWebService.class);

    }
}
