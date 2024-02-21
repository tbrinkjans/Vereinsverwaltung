package application;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Pfad zur Datenbank nicht gefunden!");
            return;
        }
        dbHelper db = new dbHelper(args[0]);
    }

}
