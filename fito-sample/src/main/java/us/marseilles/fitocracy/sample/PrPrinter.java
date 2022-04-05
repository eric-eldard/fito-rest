package us.marseilles.fitocracy.sample;

import org.apache.commons.lang3.StringUtils;
import us.marseilles.fitocracy.file.FitoFileReader;
import us.marseilles.fitocracy.file.json.FitoJsonReaderImpl;
import us.marseilles.fitocracy.model.v1.ActivitySet;
import us.marseilles.fitocracy.model.v1.ActivityWorkout;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Print to screen dates and amounts for PRs of each activity type from a fito-backup file
 */
public class PrPrinter
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

            // Print date and effort amounts for any record labeled as a PR
            activity
                .stream()
                .map(ActivityWorkout::getActivitySets)
                .flatMap(Collection::stream)
                .filter(ActivitySet::isPr)
                .sorted(Comparator.comparing(set -> set.getActionTime().getValue()))
                .forEachOrdered(set ->
                {
                    String date = set.getActionDate().toString();
                    String record = "";
                    String points = "(" + set.getPoints() + " points)";

                    // There are boolean values available for these on the Activity, however a true value doesn't
                    // indicate the presence of an effort string because some metrics are optional for logging
                    // (for example, Rowing has both time and distance, but you only have to log a value for one)
                    boolean hasFirstEffort = StringUtils.isNotBlank(set.getEffort0ImperialString());
                    boolean hasSecondEffort = StringUtils.isNotBlank(set.getEffort1ImperialString());

                    if (hasFirstEffort)
                    {
                        record += set.getEffort0ImperialString();
                    }
                    if (hasFirstEffort && hasSecondEffort)
                    {   // Yes, believe it or not, some activities have a second effort metric and not a first
                        record += " x ";
                    }
                    if (hasSecondEffort)
                    {
                        record += set.getEffort1ImperialString();
                    }
                    System.out.println("\t" + date + "\t\t" + record + "\t\t" + points);
                });
        }
    }
}