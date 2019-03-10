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
        FitoActivityDao fitoDao = new FitoActivityDaoImpl();

        String sessionId = "{the value of your valid sessionid cookie}";
        String userId = fitoDao.getOwnUserId(sessionId);

        List<ActivityStub> allStubs = fitoDao.getAllActivitiesForUser(userId, sessionId);
        System.out.println(allStubs.size() + " activities found for user");

        Map<String, List<ActivityWorkout>> allActivities = new TreeMap<>();
        for (ActivityStub stub : allStubs)
        {
            System.out.println("Retrieving history for " + stub.getName());
            allActivities.put(stub.getName(), fitoDao.getActivityForUser(stub.getId(), sessionId));
        }
    }
}
```

### sessionid
All requests require a Fitocracy *sessionid*, which can be found in an HttpOnly cookie after logging into the site.
This cookie is valid for two weeks(!), but the token is invalidated when you log out.  Identifying which user to pull
data for is accomplished through a bizarre mix of username, this sessionid token, and a user ID.  Calls which don't 
take a username or user ID use your sessionid to identify who to pull data for, meaning those calls will always be
limited to your own profile. The user ID is found in the header *X-Fitocracy-User*, which is returned on HTML page
requests after successful login.  A convenience method to obtain your ID is provided via
`FitoActivityDao#getOwnUserId(sessionId)`. This currently limits all calls to your own user, but user IDs can be
scrapped from profile pages (see TODOs).

Fitocracy also provides a CSRF token, but I've yet to encounter a call where it's needed. It's set by a response
header on HTML page requests into the non-HttpOnly cookie *csrftoken*.

## TODO
* Scrape other user IDs
* retrieve and model:
    * quests (requires scrape)
    * social data (requires scrape)