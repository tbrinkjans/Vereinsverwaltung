package application.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final boolean DUMMY = true;

    private static final String CREATE_PATH = "application/database/create.sql";
    private static final String DUMMY_DATA_PATH = "application/database/dummyData.sql";

    public static void setupDatabase(DatabaseContext context) {
        try {
            context.open();

            String sql = 
                "SELECT COUNT(*) AS \"tableCount\" "+
                "FROM \"sqlite_master\" "+
                "WHERE \"type\" = 'table';";

            ResultSet rs = context.read(sql);
            if (rs.next() && rs.getInt("tableCount") == 0) {
                executeSQLFile(context, CREATE_PATH);
                if (DUMMY) {
                    executeSQLFile(context, DUMMY_DATA_PATH);
                }
            }

            context.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void executeSQLFile(DatabaseContext context, String path) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DatabaseHelper.class.getClassLoader().getResourceAsStream(path)))) {
            String[] sqls = br.lines()
                .map(line -> line.trim())
                .filter(line -> !line.startsWith("--"))
                .reduce("", (a, b) -> a + " " + b)
                .split(";");

            for (String sql : sqls) {
                context.write(sql);
            }
        }
    }

}
