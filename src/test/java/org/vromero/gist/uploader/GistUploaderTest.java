package org.vromero.gist.uploader;


import junit.framework.Assert;
import org.apache.maven.plugin.logging.Log;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.vromero.gist.uploader.correlationstrategy.GistCorrelationStrategy;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GistUploaderTest {

    private GistUploader uploader;

    @MockitoAnnotations.Mock
    private Log log;

    @MockitoAnnotations.Mock
    private GistService service;

    @MockitoAnnotations.Mock
    private GistCorrelationStrategy correlationStrategy;


    @Before
    public void setUp() {
        uploader = new GistUploader("username", "password", log);
        uploader.setService(service);
    }

    @Test
    public void uploadGistNoCorrelatedTest() throws IOException {
        when(correlationStrategy.correlate(any(Gist.class), any(List.class))).thenReturn(null);

        Gist gist = new Gist();
        gist.setDescription("QWERTY");

        uploader.uploadGist(gist, correlationStrategy );

        verify(service, atLeastOnce()).createGist(gist);
    }

    // TODO: This test class is not yet completed

}
