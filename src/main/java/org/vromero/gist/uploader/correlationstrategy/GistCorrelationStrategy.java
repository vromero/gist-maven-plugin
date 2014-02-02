package org.vromero.gist.uploader.correlationstrategy;

import org.eclipse.egit.github.core.Gist;

import java.util.List;


public interface GistCorrelationStrategy {
    Gist correlate(Gist gist, List<Gist> userGists);
}
