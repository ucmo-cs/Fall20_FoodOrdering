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

    public void createCachedRowSet(ConnectionValues connectionValues, String query) throws SQLException {
        cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowSet.setUsername(connectionValues.userName);
        cachedRowSet.setPassword(connectionValues.password);
        cachedRowSet.setUrl(connectionValues.urlString);
        cachedRowSet.setCommand(query);
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