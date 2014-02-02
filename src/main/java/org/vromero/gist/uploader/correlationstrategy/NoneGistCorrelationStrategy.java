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

import org.eclipse.egit.github.core.Gist;

import java.util.List;


/**
 * Dummy correlation strategy that will make no effort to try to correlate. It will return always a null correlation.
 * Typically used with the intention of always upload a new gist regardless if it exist already.
 */
public class NoneGistCorrelationStrategy implements GistCorrelationStrategy {

    /**
     * Return always a null correlation.
     * @param gist the gist to correlate
     * @param userGists list of gists to be iterated
     * @return null
     */
	public Gist correlate(Gist gist, List<Gist> userGists) {
        if (gist == null) {
            throw new IllegalArgumentException("gist cannot be null");
        } else if (userGists == null) {
            throw new IllegalArgumentException("user gists cannot be null");
        }

		return null;
	}
	
}
