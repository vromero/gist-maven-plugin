package org.vromero.gist.uploader;


import junit.framework.Assert;
import org.apache.maven.plugin.logging.Log;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GistContentComparatorTest {

    private GistContentComparator comparator;

    @MockitoAnnotations.Mock
    private Log log;

    @Before
    public void setUp() {
        comparator = new GistContentComparator(log);
    }

    @Test
    public void descriptionDifferTest() {
        Gist original = new Gist();
        Gist candidate = new Gist();

        original.setDescription("QWERTY");
        candidate.setDescription("ZXCVB");

        Assert.assertTrue(comparator.isUpdateNeeded(original, candidate));
    }

    @Test
    public void fileSizeDifferTest() {
        org.eclipse.egit.github.core.Gist original = new org.eclipse.egit.github.core.Gist();
        Gist candidate = new Gist();

        original.setDescription("QWERTY");
        candidate.setDescription("QWERTY");

        GistFile originalFile = new GistFile();
        Map<String, GistFile> originalFiles = new HashMap<String, GistFile>();
        originalFiles.put("asdf.txt", originalFile);
        original.setFiles(originalFiles);

        candidate.setFiles(new HashMap<String, GistFile>());

        Assert.assertTrue(comparator.isUpdateNeeded(original, candidate));
    }

    @Test
    public void fileNameDifferTest() {
        org.eclipse.egit.github.core.Gist original = new org.eclipse.egit.github.core.Gist();
        Gist candidate = new Gist();

        original.setDescription("QWERTY");
        candidate.setDescription("QWERTY");

        GistFile originalFile = new GistFile();
        Map<String, GistFile> originalFiles = new HashMap<String, GistFile>();
        originalFiles.put("asdf.txt", originalFile);
        original.setFiles(originalFiles);

        GistFile candidateFile = new GistFile();
        Map<String, GistFile> candidateFiles = new HashMap<String, GistFile>();
        candidateFiles.put("zxcv.txt", candidateFile);
        candidate.setFiles(candidateFiles);

        Assert.assertTrue(comparator.isUpdateNeeded(original, candidate));
    }

    @Test
    public void fileContentDifferTest() {
        org.eclipse.egit.github.core.Gist original = new org.eclipse.egit.github.core.Gist();
        Gist candidate = new Gist();

        original.setDescription("QWERTY");
        candidate.setDescription("QWERTY");

        GistFile originalFile = new GistFile();
        originalFile.setContent("asdf");
        Map<String, GistFile> originalFiles = new HashMap<String, GistFile>();
        originalFiles.put("asdf.txt", originalFile);
        original.setFiles(originalFiles);

        GistFile candidateFile = new GistFile();
        candidateFile.setContent("zxcv");
        Map<String, GistFile> candidateFiles = new HashMap<String, GistFile>();
        candidateFiles.put("asdf.txt", candidateFile);
        candidate.setFiles(candidateFiles);

        Assert.assertTrue(comparator.isUpdateNeeded(original, candidate));
    }

    @Test
    public void nothingDiffersTest() {
        org.eclipse.egit.github.core.Gist original = new org.eclipse.egit.github.core.Gist();
        Gist candidate = new Gist();

        original.setDescription("QWERTY");
        candidate.setDescription("QWERTY");

        GistFile originalFile = new GistFile();
        originalFile.setContent("asdf");
        Map<String, GistFile> originalFiles = new HashMap<String, GistFile>();
        originalFiles.put("asdf.txt", originalFile);
        original.setFiles(originalFiles);

        GistFile candidateFile = new GistFile();
        candidateFile.setContent("asdf");
        Map<String, GistFile> candidateFiles = new HashMap<String, GistFile>();
        candidateFiles.put("asdf.txt", candidateFile);
        candidate.setFiles(candidateFiles);

        Assert.assertFalse(comparator.isUpdateNeeded(original, candidate));
    }


}
