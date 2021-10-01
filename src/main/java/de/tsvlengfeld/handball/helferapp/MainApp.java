package de.tsvlengfeld.handball.helferapp;

import org.apache.camel.main.Main;

/**
 * Starts a Camel Application.
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.addRouteBuilder(new SpielplanImporterRoute());
        main.run(args);
    }
}
