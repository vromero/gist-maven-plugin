package org.vromero.gist.mojo;

import junit.framework.Assert;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vromero.gist.snippet.SnippetExtractor;
import org.vromero.gist.snippet.SnippetManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SnippetMojoTest {

    @Mock
    SnippetManager snippetManager;

    @InjectMocks
    SnippetMojo mojo = new SnippetMojo();

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
    }

}
