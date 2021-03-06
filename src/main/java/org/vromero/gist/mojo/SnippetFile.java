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

import java.io.File;

public class SnippetFile {

	@Parameter(required = true )
	private File location;
	
	@Parameter(required = false )
	private String snippetId;
	
	@Parameter(required = true )
	private String gistFileName;

	public File getLocation() {
		return location;
	}

	public void setLocation(File location) {
		this.location = location;
	}

	public String getSnippetId() {
		return snippetId;
	}

	public void setSnippetId(String snippetId) {
		this.snippetId = snippetId;
	}

	public String getGistFileName() {
		return gistFileName;
	}

	public void setGistFileName(String gistFileName) {
		this.gistFileName = gistFileName;
	}
	
}
