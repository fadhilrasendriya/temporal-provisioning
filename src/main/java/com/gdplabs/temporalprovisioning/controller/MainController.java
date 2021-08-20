package com.gdplabs.temporalprovisioning.controller;

import com.gdplabs.temporalprovisioning.service.SpawnService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MainController {

    private final CoreV1Api api;

    private final SpawnService spawnService;

    public MainController(CoreV1Api api, SpawnService spawnService) {
        this.api = api;
        this.spawnService = spawnService;
    }

    @RequestMapping(
            path = "/printNodes",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String printKubeNodes() throws ApiException {
        V1NodeList nodeList = api.listNode(null, null, null, null, null, null, null, null, 10, false);
        return nodeList.getItems().toString();
    }

    @RequestMapping(
            path = "/spawnPods",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String spawnPods() throws IOException, ApiException {
        return spawnService.spawn();
    }
}
