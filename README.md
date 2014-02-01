gist-maven-plugin
=================

[![Build Status](https://travis-ci.org/vromero/gist-maven-plugin.png?branch=master)](https://travis-ci.org/vromero/gist-maven-plugin) [![Coverage Status](https://coveralls.io/repos/vromero/gist-maven-plugin/badge.png)](https://coveralls.io/r/vromero/gist-maven-plugin)


Maven plugin for the upload of automatically extracted snippets to GitHub's Gist

## Demarcation of snippets

The gist-maven-plugin follows intentionally the same snippet demarcation convention than [Doxia](https://maven.apache.org/guides/mini/guide-snippet-macro.html). The reason behind this is to be able to use the same snippet demarcators to generate maven documentation and gists, e.g:

Java

    // START SNIPPET: snip-id
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
    // END SNIPPET: snip-id
XML

    <!-- START SNIPPET: snip-id -->
    <navigation-rule>
        <from-view-id>/logon.jsp</from-view-id>
        <navigation-case>
              <from-outcome>success</from-outcome>
              <to-view-id>/mainMenu.jsp</to-view-id>
        </navigation-case>
    </navigation-rule>
    <!-- END SNIPPET: snip-id -->

JSP

    <%-- START SNIPPET: snip-id --%>
    <ul>
        <li><a href="newPerson!input.action">Create</a> a new person</li>
        <li><a href="listPeople.action">List</a> all people</li>
    </ul>
    <%-- END SNIPPET: snip-id --%>


## Plugin Configuration

        <plugin>
            <groupId>org.vromero</groupId>
            <artifactId>gist-maven-plugin</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <configuration>
                <username>MyUserName</username>
                <password>myp4ssw0rd!</password>
                <outputDir>${project.build.directory}/gists</outputDir>
                <gists>
                    <gist>
                        <correlationStrategy>DESCRIPTION</correlationStrategy>
                        <description>${mule.documentation.version}_message-sources_flowWithPoll</description>
                        <public>true</public>
                        <files>
                            <file>
                                <location>src/main/app/message-sources.xml</location>
                                <snippetId>flowWithPoll</snippetId>
                                <gistFileName>${mule.documentation.version}_message-sources_flowWithPoll</gistFileName>
                            </file>
                        </files>
                    </gist>
                </gists>
            </configuration>
        </plugin>

### Plugin execution

    mvn org.vromero:gist-maven-plugin:gist
    mvn org.vromero:gist-maven-plugin:upload

## Maintainers
* [Victor Romero](http://www.vromero.org)

## Issues
If you have any problems, please [check the project issues](https://github.com/vromero/gist-maven-plugin/issues).

## Contributions
Pull requests are, of course, very welcome!

## Acknowledgments
* Thanks to [Doxia](http://svn.apache.org/viewvc/maven/doxia/doxia/tags/doxia-1.5/doxia-core/src/main/java/org/apache/maven/doxia/macro/snippet/SnippetReader.java?view=log) for the SnippetReader file.
* Thanks to all who have contributed with pull requests, issues, suggestions.

