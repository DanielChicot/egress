package uk.gov.dwp.dataworks.egress.configuration

import com.amazonaws.services.s3.AmazonS3EncryptionClientV2
import com.amazonaws.services.s3.AmazonS3EncryptionV2
import com.amazonaws.services.s3.model.CryptoConfigurationV2
import com.amazonaws.services.s3.model.CryptoMode
import com.amazonaws.services.s3.model.EncryptionMaterialsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI

@Configuration
@Profile("LOCALSTACK")
class LocalstackConfiguration(private val encryptionMaterialsProvider: EncryptionMaterialsProvider) {

    @Bean
    fun encryptingS3Client(): AmazonS3EncryptionV2 =
        AmazonS3EncryptionClientV2.encryptionBuilder()
           .withEncryptionMaterialsProvider(encryptionMaterialsProvider)
           .withCryptoConfiguration(CryptoConfigurationV2().withCryptoMode(CryptoMode.AuthenticatedEncryption))
           .build()

    @Bean
    fun sqsClient(): SqsAsyncClient = SqsAsyncClient.builder().localstack()

    @Bean
    fun dynamoDbClient(): DynamoDbAsyncClient = DynamoDbAsyncClient.builder().localstack()

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
