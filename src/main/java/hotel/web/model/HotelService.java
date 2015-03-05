/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

import hotel.db.HotelDao;
import java.util.List;

/**
 *
 * @author tim
 */
public class HotelService {
    private final HotelDao dao;
    
    public HotelService() {
        dao = new HotelDao();
    }
    
    public void saveHotel(HotelModel hotel) throws Exception {
        System.out.println("update!");
        dao.updateHotel(hotel);
    }
    
    public void deleteHotel(HotelModel hotel) throws Exception {
        dao.deleteHotel(hotel.getId());
    }
    
    public List<HotelModel> getHotels() throws Exception{
        return dao.findHotels();
    }
}
