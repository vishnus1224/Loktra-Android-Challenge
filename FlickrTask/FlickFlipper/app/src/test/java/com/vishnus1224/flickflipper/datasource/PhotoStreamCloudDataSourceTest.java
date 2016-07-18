package com.vishnus1224.flickflipper.datasource;

import com.vishnus1224.flickflipper.MockDataFactory;
import com.vishnus1224.flickflipper.model.PhotoInfo;
import com.vishnus1224.flickflipper.model.PhotoInfoWrapper;
import com.vishnus1224.flickflipper.webservice.FlickrWebService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamCloudDataSourceTest {

    @Mock
    FlickrWebService flickrWebService;

    private PhotoStreamCloudDataSource photoStreamCloudDataSource;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        photoStreamCloudDataSource = new PhotoStreamCloudDataSource(flickrWebService);

    }

    @Test
    public void testGetPublicPhotoStream() throws Exception {

        PhotoInfoWrapper photoInfoWrapper = MockDataFactory.mockPhotoInfoWrapper();

        Observable<PhotoInfoWrapper> wrapperObservable = Observable.just(photoInfoWrapper);

        when(flickrWebService.getPublicPhotoStream()).thenReturn(wrapperObservable);

        Observable<PhotoInfoWrapper> photoInfoObservable = photoStreamCloudDataSource.getPublicPhotoStream();

        TestSubscriber<PhotoInfoWrapper> testSubscriber = new TestSubscriber<>();

        photoInfoObservable.subscribe(testSubscriber);

        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

        verify(flickrWebService).getPublicPhotoStream();

        PhotoInfoWrapper infoWrapper = testSubscriber.getOnNextEvents().get(0);

        assertNotNull(infoWrapper);
        assertEquals(infoWrapper.getItems().size(), 1);

        PhotoInfo photoInfo = infoWrapper.getItems().get(0);

        assertEquals(photoInfo.getDescription(), "test description");

    }
}