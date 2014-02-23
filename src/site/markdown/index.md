Introduction
=================

With this plugin it is possible to demarcate portions of an XML, Java or JSP files:

```java
public class Java {

    // START SNIPPET: snip-id
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
    }
    // END SNIPPET: snip-id
}
```

This demarcations will be sent to GitHub's Gist to create or update previous snippets.

![Gist](./images/gist.png "Gist")

Snippets could be backed by tests, effectively rendering them exact and maintainable if any the the dependencies
shift versions over the time.
