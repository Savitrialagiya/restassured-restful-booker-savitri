package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.model.LoginPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CustomerBookingPostTest extends TestBase {
    @Test
    public void createToken() {
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.basePath="/auth";
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post();
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createBooking() {
        HashMap<Object, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2013-02-23");
        bookingDates.put("checkout", "2014-10-23");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("saly");
        bookingPojo.setLastName("Brown");
        bookingPojo.setTotalPrice(111);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalNeeds("Breakfast");

        Response response = given()
                .header("Content-Type", "application/json")
               //.header("Accept", "application/json")
               // .header("Authorization", "Bearer bf18a4a715540ad")
                .header("cookie", "token=84cc207e5fa27f5")
                .body(bookingPojo)
                .when()
                .post("/booking");
        response.then().statusCode(200);
        response.prettyPrint();

    }
}