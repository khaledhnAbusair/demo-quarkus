package com.test;

import com.test.generated.api.FilesApiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@ApplicationScoped
public class KycFilesApiService implements FilesApiService {


    @Override
    public Response deleteFile(String id, String tenant, SecurityContext securityContext) {
        return Response.status(Response.Status.OK)
                .build();
    }

    @Override
    public Response downloadFile(String id, String tenant, SecurityContext securityContext) {
        return Response.status(Response.Status.OK)
                .build();
    }

    @Override
    public Response downloadPathFile(String id, String tenant, Integer expiry, SecurityContext securityContext) {
        return Response.status(Response.Status.OK)
                .build();
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String tenant, SecurityContext securityContext) {
        return Response.status(Response.Status.OK)
                .build();
    }
}
