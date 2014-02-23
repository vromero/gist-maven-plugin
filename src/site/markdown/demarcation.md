Demarcation
=================

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
