package org.vromero.gist.uploader.correlationstrategy;


import junit.framework.Assert;
import org.eclipse.egit.github.core.Gist;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NoneGistCorrelationStrategyTest {

    private NoneGistCorrelationStrategy strategy;

    @Before
    public void setUp() {
        strategy = new NoneGistCorrelationStrategy();
    }

    @Test
    public void testNulldOnEmptyList() {
        Assert.assertNull(strategy.correlate(new Gist(), new ArrayList<Gist>()));
    }

    @Test
    public void testNullValidCorrelation() {
        Gist candidate = new Gist();
        candidate.setDescription("valid description");

        List<Gist> userGists = new ArrayList<Gist>();
        userGists.add(candidate);

        Assert.assertNull(strategy.correlate(candidate, userGists));
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
