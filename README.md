gist-maven-plugin
=================

[![Build Status](https://travis-ci.org/vromero/gist-maven-plugin.png?branch=master)](https://travis-ci.org/vromero/gist-maven-plugin) [![Coverage Status](https://coveralls.io/repos/vromero/gist-maven-plugin/badge.png)](https://coveralls.io/r/vromero/gist-maven-plugin)


Maven plugin for the upload of automatically extracted snippets to GitHub's Gist

## Demarcation of snippets

The gist-maven-plugin follows intentionally the same snippet demarcation convention than [Doxia](https://maven.apache.org/guides/mini/guide-snippet-macro.html). The reason behind this is to be able to use the same snippet demarcators to generate maven documentation and gists, e.g:

Java

```java
    // START SNIPPET: snip-id
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
    // END SNIPPET: snip-id
```

XML

```xml
<!-- START SNIPPET: snip-id -->
<navigation-rule>
    <from-view-id>/logon.jsp</from-view-id>
    <navigation-case>
          <from-outcome>success</from-outcome>
          <to-view-id>/mainMenu.jsp</to-view-id>
    </navigation-case>
</navigation-rule>
<!-- END SNIPPET: snip-id -->
```

JSP

```xml
<%-- START SNIPPET: snip-id --%>
<ul>
    <li><a href="newPerson!input.action">Create</a> a new person</li>
    <li><a href="listPeople.action">List</a> all people</li>
</ul>
<%-- END SNIPPET: snip-id --%>
```

## Plugin Configuration

This plugin is published to the Sonatype OSS Repository and therefor, is available from maven central. No special
repository configuration is required.

```xml
<plugin>
    <groupId>org.vromero</groupId>
    <artifactId>gist-maven-plugin</artifactId>
    <version>1.0.0-SNAPSHOT</version>
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
```

* `plugin/configuration/outputDir` is optional, with a default value of: `${project.build.directory}/gists`.
* `plugin/configuration/gist` can be repeated a number of times.
* `plugin/configuration/gist/files/file` can be repeated a number of times.
* `plugin/configuration/gist/correlationStrategy` is mandatory and the only acceptable values are DESCRIPTION and NONE at
the moment. This represents how this plugin will correlate the local snippet with the gist's snippet. Given that there
is not externalId attribute on gist, description is the only mechanism possible. A feature request has been raised to
gist, when this become available correlationStrategy will be optional and will take the externalId correlation as
default value. Meanwhile, this will remain mandatory.
* `plugin/configuration/gist/files/file/gistFileName` represents the name the snippet will have in gist.

### Plugin execution

    mvn org.vromero:gist-maven-plugin:snippet
    mvn org.vromero:gist-maven-plugin:deploy

This can be simplified by adding the following ```pluginGroup``` to the ```pluginGroups``` section of your
```$HOME/.m2/settings.xml``` .

```xml
<pluginGroups>
    <pluginGroup>org.vromero</pluginGroup>
</pluginGroups>
```

With this ```pluginGroup``` added we can invoke the mojos with:

    mvn gist:snippet
    mvn gist:deploy

## Maintainers
* [Victor Romero](http://www.vromero.org)

## Issues
If you have any problems, please [check the project issues](https://github.com/vromero/gist-maven-plugin/issues).

## Contributions
Pull requests are, of course, very welcome!

## Acknowledgments
* Thanks to [Doxia](http://svn.apache.org/viewvc/maven/doxia/doxia/tags/doxia-1.5/doxia-core/src/main/java/org/apache/maven/doxia/macro/snippet/SnippetReader.java?view=log) for the SnippetReader file.
* Thanks to all who have contributed with pull requests, issues, suggestions.

