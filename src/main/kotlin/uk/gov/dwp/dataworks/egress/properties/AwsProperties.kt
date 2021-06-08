package uk.gov.dwp.dataworks.egress.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "aws")
class AwsProperties(var sqsQueueUrl: String = "") {

    @Bean
    fun sqsQueueUrl() = sqsQueueUrl
}
