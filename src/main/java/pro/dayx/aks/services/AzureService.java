package pro.dayx.aks.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AzureService {
    @Value("${azure.storage.sasToken}")
    private String azureSasToken;

    @Value("${azure.storage.blobEndPoint}")
    private String blobEndPoint;

    public String getFileUrl(String filePath, String containerName){
        return blobEndPoint + containerName + "/" + filePath;
    }
    private BlobServiceClient buildServiceClientContainer(){
        return new BlobServiceClientBuilder()
                .endpoint(this.blobEndPoint)
                .sasToken(this.azureSasToken)
                .buildClient();
    }

    public String uploadFile(MultipartFile multipartFile, String path, String containerName) {
        BlobContainerClient containerClient = buildServiceClientContainer().getBlobContainerClient(containerName);
        String newName = multipartFile.getOriginalFilename().replaceAll("[а-яА-Я]*", "");
        newName = newName.replace(" ", "");
        newName = newName.replace(".", "");
        newName = newName.replace(",", "");
        newName = newName.replace("\\", "");




        String blobName = StringUtils.cleanPath(path + "/" + new Date().getTime() + newName);
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        try {
            blobClient.upload(new BufferedInputStream(multipartFile.getInputStream()), multipartFile.getSize());
        } catch (IOException exception){

        }
        return blobName;
    }
}
