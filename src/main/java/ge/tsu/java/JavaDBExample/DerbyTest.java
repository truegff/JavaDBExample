package ge.tsu.java.JavaDBExample;

import java.sql.*;

public class DerbyTest {
    private Connection connect;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DerbyTest(Connection connection) throws Exception {
        try {
            if (connection == null) {
                connection = Database.getConection();
                connect = connection;
            }

            if (!table_exists(connection, "student")) createTable(connection);

//            PreparedStatement statement = connect.prepareStatement("SELECT * from student");
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                int userid = resultSet.getInt("ID");
//                String firstname = resultSet.getString("FIRSTNAME");
//                String lastname = resultSet.getString("LASTNAME");
//                String email = resultSet.getString("EMAIL");
//                System.out.println("ID: " + userid);
//                System.out.println("FIRSTNAME: " + firstname);
//                System.out.println("LASTNAME: " + lastname);
//                System.out.println("EMAIL: " + email);
//                System.out.println();
//            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    private void createTable(Connection connect) throws SQLException {
        Statement stm = connect.createStatement();
        stm.execute("CREATE TABLE APP.student\n" +
                "(\n" +
                "  ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
                "  FIRSTNAME VARCHAR(50) NOT NULL,\n" +
                "  LASTNAME VARCHAR(50) NOT NULL,\n" +
                "  EMAIL VARCHAR(120) NOT NULL,\n" +
                "  UNIQUE (ID)\n" +
                ")");
        stm.close();
    }

    private boolean table_exists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);

        boolean exists = false;
        while (rs.next()) {
            if (rs.getString(3).equalsIgnoreCase(tableName)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        DerbyTest dao = new DerbyTest(null);
    }

}