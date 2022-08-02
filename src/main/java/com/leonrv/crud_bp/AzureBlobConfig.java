package com.leonrv.crud_bp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import com.azure.storage.blob.*;

@Configuration
public class AzureBlobConfig {

    @Value("${azure.storage.connection.string}")
    private String connectionString;

    @Value("${azure.storage.container.name}")
    private String containerName;

    @Bean
    public BlobServiceClient clobServiceClient() {

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        return blobServiceClient;

    }

    @Bean
    public BlobContainerClient blobContainerClient() {

        BlobContainerClient blobContainerClient = clobServiceClient()
                .getBlobContainerClient(containerName);

        return blobContainerClient;

    }
}
