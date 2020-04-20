package com.lendico.loanCalculator.controller;

import com.lendico.loanCalculator.LoanCalculatorApplication;
import com.lendico.loanCalculator.LoanScheduleRequestDTO;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@ContextConfiguration(classes = LoanCalculatorApplication.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LoanScheduleControllerTest {

    @Value("http://localhost:${local.server.port}/loan/schedules/fetch")
    private String baseUrl;

    @Test
    public void testLoanSchedule() {
        LoanScheduleRequestDTO request = LoanScheduleRequestDTO.builder()
                .loanAmount(5000l)
                .duration(24)
                .nominalRate(5.0f)
                .startDate(LocalDateTime.now())
                .build();

        //@formatter:off
        given()
                .header(new Header("content-type", JSON.toString()))
                .body(request)
        .when()
                .post(baseUrl)
        .then()
                .statusCode(OK.value())
                .body("", Matchers.hasSize(24));
        //@formatter:on
    }

    @Test
    public void testLoanSchedule_withBadRequest() {
        LoanScheduleRequestDTO request = LoanScheduleRequestDTO.builder()
                .loanAmount(5000l)
                .duration(24)
                .nominalRate(5.0f)
                .build();

        //@formatter:off
        given()
                .header(new Header("content-type", JSON.toString()))
                .body(request)
        .when()
                .post(baseUrl)
        .then()
                .statusCode(BAD_REQUEST.value());
        //@formatter:on
    }

    @Test
    public void testLoanSchedule_withInvalidData() {
        LoanScheduleRequestDTO request = LoanScheduleRequestDTO.builder()
                .loanAmount(5000l)
                .duration(24)
                .nominalRate(0.0f)
                .startDate(LocalDateTime.now())
                .build();

        //@formatter:off
        given()
                .header(new Header("content-type", JSON.toString()))
                .body(request)
        .when()
                .post(baseUrl)
        .then()
                .statusCode(INTERNAL_SERVER_ERROR.value());
        //@formatter:on
    }

    @Test
    public void testLoanSchedule_withInvalidData() {
        LoanScheduleRequestDTO request = LoanScheduleRequestDTO.builder()
                .loanAmount(5000l)
                .duration(24)
                .nominalRate(0.0f)
                .startDate(LocalDateTime.now())
                .build();

        //formatter:off
        given()
                .header(new Header("content-type", JSON.toString()))
                .body(request)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.value());
        //formatter:on
    }

}
