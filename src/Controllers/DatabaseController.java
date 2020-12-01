package Controllers;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseController {
    private final static String baseURL="jdbc:mysql://stoves-dev.duckdns.org:50931/%s?serverTimezone=CST";
    private final static String username="project-user";
    private final static String password="_user_password_";

    public static ResultSet readStudentLoginDatabase(String query) throws Exception {

        final String schema = "login";

        Statement stmt = null;
        ResultSet result = null;

        Connection conn = null;

        //System.out.println("===========\n"+query+"\n");

        try {
            String url = String.format(baseURL, schema);
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            stmt.executeQuery(query);
            result = stmt.getResultSet();
        } catch (Exception e) {
            if (conn != null)
                conn.close();
            throw e;
        }
        return result;
    }


    public static void testLogin ()
    {
        final String schema = "login";
        Connection conn = null;
        try {
            String url = String.format(baseURL, schema);
            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Got it!");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
