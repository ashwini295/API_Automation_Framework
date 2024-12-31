package com.thetestingacademy.modules;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayloadManager
{
    //converting java object to string
    Gson gson;
    public String createPayloadBookingAsString()
    {
        Booking booking = new Booking();
        booking.setFirstname("Ashwini");
        booking.setLastname("Hosmani");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);
        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }

    //Converting the String to the JAVA obj
    public BookingRespons bookingResponsJava(String responseString)
    {
        gson = new Gson();
        BookingRespons bookingRespons = gson.fromJson(responseString,BookingRespons.class);
        return bookingRespons;
    }

    public Booking getResponseFromJSON(String getResponse)
    {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse,Booking.class);
        return booking;
    }

    //setAuthPayload
    //java to JSON
    public String setAuthPayload()
    {
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadstring = gson.toJson(auth);
        System.out.println("payload set to the"+jsonPayloadstring);
        return jsonPayloadstring;
    }

    //json to java
    public String getTokenFromJSON(String tokenResponse)
    {
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();

    }

    public String updatePayloadString()
    {
        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("Brown");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);

    }








    
}
