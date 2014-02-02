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
import org.eclipse.egit.github.core.GistFile;
import org.vromero.gist.snippet.SnippetManager;
import org.vromero.gist.uploader.GistUploader;
import org.vromero.gist.uploader.correlationstrategy.GistCorrelationStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Mojo( name = "upload", defaultPhase = LifecyclePhase.SITE_DEPLOY, requiresOnline = true, requiresProject = true)
public class GistMojo extends AbstractGistMojo {
    
    public void execute() throws MojoExecutionException {

    	try {
    		int gistCount = 0;

            GistUploader uploader = new GistUploader(getUsername(), getPassword(), getLog());
            SnippetManager snippetManager = new SnippetManager(getEncoding(), getOutputDirectory());

    		for (Gist gist : getGists()) {

                GistCorrelationStrategy correlationStrategy = gist.getCorrelationStrategy()
                        .getGistCorrelationStrategy();

                getLog().debug("Processing gist with description " + gist.getDescription());

                org.eclipse.egit.github.core.Gist converted = createGist(gistCount, gist, snippetManager);
                uploader.uploadGist(converted, correlationStrategy);
    			
    			gistCount++;
    		}
    		
    	} catch ( IOException e ) {
            throw new MojoExecutionException( "Error executing Gist upload mojo", e );
        }
    }

    private org.eclipse.egit.github.core.Gist createGist(int tempGistId, org.vromero.gist.mojo.Gist upload,
                                                         SnippetManager snippetManager) throws IOException {
        Map<String, String> files = new HashMap<String, String>(upload.getFiles().size());

        Map<String,GistFile> gistFiles = new HashMap<String,GistFile>(files.size());
        for (SnippetFile file : upload.getFiles()) {
            GistFile gistFile = readSnippetAsGistFile(tempGistId, file, snippetManager);
            gistFiles.put(file.getGistFileName(), gistFile);
        }

        org.eclipse.egit.github.core.Gist gist = new org.eclipse.egit.github.core.Gist()
                .setDescription(upload.getDescription())
                .setPublic(upload.isPublic())
                .setFiles(gistFiles);

        return gist;
    }

    private GistFile readSnippetAsGistFile(int tempGistId, SnippetFile snippetFile,
                                           SnippetManager snippetManager) throws IOException {

        String fileContent = snippetManager.readSnippet(String.valueOf(tempGistId), snippetFile.getSnippetId());

        return new GistFile()
                .setContent(fileContent)
                .setFilename(snippetFile.getGistFileName());
    }
    
}
