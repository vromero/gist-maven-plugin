package org.vromero.gist.snippet;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.codehaus.plexus.util.FileUtils.*;

public class SnippetManager {

    private File outputDirectory;

    private String encoding;

    public SnippetManager(String encoding, File outputDirectory) {
        this.encoding = encoding;
        this.outputDirectory = outputDirectory;
    }

    public void createSnippet(File inputFile, String tempGistId, String snippetId) throws IOException {
        File gistOutputDirectory = openOrCreateOutputDirectory(tempGistId);
        URL url = toURLs(new File[]{inputFile})[0];
        SnippetExtractor snippetExtractor = new SnippetExtractor(url, encoding);
        File file = new File( gistOutputDirectory, snippetId);
        fileWrite(file, encoding, snippetExtractor.readSnippet(snippetId).toString());
    }

    public String readSnippet(String tempGistId, String snippetId) throws IOException {
        File gistOutputDirectory = openOrCreateOutputDirectory(tempGistId);
        File snippetFileDescriptor = new File(gistOutputDirectory, snippetId);
        return FileUtils.fileRead(snippetFileDescriptor, encoding);
    }

    private File openOrCreateOutputDirectory(String tempGistId) throws IOException {
        // TODO: extract file pattern constant
        File gistOutputDirectory = new File(outputDirectory, "gist-" + tempGistId);
        forceMkdir(gistOutputDirectory);
        return gistOutputDirectory;
    }

}
