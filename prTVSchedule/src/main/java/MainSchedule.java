import java.util.SortedSet;

import tvschedule.*;

public class MainSchedule {

    public static void main(String[] args) {
        TVScheduleCommercials schedule = new TVScheduleCommercials();

        schedule.add("ITV", new TVShow("TENABLE", new Hour(15, 0), 55));
        schedule.add("ITV", new TVShow("TIPPING POINT", new Hour(16, 0), 55));
        schedule.add("BBC One", new TVShow("BARGAIN HUNT", new Hour(12, 15), 40));
        schedule.add("BBC One", new TVShow("NEWS AT ONE", new Hour(13, 0), 25));
        schedule.add("BBC One", new TVShow("DOCTORS", new Hour(13, 45), 25));

        ShortShows shortShowsSelector = new ShortShows(40);

        SortedSet<TVShow> recommended = schedule.advice(shortShowsSelector);

        System.out.println("Programas con duración menor a 40 minutos:");
        for (TVShow show : recommended) {
            System.out.println(show);
        }
    }
}
