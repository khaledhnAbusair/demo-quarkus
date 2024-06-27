package com.test;


import com.test.generated.api.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@ApplicationScoped
public class KycFilesApiService implements com.test.generated.api.FilesApiService {


    @Override
    public Response deleteFile(String id, String tenant, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response downloadFile(String id, String tenant, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response downloadPathFile(String id, String tenant, Integer expiry, SecurityContext securityContext) throws NotFoundException {
        return null;
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String tenant, SecurityContext securityContext) throws NotFoundException {
        return null;
    }
}
