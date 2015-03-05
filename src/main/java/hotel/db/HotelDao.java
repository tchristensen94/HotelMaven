/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.db;

import hotel.web.model.HotelModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Timothy
 */
public class HotelDao {

    private DBAccessor db;
    private String pk = "hotel_id";
    private String table = "hotels";
    private final String[] KEYS = {"hotel_name", "street_address", "city", "state", "postal_code", "notes"};

    public HotelDao(String table, String pk) {
        db = new DB_MySql();
        setTable(table);
        setPK(pk);
    }
    public HotelDao() {
        db = new DB_MySql();
    }
    
    public final void setTable(String table) {
        this.table = table;
    }
    
    public final void setPK(String pk) {
        this.pk = pk;
    }
    
    public final String getPK() {
        return pk;
    }
    
    public final String getTable() {
        return table;
    }
    
    public List findHotels() throws Exception {
        openLocalConn();
        List<HotelModel> hotels = new ArrayList<>();
        List results = db.getAllRecords("hotels", true);
        for(Object result : results) {
            Map<String, Object> obj = (Map)result;
            hotels.add(new HotelModel((int)obj.get(pk), (String)obj.get("hotel_name"), (String)obj.get("street_address"), (String)obj.get("city"), (String)obj.get("state"), (String)obj.get("postal_code"), (String)obj.get("notes")));
        }
        return hotels;
    }
    
    public HotelModel findHotelByID(int id) throws Exception {
        openLocalConn();
        Map<String, Object> result = db.findRecordByID(table, pk, id, true);
        HotelModel hotel;
        hotel = new HotelModel((int)result.get(pk), (String)result.get("name"), (String)result.get("street_address"), (String)result.get("city"), (String)result.get("state"), (String)result.get("postal_code"), (String)result.get("notes"));
        return hotel;
    }
    
    public Map<String, Object> findHotelByName(String name) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        return result;
    }

    public void deleteHotel(int id) throws Exception {
        openLocalConn();
        db.deleteRecord(table, pk, id, true);
    }
    
    public void newHotel(HotelModel hotel) throws Exception {
        openLocalConn();
        System.out.print(hotel.getAddress());
        String[] values = {hotel.getName(), hotel.getAddress(), hotel.getCity(), hotel.getState(), hotel.getZip(), hotel.getNotes()};
        db.insertRecord(table, KEYS, values, true);
    }
    
    public void updateHotel(HotelModel hotel) throws Exception {
        openLocalConn();
        String[] values = {hotel.getName(), hotel.getAddress(), hotel.getCity(), hotel.getState(), hotel.getZip(), hotel.getNotes()};
        if(hotel.getId() != -1) {
            db.updateRecord(table, pk, hotel.getId(), KEYS, values, true);
        } else {
            newHotel(hotel);
        }
    }

    public void setDb(DBAccessor db) {
        this.db = db;
    }

    public DBAccessor getDB() {
        return this.db;
    }

    private void openLocalConn() throws Exception {
        // Each time you perform a new query you must re-open the connection
        db.openConn(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/hotel",
                "root", "");

    }

}
