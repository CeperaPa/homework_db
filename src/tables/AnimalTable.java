package tables;

import animals.AbsAnimal;
import db.MySqlDBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class AnimalTable extends AbsTable {


    public AnimalTable() throws SQLException {
        super("animals");
        columns = new HashMap<>();
        columns.put("id", "int PRIMARY KEY AUTO_INCREMENT");
        columns.put("type", "varchar(10)");
        columns.put("name", "varchar(10)");
        columns.put("age", "int");
        columns.put("weight", "FLOAT");
        columns.put("color", "varchar(10)");
        create();
    }


    public void insert(AbsAnimal animal) throws SQLException {
        MySqlDBConnector db = new MySqlDBConnector();
        String sqlQuery = String.format("INSERT INTO %s (type, name, age, weight, color) VALUES (?, ?, ?, ?, ?)", tableName);
        try (PreparedStatement pstmt = db.getConnection().prepareStatement(sqlQuery)) {
            pstmt.setString(1, String.valueOf(animal.getAnimalType()));
            pstmt.setString(2, animal.getName());
            pstmt.setInt(3, animal.getAge());
            pstmt.setFloat(4, animal.getWeight());
            pstmt.setString(5, animal.getColor());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

}
