package com.gdplabs.temporalprovisioning.temporal;

import io.kubernetes.client.openapi.ApiException;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.io.IOException;

@WorkflowInterface
public interface SpawnWorkflow {

    String QUEUE_NAME = "SpawnWorkflow";

    @WorkflowMethod
    void startWorkflow() throws IOException, ApiException;

}
