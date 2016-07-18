package com.vishnus1224.flickflipper.ui.presenter;

import com.vishnus1224.flickflipper.ui.view.PhotoStreamView;
import com.vishnus1224.flickflipper.usecase.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class PhotoStreamPresenterTest {

    @Mock
    PhotoStreamView photoStreamView;

    @Mock
    UseCase useCase;

    private PhotoStreamPresenter photoStreamPresenter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        photoStreamPresenter = new PhotoStreamPresenter(useCase);

        photoStreamPresenter.onViewAttached(photoStreamView);
    }

    @Test
    public void testOnViewDetached() throws Exception {

        photoStreamPresenter.onViewDetached();

        verify(useCase).unSubscribe();

    }

    @Test
    public void testGetPublicPhotoStream() throws Exception {

        photoStreamPresenter.getPublicPhotoStream();

        verify(photoStreamView).showProgressBar();
        verify(useCase).execute(any(Subscriber.class));

    }
}