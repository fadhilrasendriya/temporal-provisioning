package com.gdplabs.temporalprovisioning.temporal;

import com.gdplabs.temporalprovisioning.service.KubeService;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpawnActivitiesImpl implements SpawnActivities{

    private KubeService kubeService;

    public SpawnActivitiesImpl(KubeService kubeService) {
        this.kubeService = kubeService;
    }

    @Override
    public String spawnPod1() throws IOException, ApiException {
        return kubeService.spawnPod1();
    }

    @Override
    public String spawnPod2() throws IOException, ApiException {
        return kubeService.spawnPod2();
    }
}
