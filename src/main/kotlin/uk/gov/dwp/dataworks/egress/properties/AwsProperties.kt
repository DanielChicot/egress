package uk.gov.dwp.dataworks.egress.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "aws")
class AwsProperties(var sqsQueueUrl: String = "",
                    var sqsCheckIntervalMs: Int = 10_000,
                    var dataEgressTable: String = "data-egress") {

    @Bean
    fun sqsQueueUrl() = sqsQueueUrl

    @Bean
    fun sqsCheckIntervalMs() = sqsCheckIntervalMs

    @Bean
    fun dataEgressTable() = dataEgressTable
}
