/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Timothy
 */
public interface DBAccessor {

    void close() throws SQLException;

    void deleteRecord(String table, String pk, int id, boolean keepAlive) throws Exception;

    List<Map<String, Object>> getAllRecords(String table, boolean keepAlive) throws Exception;

    /**
     * Inserts a record of strings into specified table
     * @param table table to do the insert on
     * @param keys the keys
     * @param values the values
     * @param keepAlive whether or not to keep alive
     * @throws Exception
     */
    void insertRecord(String table, String[] keys, String[] values, boolean keepAlive) throws Exception;

    /**
     *
     * @param driver
     * @param url
     * @param user
     * @param pass
     */
    void openConn(String driver, String url, String user, String pass) throws SQLException, ClassNotFoundException;

    void updateRecord(String table, String pk, int id, String[] keys, String[] values, boolean keepAlive) throws Exception;
   
    Map<String, Object> findRecordByID(String table, String pk, int id, boolean keepAlive) throws Exception;
    
    Map<String, Object> findRecordByCol(String table, String key, String value, boolean keepAlive) throws Exception;
}
