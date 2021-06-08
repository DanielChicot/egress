package uk.gov.dwp.dataworks.egress.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsAsyncClientBuilder
import java.net.URI

@Configuration
@Profile("LOCALSTACK")
class LocalstackConfiguration {

    @Bean
    fun sqsClient(): SqsAsyncClient = SqsAsyncClient.builder().localstack()

    fun <B: AwsClientBuilder<B, C>?, C> AwsClientBuilder<B, C>.localstack(): C =
        run {
            region(Region.EU_WEST_2)
            endpointOverride(URI(localstackEndpoint))
            credentialsProvider(credentialsProvider())
            build()
        }

    private fun credentialsProvider() =
        StaticCredentialsProvider.create(AwsBasicCredentials.create(localstackAccessKeyId,localstackSecretAccessKey))

    companion object {
        private const val localstackEndpoint = "http://localstack:4566/"
        private const val localstackAccessKeyId = "accessKeyId"
        private const val localstackSecretAccessKey = "secretAccessKey"
    }

}
