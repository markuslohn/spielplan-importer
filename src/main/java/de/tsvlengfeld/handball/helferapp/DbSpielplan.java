package de.tsvlengfeld.handball.helferapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DbSpielplan {

    @JsonProperty("BegegnungNr")
    private String begegnungNr;
    @JsonProperty("Termin")
    private String termin;
    @JsonProperty("Uhrzeit")
    private String uhrzeit;
    @JsonProperty("Dauer")
    private int dauer;
    @JsonProperty("Altersklasse")
    private String altersklasse;
    @JsonProperty("Liga")
    private String liga;
    @JsonProperty("Staffel")
    private String staffel;
    @JsonProperty("Spieltag")
    private int spieltag;
    @JsonProperty("HallenNr")
    private int hallenNr;
    @JsonProperty("HeimvereinNr")
    private int heimvereinNr;
    @JsonProperty("HeimvereinName")
    private String heimvereinName;
    @JsonProperty("GastvereinNr")
    private int gastvereinNr;
    @JsonProperty("GastvereinName")
    private String gastvereinName;
    @JsonProperty("ToreHeim")
    private int toreHeim;
    @JsonProperty("ToreGast")
    private int toreGast;

}
