package application;

import application.database.DatabaseContext;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Pfad zur Datenbank nicht gefunden!");
            return;
        }

        String url = args[0];
        DatabaseContext db = new DatabaseContext(url);
    }

}
