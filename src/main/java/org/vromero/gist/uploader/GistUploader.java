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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.FileUtils;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.vromero.gist.mojo.SnippetFile;

public class GistUploader {

	private static DescriptionCorrelationStrategy descriptionCorrelator = new DescriptionCorrelationStrategy();
	
	public static void uploadGist(GitHubClient client, org.vromero.gist.mojo.Gist upload, String user, List<Gist> userGists, File gistOutputDirectory) throws IOException {
    	Gist gist = createGist(upload, gistOutputDirectory);
		
		Gist correlatedGist = correlate(upload.getCorrelationStrategy(), userGists, client, gist);
		
		if (correlatedGist != null && GistContentComparator.isUpdateNeeded(gist, correlatedGist)) {
			gist.setId(correlatedGist.getId());
			gist = new GistService(client).updateGist(gist);
		} else {
			gist = new GistService(client).createGist(gist);
		}
    }

	private static Gist createGist(org.vromero.gist.mojo.Gist upload, File gistOutputDirectory) throws IOException {
		Map<String, String> files = new HashMap<String, String>(upload.getFiles().size());
		
		Map<String,GistFile> gistFiles = new HashMap<String,GistFile>(files.size());
		for (SnippetFile file : upload.getFiles()) {
			File snippetFileDescriptor = new File(gistOutputDirectory, file.getSnippetId());
			
			GistFile gistFile = new GistFile()
				.setContent(FileUtils.fileRead(snippetFileDescriptor))
				.setFilename(file.getGistFileName());
			
			gistFiles.put(file.getGistFileName(), gistFile);
		}
    	
    	Gist gist = new Gist().setDescription(upload.getDescription());
    	
    	gist.setPublic(upload.isPublic());
		gist.setFiles(gistFiles);
		
		return gist;
	}
	
	private static Gist correlate(org.vromero.gist.mojo.Gist.CorrelationStrategy strategy, List<Gist> userGists, GitHubClient client, Gist gist)
			throws IOException {
		if(strategy == org.vromero.gist.mojo.Gist.CorrelationStrategy.DESCRIPTION) {
			return descriptionCorrelator.correlate(userGists, gist);
		}
		return gist;
	}
	
}
