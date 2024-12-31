package com.thetestingacademy.tests.Integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestIntegrationFlow extends BaseTest
{
    //Test Integration Scenario e2e happy path
    //1. Create b booking->booking id
    //2. Create Token
    //3. Verify create booing working fine
    //4. update the booing(booking id n token)
    //5. Delete booking

    @Test(groups = "qa", priority = 1)
    @Owner("Ashwini")
    @Description("TC#INT1- Step1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext)
    {
        rs.basePath(APIConstants.CREATE_PATCH_BOOKING_URL);

        response = RestAssured.given(rs)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        vr = response.then().log().all();
        vr.statusCode(200);

        BookingRespons bookingRespons = payloadManager.bookingResponsJava(response.asString());
        assertActions.verifyStringKey(bookingRespons.getBooking().getFirstname(),"Ashwini");
        System.out.println(bookingRespons.getBookingid());

        iTestContext.setAttribute("bookingid",bookingRespons.getBookingid());



    }

    @Test(groups = "qa", priority = 2)
    @Owner("Ashwini")
    @Description("TC#INT1- Step2. Verify the booking by booking id")
    public void testVerifyGetBooking(ITestContext iTestContext)
    {
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        //GET Req-> to verify that the firstname after creation is Ashwini

        String basePathGet = APIConstants.CREATE_PATCH_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGet);

        rs.basePath(basePathGet);

        response = RestAssured.given(rs)
                .when().get();

        vr = response.then().log().all();
        vr.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Ashwini");
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Ashwini")
    @Description("TC#INT1- Step3. Verify Update Booking by ID")
    public void testPatchBooking(ITestContext iTestContext)
    {
        System.out.println(iTestContext.getAttribute("bookingid"));

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);

        String basePathPATCH = APIConstants.CREATE_PATCH_BOOKING_URL+ "/"+ bookingid;
        System.out.println(basePathPATCH);

        rs.basePath(basePathPATCH);

        response = RestAssured
                .given(rs).cookie("token",token)
                        .when().body(payloadManager.updatePayloadString()).patch();

        vr = response.then().log().all();
        //validatable Assertion
        vr.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("James");
        assertThat(booking.getLastname()).isEqualTo("Brown");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Ashwini")
    @Description("TC#INT1- Step4. Verify Delete Booking by ID")
    public void testDeleteBooking(ITestContext iTestContext)
    {
        String token = (String) iTestContext.getAttribute("token");
        Integer booingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = APIConstants.CREATE_PATCH_BOOKING_URL+ "/"+ booingid;

        rs.basePath(basePathDELETE).cookie("token",token);
        vr=RestAssured.given().spec(rs)
                .when().delete().then().log().all();
        vr.statusCode(201);
    }
    //allure serve allure-results->cmd for report

}
