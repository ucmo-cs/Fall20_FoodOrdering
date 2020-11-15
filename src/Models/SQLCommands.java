package Models;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLCommands {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private CachedRowSet cachedRowSet;

    public void createConnection(ConnectionValues connectionValues) throws SQLException {
        connection = DriverManager.getConnection(connectionValues.urlString, connectionValues.userName, connectionValues.password);
    }

    private final String urlRestaurant="jdbc:mysql://stoves-dev.duckdns.org:50931/restaurant?serverTimezone=CST";
    private final String urlStudent="jdbc:mysql://stoves-dev.duckdns.org:50931/student?serverTimezone=CST";
    private final String urlLogin="jdbc:mysql://stoves-dev.duckdns.org:50931/login?serverTimezone=CST";
    private final String dbDriver="com.mysql.jdbc.Driver";
    private final String username="table_editor";
    private final String password="!sleekPanda!";
    public void createCachedRowSet(ConnectionValues connectionValues, String query) throws SQLException {
        cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowSet.setUsername(connectionValues.userName);
        cachedRowSet.setPassword(connectionValues.password);
        cachedRowSet.setUrl(connectionValues.urlString);
        cachedRowSet.setCommand(query);
    }

    // NOTICE!
    // setConnection and readRestaurantDataBase are still in development.
    // Will replace both of the other readDB functions when completed.
    public CachedRowSet setConnection(int db) throws SQLException {
        String url = null;
        switch (db) {
            case 1:
                url = urlRestaurant;
                break;
            case 2:
                url = urlStudent;
                break;
            case 3:
                url = urlLogin;
            default:
                url = "Invalid URL";
                break;
        }
        CachedRowSet cachedRowset = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowset.setUrl(url);
        cachedRowset.setUsername(username);
        cachedRowset.setPassword(password);
        return cachedRowset;
    }
    public void createPreparedStatement(CachedRowSet cachedRowSet) throws SQLException {
        preparedStatement = connection.prepareStatement(cachedRowSet.getCommand());
    }

    public CachedRowSet readDataBase(int dbID, String query) throws Exception {
        ConnectionValues connectionValues = new ConnectionValues(dbID);
        createConnection(connectionValues);
        createCachedRowSet(connectionValues, query);
        createPreparedStatement(cachedRowSet);

        try {
            cachedRowSet.execute();
            return cachedRowSet;
        } catch (Exception e) {
            System.err.print("ERROR!\nFunction: readDataBase\nClass: SQLCommands\n");
            System.err.print(e);
        } finally {
            connection.close();
        }
        return cachedRowSet;
    }
}