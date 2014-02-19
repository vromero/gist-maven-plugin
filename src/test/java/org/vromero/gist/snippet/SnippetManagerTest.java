package org.vromero.gist.snippet;

import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class SnippetManagerTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testSnippetManagerCreateAndRead() throws IOException {
        File snippetDir = testFolder.newFolder("testSnippetManagerCreate");

        URL javaUrl = getClass().getClassLoader().getResource("snippet");

        Assert.assertNotNull(javaUrl);

        File javaFile;
        try {
            javaFile = new File(javaUrl.toURI());
        } catch(URISyntaxException e) {
            javaFile = new File(javaUrl.getPath());
        }

        Assert.assertNotNull(javaFile);

        SnippetManager manager = new SnippetManager("UTF-8", snippetDir);
        manager.createSnippet(javaFile, "gist-id", "snip-id");
        manager.readSnippet("gist-id", "snip-id");
    }

}
