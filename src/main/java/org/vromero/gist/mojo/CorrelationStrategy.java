package org.vromero.gist.mojo;

import org.vromero.gist.uploader.correlationstrategy.DescriptionGistCorrelationStrategy;
import org.vromero.gist.uploader.correlationstrategy.GistCorrelationStrategy;
import org.vromero.gist.uploader.correlationstrategy.NoneGistCorrelationStrategy;

public enum CorrelationStrategy {

    DESCRIPTION(new DescriptionGistCorrelationStrategy()), NONE(new NoneGistCorrelationStrategy());

    GistCorrelationStrategy gistCorrelationStrategy;

    private CorrelationStrategy(GistCorrelationStrategy strategy) {
        gistCorrelationStrategy = strategy;
    }

    public GistCorrelationStrategy getGistCorrelationStrategy() {
        return gistCorrelationStrategy;
    }
}
