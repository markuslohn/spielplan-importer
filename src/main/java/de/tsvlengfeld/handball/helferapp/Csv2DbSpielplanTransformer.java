package de.tsvlengfeld.handball.helferapp;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This mapper transform a playing schedule provided by nuLiga (BHV) into a JSON
 * format.
 *
 * @author mlohn
 */
public class Csv2DbSpielplanTransformer {

    private final Logger logger = LoggerFactory.getLogger(Csv2DbSpielplanTransformer.class);

    public Csv2DbSpielplanTransformer() {
    }

    public List<DbSpielplan> map(List<CsvSpielplan> csvSchedules) throws Exception {
        List<DbSpielplan> dbSchedules = new ArrayList<>();

        for (CsvSpielplan currentSchedule : csvSchedules) {
            logger.info("Transforms schedule number: {}: ", currentSchedule.getUid().toString());
            DbSpielplan currentScheduleEvent = buildEvent(currentSchedule);
            dbSchedules.add(currentScheduleEvent);
        }
        return dbSchedules;
    }

    private DbSpielplan buildEvent(CsvSpielplan schedule) throws Exception {
        return DbSpielplan.builder()
                .begegnungNr(schedule.getUid().toString())
                .termin(schedule.getDate())
                .uhrzeit(schedule.getTime())
                .altersklasse(schedule.getAgeClass())
                .liga(schedule.getLeague())
                .staffel(schedule.getSubLeague())
                .hallenNr(schedule.getLocationNr())
                .heimvereinNr(schedule.getHomeClubNr())
                .heimvereinName(schedule.getHomeClubName())
                .gastvereinNr(schedule.getVisitorClubNr())
                .gastvereinName(schedule.getVisitorClubName())
                .toreHeim(schedule.getGoalsHome())
                .toreGast(schedule.getGoalsVisitor()).build();
    }
}
