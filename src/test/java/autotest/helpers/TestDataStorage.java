package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestDataStorage {

    private static String DBPATH = "/TestData.db";

    private static String SQLSTMTEMPLATE = "SELECT u.* FROM users AS u WHERE id = ?";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws Exception {
        try {
            String basedir  = System.getProperty("project.basedir");

            if (basedir == null) {
                basedir = ".";
            }

            String testDataPath = new java.io.File(basedir + DBPATH).getCanonicalPath();
            File f = new File(testDataPath);

            if (f.exists() && !f.isDirectory()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + testDataPath);
            } else {
                System.out.println("File not found "+testDataPath);
                throw new FileNotFoundException(testDataPath);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            throw e;
        }
    }

    public void dBClose() throws SQLException {
        connection.close();
    }

    //TODO: to extend
    public void execSQL(String stmt) throws SQLException {
        try {
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            statement.execute(stmt);
            statement.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public TestDataEntry getTestDataById(String id) throws SQLException, ParseException {
        TestDataEntry testDataEntry = new TestDataEntry();
        PreparedStatement statement = connection.prepareStatement(SQLSTMTEMPLATE);
        statement.setString(1, id);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            testDataEntry.login = rs.getString("login");
            testDataEntry.password = rs.getString("password");
            testDataEntry.firstname = rs.getString("firstname");
            testDataEntry.lastname = rs.getString("lastname");
        }

        return testDataEntry;
    }

    public List<String> getAllIds() throws SQLException {
        List<String> result = new ArrayList<String>();
        String stm = "select distinct id from users";
        PreparedStatement statement = connection.prepareStatement(stm);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String id = rs.getString("id");
            result.add(id);
        }
        return result;
    }

}


