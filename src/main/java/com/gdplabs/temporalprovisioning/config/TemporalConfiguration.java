package com.gdplabs.temporalprovisioning.config;

import com.gdplabs.temporalprovisioning.temporal.SpawnActivities;
import com.gdplabs.temporalprovisioning.temporal.SpawnActivitiesImpl;
import com.gdplabs.temporalprovisioning.temporal.SpawnWorkflow;
import com.gdplabs.temporalprovisioning.temporal.SpawnWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfiguration {

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newInstance();
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs);
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public Worker worker(WorkerFactory workerFactory, SpawnActivities spawnActivities) {
        Worker worker = workerFactory.newWorker(SpawnWorkflow.QUEUE_NAME);
        worker.registerActivitiesImplementations(spawnActivities);
        worker.registerWorkflowImplementationTypes(SpawnWorkflowImpl.class);
        return worker;
    }

}
