package com.vishnus1224.commitsearch.di.module;

import android.app.Application;

import com.vishnus1224.commitsearch.di.scope.PerApplication;
import com.vishnus1224.commitsearch.threads.BaseScheduler;
import com.vishnus1224.commitsearch.threads.IoScheduler;
import com.vishnus1224.commitsearch.threads.MainThreadScheduler;
import com.vishnus1224.commitsearch.webservice.GithubWebService;

import javax.inject.Named;

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
    @Provides @PerApplication
    Application provideApplication(){

        return application;
    }

    /**
     * Provides a single instance of OkHttpClient throughout the application.
     * @return Instance of OkHttpClient.
     */
    @Provides @PerApplication
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }

    /**
     * Provides a single instance of GSON converter factory required by retrofit to map JSON string to JAVA objects.
     * @return Instance of GSONConverterFactory.
     */
    @Provides @PerApplication
    Converter.Factory provideGSONConverterFactory(){
        return GsonConverterFactory.create();
    }

    /**
     * Provides a single instance of RxJavaCallAdapterFactory needed by retrofit to return Observables.
     * @return RxJavaCallAdapterFactory instance.
     */
    @Provides @PerApplication
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
    @Provides @PerApplication
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
    @Provides @PerApplication
    GithubWebService provideGithubWebService(Retrofit retrofit){

        return retrofit.create(GithubWebService.class);

    }

    /**
     * Provides a schedulers for performing tasks on the io thread.
     * @param ioScheduler IoScheduler instance.
     * @return IoScheduler instance.
     */
    @Provides @PerApplication @Named("io")
    BaseScheduler provideExecutionScheduler(IoScheduler ioScheduler){

        return ioScheduler;

    }

    /**
     * Provides a scheduler for performing tasks on the main thread.
     * @param mainThreadScheduler MainThreadScheduler instance.
     * @return MainThreadScheduler instance.
     */
    @Provides @PerApplication @Named("main")
    BaseScheduler provideMainThreadScheduler(MainThreadScheduler mainThreadScheduler){

        return mainThreadScheduler;

    }
}
