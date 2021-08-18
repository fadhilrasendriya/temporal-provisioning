package com.gdplabs.temporalprovisioning.controller;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final CoreV1Api api;

    public MainController(CoreV1Api api) {
        this.api = api;
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
}
