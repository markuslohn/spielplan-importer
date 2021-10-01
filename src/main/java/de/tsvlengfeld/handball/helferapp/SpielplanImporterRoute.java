package de.tsvlengfeld.handball.helferapp;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;

/**
 *
 * A Camel route to read CSV files from nuLiga (BHV) from a directory. Then it
 * will transform the CSV data to update the playing schedule within an Oracle
 * Apex database using a REST service.
 *
 * @author mlohn
 */
public class SpielplanImporterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        DataFormat csvFormat = new BindyCsvDataFormat(CsvSpielplan.class);
        from("file:src/test/resources/?fileName=spielplan.csv&charset=ISO-8859-1")
                .log(LoggingLevel.INFO, "Received CSV ${file:name} file for processing.")
                .removeProperty(Exchange.CHARSET_NAME)
                .unmarshal(csvFormat)
                .bean(new Csv2DbSpielplanTransformer(), "map")
                .split(body())
                .setHeader("begegnungNr", simple("${body.begegnungNr}"))
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}")
                .toD("https://servername:port/${header.begegnungNr}?httpMethod=PUT");
    }
}
