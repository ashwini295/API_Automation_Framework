package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingRespons;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class test_CreateBooking extends BaseTest
{
    @Owner("Ashwini")
    @TmsLink("https://bugzs.atlassian.net/jira/software/projects/REQ/boards/1?selecttest")
    @Link(name="Link to TC", url="https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @Description("Verify that post req is working fine")
    @Test(groups = "qa")
    public void testVerifyCreateBookingPost01()
    {
        rs.basePath(APIConstants.CREATE_PATCH_BOOKING_URL);

        response= RestAssured.given(rs)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        vr=response.then().log().all();
        vr.statusCode(200);

        BookingRespons bookingRespons = payloadManager.bookingResponsJava(response.asString());
        assertActions.verifyStringKey(bookingRespons.getBooking().getFirstname(),"Ashwini");

    }

}
