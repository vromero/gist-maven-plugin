package org.vromero.gist.mojo;

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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.vromero.gist.uploader.GistUploader;

@Mojo( name = "upload", defaultPhase = LifecyclePhase.SITE_DEPLOY, requiresOnline = true, requiresProject = true)
public class GistMojo extends AbstractGistMojo {
    
    public void execute() throws MojoExecutionException {

    	try {
    		int gistCount = 0;

    		getLog().debug("Connecting to GitHub with username " + getUsername());
        	GitHubClient client = new GitHubClient().setCredentials(getUsername(), getPassword());
        	
        	getLog().debug("Obtaining existing gists");
        	List<org.eclipse.egit.github.core.Gist> userGists = new GistService(client).getGists(getUsername());
        	
    		for (Gist gist : getGists()) {
    			File gistOutputDirectory = new File(getOutputDirectory(), "gist-" + String.valueOf(gistCount));
    			
    			getLog().debug("Processing gist with description " + gist.getDescription());
    			GistUploader.uploadGist(client, gist, getUsername(), userGists, gistOutputDirectory);
    			
    			gistCount++;
    		}
    		
    	} catch ( IOException e ) {
            throw new MojoExecutionException( "Error executing Gist upload mojo", e );
        }
    }
    
}
