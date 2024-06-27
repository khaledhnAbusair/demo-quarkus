package com.test;


import com.test.generated.model.FileType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.apache.http.HttpStatus;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class FakeFilesApiService extends KycFilesApiService {

    public static final String EXISTING_CASE_ID = "4be556ca-3560-406b-8a35-f0e7721e4471";

    @Override
    public Response uploadFile(MultipartFormDataInput input, String tenant, SecurityContext securityContext) {
        try {
            com.test.generated.model.FileType fileType = input.getFormDataPart("fileType", FileType.class, null);
            String caseId = input.getFormDataPart("caseId", String.class, null);
            if (caseId != null && !EXISTING_CASE_ID.equals(caseId))
                return Response.status(HttpStatus.SC_NOT_FOUND).build();
            return Response.status(HttpStatus.SC_CREATED)
                    .entity(response(fileType))
                    .build();
        } catch (Exception e) {
            return Response.status(HttpStatus.SC_BAD_REQUEST).build();
        }
    }

    private com.test.generated.model.UploadResponse response(FileType fileType) {
        com.test.generated.model.UploadResponse result = new com.test.generated.model.UploadResponse();
        result.setId(fileType.toString());
        return result;
    }

    @Override
    public Response downloadFile(String id, String tenant, SecurityContext securityContext) {
        String INVALID_ID = "123";
        if (INVALID_ID.equals(id)) {
            return Response.status(HttpStatus.SC_BAD_REQUEST).build();
        }
        DownloadFileResponse response = new DownloadFileResponse(new byte[1]);
        return Response.status(HttpStatus.SC_OK).entity(response.getImage()).build();
    }
}
