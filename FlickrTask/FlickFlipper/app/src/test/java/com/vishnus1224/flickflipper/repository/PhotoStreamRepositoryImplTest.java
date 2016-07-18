package com.vishnus1224.flickflipper.repository;

import com.vishnus1224.flickflipper.MockDataFactory;
import com.vishnus1224.flickflipper.datasource.PhotoStreamDataSource;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamRepositoryImplTest {

    @Mock
    PhotoStreamDataSource photoStreamDataSource;

    private PhotoStreamRepository photoStreamRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        photoStreamRepository = new PhotoStreamRepositoryImpl(photoStreamDataSource);

    }

    @Test
    public void testGetPublicPhotoStream() throws Exception {

        Observable<PhotoInfoWrapper> wrapperObservable = Observable.just(MockDataFactory.mockPhotoInfoWrapper());

        TestSubscriber<PhotoInfoWrapper> testSubscriber = new TestSubscriber<>();

        when(photoStreamDataSource.getPublicPhotoStream()).thenReturn(wrapperObservable);

        Observable<PhotoInfoWrapper> observable = photoStreamRepository.getPublicPhotoStream();

        observable.subscribe(testSubscriber);

        testSubscriber.assertCompleted();

        testSubscriber.assertNoErrors();

        verify(photoStreamDataSource).getPublicPhotoStream();

        PhotoInfoWrapper photoInfoWrapper = testSubscriber.getOnNextEvents().get(0);

        assertNotNull(photoInfoWrapper);

        assertEquals(photoInfoWrapper.getItems().size(), 1);

        assertEquals(photoInfoWrapper.getItems().get(0).getTitle(), "Amazed");

    }
}