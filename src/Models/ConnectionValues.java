package Models;

import java.sql.SQLException;

public class ConnectionValues {

    public String dbName;
    public String userName;
    public String password;
    public String urlString;

    public ConnectionValues(int db) throws SQLException {
        getConnectionSettings(db);
    }

    // Establish the connection settings
    public void getConnectionSettings(int db) throws SQLException {

        String serverURL = "jdbc:mysql://stoves-dev.duckdns.org:50931";
        String serverTimeZone = "?serverTimezone=CST";
        userName="table_editor";
        password="!sleekPanda!";

        String serverDbSchema;
        switch (db) {
            case 1: serverDbSchema = "/login";      break;
            case 2: serverDbSchema = "/restaurant"; break;
            case 3: serverDbSchema = "/student";    break;
            default: serverDbSchema = "InvalidURL"; break;
        }
        urlString= serverURL + serverDbSchema + serverTimeZone;
    }

//    public PreparedStatement createPreparedStatement(ConnectionValues connection, String query){
//        PreparedStatement preparedStatement=null;
//
//        return preparedStatement;
//    }

    public String getUserName() { return userName; }

    public String getPassword() { return password; }

    public String getUrlString() { return urlString; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setPassword(String password) { this.password = password; }

    public void setUrlString(String urlString) { this.urlString = urlString; }
}
