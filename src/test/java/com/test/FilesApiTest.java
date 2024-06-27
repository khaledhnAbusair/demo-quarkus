package com.test;


import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilesApiTest {

    @BeforeAll
    public static void setup(){
        FakeFilesApiService fileApiService = new FakeFilesApiService();
        QuarkusMock.installMockForType(fileApiService, com.test.generated.api.FilesApiService.class);
    }

    @Test
    void givenMultiPartRequestWhenUploadThenShouldReturn204() {
        given().
                 multiPart("file", "")
                .formParam("fileType", "IDFACEIMAGE")
                .when().post("files/")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    void givenInvalidMultiPartRequestWhenUploadThenShouldReturn400() {
        given().
                multiPart("file", "")
                .formParam("fileType", "IDACEMAGE")
                .formParam("caseId",FakeFilesApiService.EXISTING_CASE_ID)
                .when().post("files/")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void givenMultiPartRequestWithNonExistingCaseIdWhenUploadThenShouldReturn404() {
        given().
                multiPart("file", "")
                .formParam("fileType", "IDFACEIMAGE")
                .formParam("caseId", UUID.randomUUID().toString())
                .when().post("files/")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void givenValidIdWhenDownloadThenShouldReturn200(){
        given()
                .when().get("/files/ekyc.2020.006_IDFACEIMAGE.98351ab5-7ed1-46fb-bcdc-e0b78ae584e4")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    void givenInValidIdWhenDownloadThenShouldReturn400(){
        given()
                .when().get("/files/123")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}
