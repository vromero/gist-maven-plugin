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

import org.apache.maven.plugins.annotations.Parameter;

import java.util.Collections;
import java.util.List;

public class Gist {

    @Parameter(property = "correlationStrategy")
    private CorrelationStrategy correlationStrategy;
    
	@Parameter(property = "description")
	private String description;
	
    @Parameter(property = "public", defaultValue = "true")
	private boolean isPublic;

    @Parameter(property = "files", required = true)
    private List<SnippetFile> files;
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public List<SnippetFile> getFiles() {
		return files == null ? Collections.<SnippetFile>emptyList() : files;
	}
	
	public void setFiles(List<SnippetFile> files) {
		this.files = files;
	}

	public CorrelationStrategy getCorrelationStrategy() {
		return correlationStrategy;
	}

	public void setCorrelationStrategy(CorrelationStrategy correlationStrategy) {
		this.correlationStrategy = correlationStrategy;
	}
	
}
