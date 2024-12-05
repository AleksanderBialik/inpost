package org.example.inpost

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification


@ContextConfiguration(
        classes = [InpostApplication.class],
        initializers = [MongoTestContainerInitializer.class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(["test"])
@AutoConfigureWebTestClient
class IntegrationSpecification extends Specification{
}
