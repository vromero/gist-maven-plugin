package org.vromero.gist.uploader;

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

import org.apache.maven.plugin.logging.Log;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.vromero.gist.uploader.correlationstrategy.GistCorrelationStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GistUploader {

    private Log log;

    private String username;

    private String password;

    private GistService service;

    List<Gist> userGists;

    public GistUploader(String username, String password, Log log) {
        this.log = log;
        this.username = username;
        this.password = password;
        this.service = buildService(username, password);
    }

    public void uploadGist(Gist gist, GistCorrelationStrategy correlationStrategy) throws IOException {
		Gist correlatedGist = correlationStrategy.correlate(gist, getGists());
        GistContentComparator comparator = new GistContentComparator(log);

		if (correlatedGist != null) {
            if (comparator.isUpdateNeeded(gist, correlatedGist)) {
                log.debug("Updating existent gist");
                gist.setId(correlatedGist.getId());
                service.updateGist(gist);
            } else {
                log.debug("No update required");
            }
		} else {
            log.debug("Creating new gist");
            service.createGist(gist);
		}

    }

    private List<Gist> getGists() throws IOException {
        if(userGists != null) {
            return userGists;
        }

        log.debug("Obtaining existing gists");
        List<Gist> incompleteGists = service.getGists(username);
        List<Gist> userGists = new ArrayList<Gist>(incompleteGists.size());

        for (Gist gist : incompleteGists) {
            userGists.add(service.getGist(gist.getId()));
        }
        this.userGists = userGists;
        return userGists;
    }

    private GistService buildService(String username, String password) {
        log.debug("Creating gist service with username " + username);
        GitHubClient client = new GitHubClient().setCredentials(username, password);
        service = new GistService(client);
        return service;
    }

    public void setService(GistService service) {
        this.service = service;
    }
}
