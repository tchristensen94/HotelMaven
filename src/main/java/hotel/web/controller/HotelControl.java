/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.web.controller;

import hotel.web.model.HotelModel;
import hotel.web.model.HotelService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tim
 */
public class HotelControl extends HttpServlet {

    private final String RESULT = "/hotel/index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HotelService hs = new HotelService();
        List<HotelModel> hotels = new ArrayList<>();
        try {
            hotels = hs.getHotels();
        } catch (Exception e) {
            System.out.println(e);
        }

        String option = request.getParameter("option");
        String name = request.getParameter("hotelName");
        String address = request.getParameter("hotelAddress");
        String city = request.getParameter("hotelCity");
        String state = request.getParameter("hotelState");
        String zipcode = request.getParameter("hotelZip");
        String hotelId = request.getParameter("hotelID");
        String notes = request.getParameter("hotelNotes");

        String citySearch = request.getParameter("searchByCity");
        String stateSearch = request.getParameter("searchByState");

        request.setAttribute("citySearch", citySearch);
        request.setAttribute("stateSearch", stateSearch);

        try {
            if (option != null) {
                if (option.equals("create")) {
                    hs.saveHotel(new HotelModel(name, address, city, state, zipcode, notes));
                } else if (option.equals("update")) {
                    hs.saveHotel(new HotelModel(Integer.parseInt(hotelId), name, address, city, state, zipcode, notes));
                } else if (option.equals("delete")) {
                    hs.deleteHotel(new HotelModel(Integer.parseInt(hotelId), name, address, city, state, zipcode, notes));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<HotelModel> hotelsToRemove = new ArrayList<>();
        if (citySearch != null && !citySearch.isEmpty()) {
            for (HotelModel h : hotels) {
                if (!h.getCity().equals(citySearch)) {
                    hotelsToRemove.add(h);
                }
            }
        }
        if (stateSearch != null && !stateSearch.isEmpty()) {
            for (HotelModel h : hotels) {
                if (!h.getState().equals(stateSearch)) {
                    hotelsToRemove.add(h);
                }
            }
        }
        hotels.removeAll(hotelsToRemove);
        
        
        request.setAttribute("hotels", hotels);
        int hotelID = -1;
        String hotelToEdit = request.getParameter("id");

        if (hotelToEdit != null) {
            try {
                hotelID = Integer.parseInt(hotelToEdit);
                for (HotelModel h : hotels) {
                    if (h.getId() == hotelID) {
                        request.setAttribute("hotel", h);
                        break;
                    }
                }

            } catch (Exception e) {

            }
        }
        RequestDispatcher view = request.getRequestDispatcher(RESULT);
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
