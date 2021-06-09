package uk.gov.dwp.dataworks.egress.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.sqs.SqsAsyncClient

@Configuration
@Profile("!LOCALSTACK")
class AwsConfiguration {

    @Bean
    fun sqsClient(): SqsAsyncClient = SqsAsyncClient.create()

    @Bean
    fun dynamoDbClient(): DynamoDbAsyncClient = DynamoDbAsyncClient.create()
}
