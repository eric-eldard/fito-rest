# fito-rest

## About
It's no secret that [Fitocracy.com](fitocracy.com) has seen better days.  The new ownership seems far more concerned 
with selling online coaching than with maintaining the original core product.  This isn't a surprise, as the original
creators got out in 2016 after years of struggling to monetize through premium memberships and sparse ads.

The site has seen no new feature development in years.  When the prop system broke in January 2019, and remained broken
for several days, I realized it was time to start doomsday prepping for the eventual dereliction and/or shutdown of the
site. While I've really enjoyed both the community and the gamification, the thing I can't lose is my fitness log.

There are (at least) two Fitocracy REST APIs, both of which are currently in use on their website.  The v1 API seems to
be geared towards aggregating a user's activities across their time on Fito, like on the site's Performance page,
whereas the v2 API can be used to pull back individual workouts for a user.  While they're backed by the same data, they
are modeled completely differently.

This project was thrown together to pull a Fitocracy user's full exercise log from a combination of Fito's REST API (v1
and v2) and some page scraping, both for posterity and for playing around with the data.  Fork away—I'd love to
collaborate and see what you do with it!

## Build
`mvn clean install`

## Use
Add dependency
```xml
<dependency>
  <groupId>us.marseilles.fitocracy</groupId>
  <artifactId>fito-rest</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Create a `FitoActivityDao` and start making calls
```java
import java.util.List;

import us.marseilles.fitocracy.rest.dao.FitoActivityDao;
import us.marseilles.fitocracy.rest.dao.FitoActivityDaoImpl;
import us.marseilles.fitocracy.rest.domain.v1.ActivityStub;

public class Sample
{
    public static void main(String[] args)
    {
        FitoActivityDao fitoDao = new FitoActivityDaoImpl();

        String sessionId = "{the value of your valid sessionid cookie}";
        String userId = fitoDao.getOwnUserId(sessionId);

        List<ActivityStub> allStubs = fitoDao.getAllActivitiesForUser(userId, sessionId);
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