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

import java.io.IOException;

import org.codehaus.plexus.util.StringUtils;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

public class GistContentComparator {

	public static boolean isUpdateNeeded(Gist gist, Gist candidate) throws IOException {

		if (!StringUtils.equals(gist.getDescription(), candidate.getDescription())) {
			return false;
		}
		
		if (gist.getFiles().size() != candidate.getFiles().size()) {
			return false;
		}
		
		for (String fileName : gist.getFiles().keySet()) {
			GistFile gistFile = candidate.getFiles().get(fileName);
			if (gistFile == null) {
				return false;
			}
			
			if (StringUtils.equals(gistFile.getContent(), gist.getFiles().get(fileName).getContent())) {
				return false;
			}
		}
		
		return true;
	}
}
