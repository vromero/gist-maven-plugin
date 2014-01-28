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
import java.net.URL;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.codehaus.plexus.util.FileUtils;
import org.vromero.gist.snippet.SnippetReader;

@Mojo( name = "snippet", defaultPhase = LifecyclePhase.SITE, requiresOnline = true, requiresProject = true)
public class SnippetMojo extends AbstractGistMojo {
	
    public void execute() throws MojoExecutionException {
    	int gistCount = 0;

    	try {
    		for (Gist gist : getGists()) {
    			File gistOutputDirectory = openOrCreateOutputDirectory(gistCount);
    			
    			for (SnippetFile file : gist.getFiles()) {
    				getLog().debug("Generating snippet for " + file.getGistFileName() + " and snippetId " + file.getSnippetId());
    				processFile(file.getLocation(), file.getSnippetId(), gistOutputDirectory);	
    			}
    		}
    	} catch ( IOException e ) {
            throw new MojoExecutionException( "Error executing snippet creation mojo", e );
        }
    }

    private File openOrCreateOutputDirectory(int gistCount) throws IOException {
    	File gistOutputDirectory = new File(getOutputDirectory(), "gist-" + String.valueOf(gistCount));
		FileUtils.forceMkdir(gistOutputDirectory);
		return gistOutputDirectory;
    }
    
    private void processFile(File inputFile, String outputFileName, File outputDirectory) throws IOException {
       	URL url = FileUtils.toURLs(new File[]{inputFile})[0];
        SnippetReader snippetReader = new SnippetReader(url);
        File file = new File( outputDirectory, outputFileName);
        FileUtils.fileWrite(file, snippetReader.readSnippet("snip-id").toString());
    }

}
