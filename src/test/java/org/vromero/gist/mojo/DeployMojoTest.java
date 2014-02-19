package org.vromero.gist.mojo;


import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoAnnotations.Mock;
import org.vromero.gist.snippet.SnippetManager;
import org.vromero.gist.uploader.GistUploader;
import org.vromero.gist.uploader.correlationstrategy.GistCorrelationStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class DeployMojoTest {

    @Mock
    private SnippetManager snippetManager;

    @Mock
    private GistUploader uploader;

    @InjectMocks
    private DeployMojo mojo = new DeployMojo();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWithOneGists() throws MojoExecutionException, IOException {
        SnippetFile snippetFile = new SnippetFile();
        snippetFile.setGistFileName("fileName");
        snippetFile.setLocation(File.createTempFile("pref", "suf"));
        snippetFile.setSnippetId("snip-id");

        List<SnippetFile> snippetFiles = new ArrayList<SnippetFile>();
        snippetFiles.add(snippetFile);

        Gist gist = new Gist();
        gist.setDescription("This is a description");
        gist.setCorrelationStrategy(CorrelationStrategy.DESCRIPTION);
        gist.setPublic(true);
        gist.setFiles(snippetFiles);

        List<Gist> gists = new ArrayList<Gist>();
        gists.add(gist);

        mojo.setGists(gists);

        mojo.execute();

        verify(uploader, atLeastOnce()).uploadGist(any(org.eclipse.egit.github.core.Gist.class),
                any(GistCorrelationStrategy.class));
    }

}
