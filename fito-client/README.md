# fito-client

## About
Java tools for reading and writing Fitocracy.com REST API data as JSON.

## Build
`mvn clean package`

## Use
Add dependency
```xml
<dependency>
  <groupId>us.marseilles.fitocracy</groupId>
  <artifactId>fito-client</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Create a `FitoActivityDao` and start making calls
```java
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.marseilles.fitocracy.client.dao.FitoActivityDao;
import us.marseilles.fitocracy.client.dao.FitoActivityDaoImpl;
import us.marseilles.fitocracy.model.v1.ActivityStub;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

public class ClientSample
{
    public static void main(String[] args)
    {
        String username = args[0];
        String password = args[1];

        FitoActivityDao fitoDao = new FitoActivityDaoImpl();

        AuthenticatedUser authenticatedUser = fitoDao.authenticate(username, password);

        List<ActivityStub> allStubs = fitoDao.getAllActivitiesForUser(authenticatedUser);
        System.out.println(allStubs.size() + " activities found for user");

        Map<String, List<ActivityWorkout>> allActivities = new TreeMap<>();
        for (ActivityStub stub : allStubs)
        {
            System.out.println("Retrieving history for " + stub.getName());
            allActivities.put(stub.getName(), fitoDao.getActivityForUser(stub.getId(), authenticatedUser));
        }
    }
}
```

## TODO
* Scrape other user IDs
* retrieve and model:
    * quests (requires scrape)
    * social data (requires scrape)
    * other users