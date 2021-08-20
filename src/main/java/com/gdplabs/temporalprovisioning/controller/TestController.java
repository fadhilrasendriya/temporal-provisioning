package com.gdplabs.temporalprovisioning.controller;

import com.gdplabs.temporalprovisioning.service.KubeService;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class TestController {

    private final KubeService kubeService;

    public TestController(KubeService kubeService) {
        this.kubeService = kubeService;
    }

    @RequestMapping(
            path = "/loadResource",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String loadFile() throws FileNotFoundException, IOException {
        File file = ResourceUtils.getFile("classpath:k8sspecs/alpine1.yaml");
        if(file.exists()) {
            byte[] fileData = Files.readAllBytes(file.toPath());
            String fileContent = new String(fileData);
            return fileContent;
        }
        return "Fail";
    }

    @RequestMapping(
            path = "/spawnPod1",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String spawnPod1() throws FileNotFoundException, IOException, ApiException {
        return kubeService.spawnPod1();
    }
}
