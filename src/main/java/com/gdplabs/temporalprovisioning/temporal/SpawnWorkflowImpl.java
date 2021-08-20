package com.gdplabs.temporalprovisioning.temporal;

import io.kubernetes.client.openapi.ApiException;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.io.IOException;
import java.time.Duration;

public class SpawnWorkflowImpl implements SpawnWorkflow{

    private final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(10))
            .setMaximumAttempts(3)
            .build();

    private final ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(10))
            .setRetryOptions(retryOptions)
            .build();

    private final SpawnActivities activities = Workflow.newActivityStub(SpawnActivities.class, activityOptions);

    @Override
    public void startWorkflow() throws IOException, ApiException {
        activities.spawnPod1();

        activities.spawnPod2();
    }
}
