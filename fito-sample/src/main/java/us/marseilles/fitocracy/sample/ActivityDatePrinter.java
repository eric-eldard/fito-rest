package us.marseilles.fitocracy.sample;

import us.marseilles.fitocracy.file.FitoFileReader;
import us.marseilles.fitocracy.file.json.FitoJsonReaderImpl;
import us.marseilles.fitocracy.model.v1.ActivitySet;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Print to screen all dates of each activity type from a fito-backup file
 */
public class ActivityDatePrinter
{
    public static void main(String[] args)
    {
        String source = args[0]; // Location of a fito-backup file
        FitoFileReader fitoJsonReader = new FitoJsonReaderImpl();
        FitoFileReader.ActivityHistory activityWorkouts = fitoJsonReader.readActivityHistory(source);

        // Iterate each activity group
        for (List<ActivityWorkout> activity : activityWorkouts)
        {
            // Print the name of the first activity in group (all activities in the group have same name)
            System.out.println(activity.get(0).getActivitySets().get(0).getActivity().getName());

            // Aggregate the dates the activity was logged, collapsing dupes for days it was logged multiple times
            SortedSet<String> activityDates = activity
                .stream()
                .map(ActivityWorkout::getActivitySets)
                .flatMap(Collection::stream)
                .map(ActivitySet::getActionDate)
                .distinct()
                .map(Objects::toString)
                .collect(Collectors.toCollection(TreeSet::new));

            Iterator<String> iterator = activityDates.iterator();
            int i = 0;

            // Print dates activity was logged, up to 10 dates per line
            while (iterator.hasNext())
            {
                System.out.print('\t' + iterator.next());
                i++;
                if (i % 10 == 0 || !iterator.hasNext())
                {
                    System.out.println();
                }
            }
        }
    }
}