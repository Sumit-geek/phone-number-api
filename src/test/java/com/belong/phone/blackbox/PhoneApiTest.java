package com.belong.phone.blackbox;

import com.belong.phone.PhoneApplication;
import com.belong.phone.models.CustomerDto;
import com.belong.phone.models.PhoneDto;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {PhoneApplication.class})
@Configuration
@Sql(scripts = {"/db/test_data/data_setup.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = {"/db/test_data/data_cleanup.sql"}, executionPhase = AFTER_TEST_METHOD)
class PhoneApiTest {

  public static final String X_CORRELATION_ID = "X_CORRELATION_ID";

  @BeforeAll
  static void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.basePath = "/phone-number";
    RestAssured.port = 8090;
  }

  @Test
  void getPhoneNumbers_returnAllPhoneNumbers() {
    given()
        .log().all()
        .header(X_CORRELATION_ID, "1234")
        .when()
        .get("v1/phones")
        .then()
        .log().all()
        .statusCode(HttpStatus.SC_OK)
        .body(containsString("222222"));
  }

  @Test
  void getPhoneNumbers_whenCorrectCustomerId_returnAllPhoneNumbers() {
    given()
        .log().all()
        .header(X_CORRELATION_ID, "1234")
        .when()
        .pathParam("customerId", "0522415f-0f7a-42b1-a072-3dd6f45beccb")
        .get("v1/customers/{customerId}/phones")
        .then()
        .log().all()
        .statusCode(HttpStatus.SC_OK)
        .body(containsString("111111"));
  }

  @Test
  void getPhoneNumbers_whenWrongCustomerId_return404() {
    given()
        .log().all()
        .header(X_CORRELATION_ID, "1234")
        .pathParam("customerId", "0524415f-0f7a-42b1-a072-3dd6f45beccb")
        .when()
        .get("v1/customers/{customerId}/phones")
        .then()
        .log().all()
        .statusCode(HttpStatus.SC_NOT_FOUND)
        .body(containsString("404"));
  }

  @Test
  void activatePhoneNumbers_whenValidRequest_returnSuccess() {
    given()
        .log().all()
        .header(X_CORRELATION_ID, "1234")
        .contentType(MediaType.APPLICATION_JSON.toString())
        .when()
        .pathParam("customerId", "0522415f-0f7a-42b1-a072-3dd6f45beccb")
        .body(getCustomerDtoWithPhoneNumber("111111"))
        .put("v1/customers/{customerId}/activate")
        .then()
        .log().all()
        .statusCode(HttpStatus.SC_ACCEPTED);
  }

  @Test
  void activatePhoneNumbers_whenCustomerIdAndPhoneNumberNotMatching_returnNotFound() {
    given()
        .log().all()
        .header(X_CORRELATION_ID, "1234")
        .contentType(MediaType.APPLICATION_JSON.toString())
        .when()
        .pathParam("customerId", "0522415f-0f7a-42b1-a072-3dd6f45beccb")
        .body(getCustomerDtoWithPhoneNumber("111112"))
        .put("v1/customers/{customerId}/activate")
        .then()
        .log().all()
        .statusCode(HttpStatus.SC_NOT_FOUND);
  }

  private CustomerDto getCustomerDtoWithPhoneNumber(String phoneNo) {
    List<PhoneDto> phoneDtoList = new ArrayList<>();
    phoneDtoList.add(PhoneDto.builder().phoneNo(phoneNo).build());
    CustomerDto customerDto = CustomerDto.builder()
            .id(UUID.fromString("0522415f-0f7a-42b1-a072-3dd6f45beccb"))
            .phoneNos(phoneDtoList).build();
    return customerDto;
  }
}
