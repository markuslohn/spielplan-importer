package de.tsvlengfeld.handball.helferapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Represents a single playing schedule provided by CSV file. The layout of the
 * CSV file is based on nuLiga.
 *
 * @author mlohn
 */
@CsvRecord(separator = ";", skipFirstLine = true)
@Data
public class CsvSpielplan {

    @DataField(pos = 1, required = true)
    private String date;

    @DataField(pos = 2, required = true)
    private String time;

    @DataField(pos = 3)
    private String organization;

    @DataField(pos = 4)
    private String season;

    @DataField(pos = 5)
    private String championship;

    @DataField(pos = 6, required = true)
    private String ageClass;

    @DataField(pos = 7)
    private String league;

    @DataField(pos = 8)
    private String subLeague;

    @DataField(pos = 9)
    private int matchDay;

    @DataField(pos = 10, required = true)
    private Long uid;

    @DataField(pos = 11)
    private int locationNr;

    @DataField(pos = 12, required = true)
    private String locationName;

    @DataField(pos = 13)
    private String homeOrganization;

    @DataField(pos = 14)
    private int homeClubNr;

    @DataField(pos = 15)
    private String homeClubName;

    @DataField(pos = 16)
    private String homeClubNameShort;

    @DataField(pos = 17)
    private String homeTeamAgeClass;

    @DataField(pos = 18)
    private int homeTeamNr;

    @DataField(pos = 19, required = true)
    private String homeTeamName;

    @DataField(pos = 20)
    private String visitorClubOrganization;

    @DataField(pos = 21)
    private int visitorClubNr;

    @DataField(pos = 22)
    private String visitorClubName;

    @DataField(pos = 23)
    private String visitorClubNameShort;

    @DataField(pos = 24)
    private String visitorTeamAgeClass;

    @DataField(pos = 25)
    private int visitorTeamNr;

    @DataField(pos = 26, required = true)
    private String visitorTeamName;

    @DataField(pos = 27)
    private int goalsHome;

    @DataField(pos = 28)
    private int goalsVisitor;

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        calculateStartAndEndTime();
        return startTime;
    }

    public Date getEndTime() {
        calculateStartAndEndTime();
        return endTime;
    }

    private void calculateStartAndEndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
        try {
            startTime = formatter.parse(date + " " + time);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        endTime = calendar.getTime();
    }
}
