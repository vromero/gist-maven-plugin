package org.vromero.gist.snippet;

import junit.framework.Assert;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class SnippetExtractorTest {

    private SnippetExtractor extractor;

    @Test
    public void testJavaSnippet() throws IOException {
        URL url = getClass().getClassLoader().getResource("snippet/Java.java");
        Assert.assertNotNull(url);

        extractor = new SnippetExtractor(url, "UTF-8");
        assertThat(extractor.readSnippet("snip-id").length(), is(not(0)));
        assertThat(extractor.readSnippet("NONEXISTENT").length(), is(0));
    }

    @Test
    public void testXmlSnippet() throws IOException {
        URL url = getClass().getClassLoader().getResource("snippet/Xml.xml");
        Assert.assertNotNull(url);

        extractor = new SnippetExtractor(url, "UTF-8");
        assertThat(extractor.readSnippet("snip-id").length(), is(not(0)));
        assertThat(extractor.readSnippet("NONEXISTENT").length(), is(0));
    }


}
