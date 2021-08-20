package com.gdplabs.temporalprovisioning.service;

import com.gdplabs.temporalprovisioning.temporal.SpawnWorkflow;
import io.kubernetes.client.openapi.ApiException;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.api.enums.v1.WorkflowIdReusePolicy;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SpawnService {
    private final WorkflowClient workflowClient;

    public SpawnService(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    public String spawn() {

        WorkflowIdReusePolicy workflowIdReusePolicy = WorkflowIdReusePolicy.WORKFLOW_ID_REUSE_POLICY_ALLOW_DUPLICATE;

        WorkflowOptions workflowOptions = WorkflowOptions
                .newBuilder()
                .setWorkflowId("SpawnWorkflow")
                .setTaskQueue(SpawnWorkflow.QUEUE_NAME)
                .setWorkflowIdReusePolicy(workflowIdReusePolicy)
                .build();

        SpawnWorkflow workflow = workflowClient.newWorkflowStub(SpawnWorkflow.class, workflowOptions);
        WorkflowExecution start = WorkflowClient.start(() -> {
            try {
                workflow.startWorkflow();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        });
        return "Workflow started.";
    }


}
