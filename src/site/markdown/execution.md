Plugin Execution
=================

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
