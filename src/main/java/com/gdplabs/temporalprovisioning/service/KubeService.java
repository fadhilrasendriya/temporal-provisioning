package com.gdplabs.temporalprovisioning.service;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.util.Yaml;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class KubeService {

    private CoreV1Api api;

    private ApiClient client;

    public KubeService(CoreV1Api api, ApiClient client) {
        this.api = api;
        this.client = client;
    }

    public String spawnPod1() throws FileNotFoundException, IOException, ApiException {

        File file = ResourceUtils.getFile("classpath:k8sspecs/alpine1.yaml");
        V1Pod v1Pod = (V1Pod) Yaml.load(file);
        V1Pod result = api.createNamespacedPod("default", v1Pod, null, null, null);
        return result.toString();
    }

    public String spawnPod2() throws FileNotFoundException, IOException, ApiException {

        File file = ResourceUtils.getFile("classpath:k8sspecs/alpine2.yaml");
        V1Pod v1Pod = (V1Pod) Yaml.load(file);
        V1Pod result = api.createNamespacedPod("default", v1Pod, null, null, null);
        return result.toString();
    }

}
