package us.marseilles.fitocracy.backup;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import us.marseilles.fitocracy.client.dao.FitoActivityDao;
import us.marseilles.fitocracy.client.dao.FitoActivityDaoImpl;
import us.marseilles.fitocracy.file.json.FitoJsonWriterImpl;
import us.marseilles.fitocracy.model.ModelUtils;
import us.marseilles.fitocracy.model.v1.ActivityStub;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

/**
 * Write user's entire activity history to disk as json
 */
public class FileBackup
{
    public static void main(String[] args)
    {
        String sessionId = args[0];   // Value from authenticated user's "sessionid" cookie
        String destination = args[1]; // Output directory for file

        FitoActivityDao activityDao = new FitoActivityDaoImpl();
        
        String userId = activityDao.getOwnUserId(sessionId);

        // Get all activity types the user has logged
        List<ActivityStub> allActivityStubs = activityDao.getAllActivitiesForUser(userId, sessionId);
        System.out.println(allActivityStubs.size() + " activities found for user");

        Map<String, List<ActivityWorkout>> allActivities = new TreeMap<>();

        // Get the complete history of each activity type
        for (ActivityStub stub : allActivityStubs)
        {
            System.out.println("Retrieving history for " + stub.getName());
            List<ActivityWorkout> activityHistory = activityDao.getActivityForUser(stub.getId(), sessionId);
            allActivities.put(stub.getName(), activityHistory);
        }

        ModelUtils.nullOutBlanksAndZeros(allActivities);

        // Write file to disk; exclude keys with null values
        new FitoJsonWriterImpl().writeActivityHistory(allActivities.values(), destination, false, true);
    }
}