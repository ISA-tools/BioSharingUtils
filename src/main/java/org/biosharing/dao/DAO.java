package org.biosharing.dao;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:48
 */
public abstract class DAO {

    static Properties dbProperties;

    static {
        dbProperties = new Properties();
        try {
            dbProperties.load(DAO.class.getResourceAsStream("/db/dbConnection.properties"));
        } catch (IOException e) {
            System.err.println("DB Properties failed to load.");
        }
    }

    private Connection connection;

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName(dbProperties.getProperty("db.class"));
        return DriverManager.getConnection(
                dbProperties.getProperty("db.url"),
                dbProperties.getProperty("db.username"),
                dbProperties.getProperty("db.password"));
    }

    protected ResultSet executeQuery(String query) {
        try {
            connection = getConnection();

            Statement stmt = connection.createStatement();

            return stmt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update helper
     * @param query - query to execute
     * @return the number of affected rows.
     */
    protected int executeUpdate(String query) {
        int rowsAffected= 0;
        try {
            connection = getConnection();

            Statement stmt = connection.createStatement();

            System.out.println(query);
            rowsAffected = stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }

    protected boolean executeInsert(String query) {
        try {
            connection = getConnection();

            Statement stmt = connection.createStatement();

            return stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
