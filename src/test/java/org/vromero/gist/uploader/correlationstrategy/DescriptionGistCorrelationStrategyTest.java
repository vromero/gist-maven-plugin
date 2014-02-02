package org.vromero.gist.uploader.correlationstrategy;


import junit.framework.Assert;
import org.eclipse.egit.github.core.Gist;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DescriptionGistCorrelationStrategyTest {

    private DescriptionGistCorrelationStrategy strategy;

    @Before
    public void setUp() {
        strategy = new DescriptionGistCorrelationStrategy();
    }

    @Test
    public void testNullOnEmptyList() {
        Assert.assertNull(strategy.correlate(new Gist(), new ArrayList<Gist>()));
    }

    @Test
    public void testGistOnValidCorrelation() {
        Gist candidate = new Gist();
        candidate.setDescription("valid description");

        Gist element1 = new Gist();
        element1.setId("element1");
        element1.setDescription("different description");

        Gist element2 = new Gist();
        element2.setId("element2");
        element2.setDescription("valid description");

        List<Gist> userGists = new ArrayList<Gist>();
        userGists.add(element1);
        userGists.add(element2);

        Gist correlated = strategy.correlate(candidate, userGists);

        Assert.assertNotNull(correlated);
        assertThat(correlated, instanceOf(Gist.class));
        assertThat(correlated.getId(), is(element2.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnNullGist() {
        List<Gist> userGists = new ArrayList<Gist>();
        Gist correlated = strategy.correlate(null, userGists);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionOnNullUserGists() {
        Gist candidate = new Gist();
        Gist correlated = strategy.correlate(candidate, null);
    }

}
