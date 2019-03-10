# fito-model

## About
Java modeling of the Fitocracy.com REST API JSON entities.  There are at least two Fitocracy APIs ("v1" and v2).  While
they're backed by the same data, they are modeled completely differently.

This module isn't intended for standalone use; it backs **fito-client** and **fito-rest**.

## Build
`mvn clean package`

## Use
Add dependency
```xml
<dependency>
  <groupId>us.marseilles.fitocracy</groupId>
  <artifactId>fito-model</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```