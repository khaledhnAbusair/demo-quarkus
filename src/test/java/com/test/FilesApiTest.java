package com.test;


import com.test.generated.api.FilesApiService;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilesApiTest {
    @Inject
    FilesApiService filesApiService;

    @BeforeAll
    public static void setup() {
        FakeFilesApiService fileApiService = new FakeFilesApiService();
        QuarkusMock.installMockForType(fileApiService, FilesApiService.class);
    }


    @Test
    void givenInvalidMultiPartRequestWhenUploadThenShouldReturn400() {
        given().
                multiPart("file", "")
                .formParam("fileType", "IDACEMAGE")
                .formParam("caseId", FakeFilesApiService.EXISTING_CASE_ID)
                .when().post("files/")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void givenValidIdWhenDownloadThenShouldReturn200() {
        given()
                .when().get("/files/ekyc.2020.006_IDFACEIMAGE.98351ab5-7ed1-46fb-bcdc-e0b78ae584e4")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test
    void givenInValidIdWhenDownloadThenShouldReturn400() {
        given()
                .when().get("/files/123")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }


}
