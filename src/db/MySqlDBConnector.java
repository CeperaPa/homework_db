package db;

import config.DBConfigurator;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySqlDBConnector implements IDBConnector {

    private DBConfigurator dbConfigurator = new DBConfigurator();

    private static Statement statement = null;
    private static Connection connection = null;
    {
        connect();
    }

    private void connect() {
        try {
            Properties configuration = dbConfigurator.getDBConfig();
            if(connection != null) {
                connection = DriverManager.getConnection(
                        String.format("%s/%s", configuration.getProperty("db_url"), configuration.getProperty("db_name")),
                        configuration.getProperty("username"),
                        configuration.getProperty("password")
                );
            }
            if(statement != null) {
                statement = connection.createStatement();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    @Override
    public boolean execute(String sql) throws SQLException {
        return statement.execute(sql);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }
    @Override
    public void close() throws SQLException {
        if(statement != null) {
            statement.close();
        }

        if(connection != null) {
            connection.close();
        }
    }
}
