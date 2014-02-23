Plugin Configuration
=================

This plugin is published to the Sonatype OSS Repository and therefor, is available from maven central. No special
repository configuration is required.

```xml
<plugin>
    <groupId>org.vromero</groupId>
    <artifactId>gist-maven-plugin</artifactId>
    <version>1.0.0</version>
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
