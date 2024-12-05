package org.example.inpost

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer

class MongoTestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    def mongo = new MongoDBContainer("mongo:8.0")

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        mongo.withReuse(true)
        mongo.start()
        TestPropertyValues values = TestPropertyValues.of(
                "spring.data.mongodb.uri=" + mongo.getReplicaSetUrl("inpost-test"),
                "spring.data.mongodb.database=inpost-test"
        )
        values.applyTo(applicationContext)
    }
}