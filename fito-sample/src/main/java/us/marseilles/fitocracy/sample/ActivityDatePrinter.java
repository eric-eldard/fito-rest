package us.marseilles.fitocracy.sample;

import com.google.api.client.util.DateTime;
import us.marseilles.fitocracy.file.FitoFileReader;
import us.marseilles.fitocracy.file.json.FitoJsonReaderImpl;
import us.marseilles.fitocracy.model.v1.ActivitySet;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Print to screen all dates of each activity type from a fito-backup file
 */
public class ActivityDatePrinter
{
    public static void main(String[] args)
    {
        String source = args[0]; // Location of a fito-backup file
        FitoFileReader fitoJsonReader = new FitoJsonReaderImpl();
        FitoFileReader.ActivityHistory activityHistory = fitoJsonReader.readActivityHistory(source);

        // Iterate each activity group
        for (List<ActivityWorkout> activity : activityHistory)
        {
            // Print the name of the first activity in group (all activities in the group have same name)
            System.out.println(activity.get(0).getActivitySets().get(0).getActivity().getName());

            // Aggregate the dates the activity was logged, collapsing dupes for days it was logged multiple times
            AtomicInteger index = new AtomicInteger();
            activity
                .stream()
                .map(ActivityWorkout::getActivitySets)
                .flatMap(Collection::stream)
                .map(ActivitySet::getActionDate)
                .distinct()
                .map(DateTime::toString)
                .forEachOrdered(dateStr ->
                {
                    System.out.print("\t" + dateStr);
                    if (index.incrementAndGet() % 10 == 0) { System.out.println(); }
                });

            if (index.get() % 10 != 0) { System.out.println(); }
        }
    }
}