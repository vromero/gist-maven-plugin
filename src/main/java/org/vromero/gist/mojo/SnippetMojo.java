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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.vromero.gist.snippet.SnippetManager;

import java.io.IOException;

@Mojo( name = "snippet", defaultPhase = LifecyclePhase.SITE, requiresOnline = true, requiresProject = true)
public class SnippetMojo extends AbstractGistMojo {
	
    public void execute() throws MojoExecutionException {

        SnippetManager snippetManager = new SnippetManager(getEncoding(), getOutputDirectory());

    	try {
            int gistCount = 0;

    		for (Gist gist : getGists()) {

    			for (SnippetFile file : gist.getFiles()) {
    				getLog().debug("Generating snippet for " + file.getGistFileName() + " and snippetId " + file.getSnippetId());
                    snippetManager.createSnippet(file.getLocation(), String.valueOf(gistCount), file.getSnippetId());
    			}
    			
    			gistCount++;
    		}
    	} catch ( IOException e ) {
            throw new MojoExecutionException( "Error executing snippet creation mojo", e );
        }
    }

}
