package org.vromero.gist.uploader.correlationstrategy;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.egit.github.core.Gist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Strategy to correlate using the description field of the gists.
 */
public class DescriptionGistCorrelationStrategy implements GistCorrelationStrategy {

    /**
     * Correlate against the userGists the given gist using the description field
     * @param gist the gist to correlate
     * @param userGists list of gists to be iterated
     * @return gist correlated or null if not found
     */
	@Override
    public Gist correlate(Gist gist, List<Gist> userGists) {

        if (gist == null) {
            throw new IllegalArgumentException("gist cannot be null");
        } else if (userGists == null) {
            throw new IllegalArgumentException("user gists cannot be null");
        }

        checkForDescriptionUniqueness(userGists);
        for (Gist candidate : userGists) {
			if (candidate.getDescription().equals(gist.getDescription())) {
				return candidate;
			}
		}
		
		return null;
	}

    private void checkForDescriptionUniqueness(List<Gist> userGists) {
        Set<String> descriptionSet = new HashSet<String>(userGists.size());
        for (Gist gist : userGists) {
            boolean inserted = descriptionSet.add(gist.getDescription());
            if (!inserted) {
                throw new IllegalStateException
                        ("Description correlation cannot be use with users that have duplicated descriptions");
            }
        }
    }
	
}
