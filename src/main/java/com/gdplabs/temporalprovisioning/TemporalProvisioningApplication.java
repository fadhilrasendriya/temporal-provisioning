package com.gdplabs.temporalprovisioning;

import io.temporal.worker.WorkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TemporalProvisioningApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TemporalProvisioningApplication.class, args);
        WorkerFactory workerFactory = applicationContext.getBean(WorkerFactory.class);
        workerFactory.start();
    }

}
