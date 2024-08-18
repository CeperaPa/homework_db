package tables;

import db.IDBConnector;
import db.MySqlDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class AbsTable {

    public AbsTable(String tableName) {
        this.tableName = tableName;
    }

    protected Map<String, String> columns;
    private IDBConnector idbConnector = new MySqlDBConnector();
    String tableName = "";

    public void create() throws SQLException {
        if(!isTableExist()) {
            String sqlRequest = String.format("create table %s (%s)", tableName, String.join(", ", (Iterable<? extends CharSequence>) columns));
            idbConnector.execute(sqlRequest);
        }
    }

    private boolean isTableExist() throws SQLException {
        String sqlRequest = String.format("show table %s", tableName);
        ResultSet resultSet = idbConnector.executeQuery(sqlRequest);

        while(resultSet.next()) {
            if(resultSet.getString(1).equals(tableName)) {
                return true;
            }
        }
        return false;
    }
    //public ResultSet list (String animalType) { }

}
