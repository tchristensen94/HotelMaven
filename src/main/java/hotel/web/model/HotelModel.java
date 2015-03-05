/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.model;

/**
 *
 * @author tim
 */
public class HotelModel {
    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String notes;

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final String getAddress() {
        return address;
    }

    public final void setAddress(String address) {
        this.address = address;
    }

    public final String getCity() {
        return city;
    }

    public final void setCity(String city) {
        this.city = city;
    }

    public final String getState() {
        return state;
    }

    public final void setState(String state) {
        this.state = state;
    }

    public final String getZip() {
        return zip;
    }

    public final void setZip(String zip) {
        this.zip = zip;
    }

    public final String getNotes() {
        return notes;
    }

    public final void setNotes(String notes) {
        this.notes = notes;
    }
    
    public HotelModel(int id, String name, String address, String city, String state, String zip, String notes) {
        setId(id);
        setName(name);
        setAddress(address);
        setCity(city);
        setState(state);
        setZip(zip);
        setNotes(notes);
    }
    public HotelModel(String name, String address, String city, String state, String zip, String notes) {
        setId(-1);
        setName(name);
        setAddress(address);
        setCity(city);
        setState(state);
        setZip(zip);
        setNotes(notes);
    }
    
    
}
