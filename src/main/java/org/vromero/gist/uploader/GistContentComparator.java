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
import org.codehaus.plexus.util.StringUtils;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

/**
 * Comparator for gists. Will decide if the difference between two Gists is enough to perform an update.
 *
 */
public class GistContentComparator {

    private Log log;

    public GistContentComparator(Log log) {
        this.log = log;
    }

    /**
     * Checks if the difference between two Gists is enough to perform an update.
     *
     * @param original the online gist
     * @param candidate the potentially new gist
     * @return true if the gist requires update
     */
	public boolean isUpdateNeeded(Gist original, Gist candidate) {

		if (!StringUtils.equals(original.getDescription(), candidate.getDescription())) {
            log.debug("Update needed as descriptions differ");
			return true;
		}
		
		if (original.getFiles().size() != candidate.getFiles().size()) {
            log.debug("Update needed as number of files differ");
			return true;
		}
		
		for (String fileName : original.getFiles().keySet()) {
			GistFile gistFile = candidate.getFiles().get(fileName);
			if (gistFile == null) {
                log.debug("Update needed as at least one file in not present in the candidate");
				return true;
			}

			if (!StringUtils.equals(gistFile.getContent(), original.getFiles().get(fileName).getContent())) {
                log.debug("Update needed as at least one file differ in contents");
				return true;
			}
		}
		
		return false;
	}
}
