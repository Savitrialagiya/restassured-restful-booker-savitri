package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CustomerBookingPatchTest extends TestBase {
    @Test
    public void partialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        HashMap<Object, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", 2018 - 01 - 01);
        bookingDates.put("checkout", 2019 - 01 - 01);

        bookingPojo.setFirstName("saly");
        bookingPojo.setLastName("Brown");
        bookingPojo.setTotalPrice(111);
        bookingPojo.setDepositPaid(true);

        bookingPojo.setBookingDates(bookingDates);

        bookingPojo.setAdditionalNeeds("Breakfast");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=ab6bcd40bef3eb4")
                .auth().preemptive().basic("admin", "password123")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .patch("/booking/20");
        response.then().statusCode(200);
        response.prettyPrint();

    }

}
