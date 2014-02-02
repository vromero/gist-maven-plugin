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

    @Test
    public void testNullOnIllegalArguments() {
        Assert.assertNull(strategy.correlate(null, null));
    }

}
