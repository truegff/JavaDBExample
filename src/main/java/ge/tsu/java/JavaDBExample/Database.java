package ge.tsu.java.JavaDBExample;

import ge.tsu.java.JavaDBExample.model.MyTableModel;
import ge.tsu.java.JavaDBExample.model.Student;

import java.sql.*;

public class Database {

    private static final String DATABASE_NAME = "./db/students";
    private static final String DERBY_EMBEDDED_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DERBY_PROTOCOL = "jdbc:derby:";
    private static final String CREATE_ME = ";create=true";

    public static Connection getConection() throws Exception {
        Class.forName(DERBY_EMBEDDED_DRIVER).newInstance();
        return DriverManager.getConnection(DERBY_PROTOCOL + DATABASE_NAME + CREATE_ME);
    }

    public static void loadData(MyTableModel model) throws Exception {
        Connection c = getConection();
        PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM student");
        ResultSet resultSet = preparedStatement.executeQuery();
        model.readResultSet(resultSet);
        c.close();
    }

    public static boolean addRecord(Student s) throws SQLException {
        Connection c = null;
        boolean retVal = true;
        try {
            c = getConection();
            PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO student (FIRSTNAME, LASTNAME, EMAIL) Values (?, ?, ?)");
            preparedStatement.setString(1, s.getFirstname());
            preparedStatement.setString(2, s.getLastname());
            preparedStatement.setString(3, s.getEmail());
            preparedStatement.execute();
        } catch (Exception e) {
            retVal = false;
        }
        c.close();
        return retVal;
    }

    public static void deleteRecord(Integer i) throws SQLException {
        Connection c = null;
        try {
            c = getConection();
            PreparedStatement preparedStatement = c.prepareStatement("DELETE FROM student WHERE ID=?");
            preparedStatement.setString(1, i.toString());
            preparedStatement.execute();
        } catch (Exception e) { }
        finally {
            c.close();
        }
    }

    public static boolean updateRecord(Student student) throws SQLException {
        Connection c = null;
        boolean retVal = true;
        try {
            c = getConection();
            PreparedStatement preparedStatement = c.prepareStatement("UPDATE student SET FIRSTNAME=?, LASTNAME=?, EMAIL=? WHERE ID=?");
            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.execute();
        } catch (Exception e) {
            retVal = false;
        }
        c.close();
        return retVal;
    }
}
