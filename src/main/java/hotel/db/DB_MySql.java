/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tim
 */
public class DB_MySql implements DBAccessor {

    private Connection conn;

    public DB_MySql() {

    }

    /**
     *
     * @param driver
     * @param url
     * @param user
     * @param pass
     */
    @Override
    public void openConn(String driver, String url, String user, String pass) throws SQLException, ClassNotFoundException {
        if (url == null) {
            throw new IllegalArgumentException("Url must not be null");
        }
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, pass);
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

    @Override
    public List<Map<String, Object>> getAllRecords(String table, boolean keepAlive) throws Exception {
        List<Map<String, Object>> records = new ArrayList<>();
        Statement s = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        String statement = "SELECT * FROM " + table;
        try {
            s = conn.createStatement();
            rs = s.executeQuery(statement);
            metaData = rs.getMetaData();
            int fields = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> temp = new HashMap<>();
                for (int i = 1; i <= fields; i++) {
                    temp.put(metaData.getColumnName(i), rs.getObject(i));
                }

                records.add(temp);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                s.close();
                if (!keepAlive) {
                    conn.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return records;
    }

    /**
     * Inserts a record of strings into specified table
     *
     * @param table table to do the insert on
     * @param keys the keys
     * @param values the values
     * @param keepAlive whether or not to keep alive
     * @throws Exception
     */
    @Override
    public void insertRecord(String table, String[] keys, String[] values, boolean keepAlive) throws Exception {
        PreparedStatement ps = null;
        String insertString = "INSERT INTO " + table + "(";

        for (int i = 1; i <= keys.length; i++) {
            if (i == keys.length) {
                insertString += keys[i-1];
            } else {
                insertString += keys[i-1] + ", ";
            }
        }
        insertString += ") VALUES (";
        for (int i = 1; i <= keys.length; i++) {
            if (i == keys.length) {
                insertString += "?)";
            } else {
                insertString += "?,";
            }
        }
        System.out.println(insertString);
        ps = conn.prepareStatement(insertString);
        for (int i = 1; i <= keys.length; i++) {
            System.out.println(values[i-1]);
            ps.setString(i, values[i-1]);
        }
        ps.executeUpdate();
        ps.close();
        if (!keepAlive) {
            conn.close();
        }
    }

    @Override
    public void updateRecord(String table, String pk, int id, String[] keys, String[] values, boolean keepAlive) throws Exception {
        PreparedStatement ps = null;
        String insertString = "UPDATE " + table + " SET ";

        for (int i = 1; i <= keys.length; i++) {
            if (i == keys.length) {
                insertString += keys[i-1] + " = ?";
            } else {
                insertString += keys[i-1] + " = ?, ";
            }
        }
        
        insertString += " WHERE " + pk + " = " + id;
        ps = conn.prepareStatement(insertString);
        for (int i = 1; i <= keys.length; i++) {
            ps.setString(i, values[i-1]);
        }
        ps.executeUpdate();
        ps.close();
        if (!keepAlive) {
            conn.close();
        }
    }

    @Override
    public void deleteRecord(String table, String pk, int id, boolean keepAlive) throws Exception {
        String sql = "DELETE FROM " + table + " WHERE " + pk + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            
            ps.executeUpdate();
        }
        if (!keepAlive) {
            conn.close();
        }
    }

    @Override
    public Map<String, Object> findRecordByID(String table, String pk, int id, boolean keepAlive) throws Exception {
        String sql = "SELECT * FROM " + table + " WHERE " + pk + " = ?";

        Map<String, Object> result;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int fields = metaData.getColumnCount();
            result = new HashMap<>();
            while (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    result.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
        }
        if (!keepAlive) {
            conn.close();
        }
        return result;
    }
    
    /**
     * Finds the first record that matches the key/value provided
     * @param table the table to select from
     * @param key the column name
     * @param value the column value
     * @param keepAlive close connection once done
     * @return a map of the record
     * @throws Exception 
     */
    @Override
    public Map<String, Object> findRecordByCol(String table, String key, String value, boolean keepAlive) throws Exception {
        String sql = "SELECT * FROM " + table + " WHERE " + key + " = ? LIMIT 1";

        Map<String, Object> result;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int fields = metaData.getColumnCount();
            result = new HashMap<>();
            while (rs.next()) {
                for (int i = 1; i <= fields; i++) {
                    result.put(metaData.getColumnName(i), rs.getObject(i));
                }
            }
        }
        if (!keepAlive) {
            conn.close();
        }
        return result;
    }
}
