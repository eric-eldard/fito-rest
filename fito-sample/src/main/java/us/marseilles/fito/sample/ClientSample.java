package us.marseilles.fito.sample;

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