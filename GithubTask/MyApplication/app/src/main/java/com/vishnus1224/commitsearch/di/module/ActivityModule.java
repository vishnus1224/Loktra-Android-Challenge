package com.vishnus1224.commitsearch.di.module;

import android.app.Activity;
import android.view.LayoutInflater;

import com.vishnus1224.commitsearch.datastore.CloudCommitsDataStore;
import com.vishnus1224.commitsearch.datastore.CommitsDataStore;
import com.vishnus1224.commitsearch.di.scope.PerActivity;
import com.vishnus1224.commitsearch.model.QueryParamPage;
import com.vishnus1224.commitsearch.repository.CommitRepositoryImpl;
import com.vishnus1224.commitsearch.repository.CommitsRepository;
import com.vishnus1224.commitsearch.usecase.GetCommitsUseCase;
import com.vishnus1224.commitsearch.usecase.UseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Provides activity level dependencies.
 * Created by Vishnu on 7/16/2016.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){

        this.activity = activity;

    }

    /**
     * Provide an instance of the cloud commit data store.
     * @param cloudCommitsDataStore CloudCommitsDataStore instance.
     * @return CloudCommitsDataStore instance.
     */
    @Provides @PerActivity
    CommitsDataStore provideCommitsDataStore(CloudCommitsDataStore cloudCommitsDataStore){

        return cloudCommitsDataStore;

    }

    /**
     * Provide an instance of the commit repository implementation.
     * @param commitRepositoryImpl CommitRepositoryImpl instance.
     * @return CommitRepositoryImpl instance.
     */
    @Provides @PerActivity
    CommitsRepository provideCommitsRepository(CommitRepositoryImpl commitRepositoryImpl){

        return commitRepositoryImpl;

    }

    /**
     * Provides a named use case instance i.e use case for getting commits from the server.
     * @param getCommitsUseCase GetCommitsUseCase instance.
     * @return GetCommitsUseCase instance.
     */
    @Provides @PerActivity @Named("getCommits")
    UseCase provideGetCommitsUseCase(GetCommitsUseCase getCommitsUseCase){

        return getCommitsUseCase;

    }

    @Provides @PerActivity
    LayoutInflater provideLayoutInflater(){

        return activity.getLayoutInflater();

    }

    @Provides @PerActivity
    QueryParamPage provideQueryParamPage(){

        return new QueryParamPage(1);
    }


}
