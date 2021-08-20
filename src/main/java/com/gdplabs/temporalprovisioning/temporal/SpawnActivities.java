package com.gdplabs.temporalprovisioning.temporal;

import io.kubernetes.client.openapi.ApiException;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.io.IOException;

@ActivityInterface
public interface SpawnActivities {

    @ActivityMethod
    String spawnPod1() throws IOException, ApiException;

    @ActivityMethod
    String spawnPod2() throws IOException, ApiException;

}
