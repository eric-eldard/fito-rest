# fito-backup

## About
A fito-rest application for writing a user's entire activity history to disk as json

## Build
`mvn clean package`

## Use
```
java -jar fito-backup-1.0-SNAPSHOT.jar yourValidFitoSessionId /tmp
```

See **fito-client** to learn how to obtain a valid session id