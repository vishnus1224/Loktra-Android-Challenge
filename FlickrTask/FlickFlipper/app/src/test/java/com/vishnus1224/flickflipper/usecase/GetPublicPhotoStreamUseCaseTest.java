package com.vishnus1224.flickflipper.usecase;

import com.vishnus1224.flickflipper.repository.PhotoStreamRepository;
import com.vishnus1224.flickflipper.threads.BaseExecutor;
import com.vishnus1224.flickflipper.threads.BaseScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Vishnu on 7/18/2016.
 */
public class GetPublicPhotoStreamUseCaseTest {

    @Mock
    PhotoStreamRepository photoStreamRepository;

    @Mock
    BaseExecutor baseExecutor;

    @Mock
    BaseScheduler baseScheduler;

    private GetPublicPhotoStreamUseCase getPublicPhotoStreamUseCase;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        getPublicPhotoStreamUseCase = new GetPublicPhotoStreamUseCase(photoStreamRepository,
                baseExecutor, baseScheduler);

    }

    @Test
    public void testBuildUseCase() throws Exception {

        getPublicPhotoStreamUseCase.buildUseCase();

        verify(photoStreamRepository).getPublicPhotoStream();
        verifyNoMoreInteractions(photoStreamRepository);

    }
}