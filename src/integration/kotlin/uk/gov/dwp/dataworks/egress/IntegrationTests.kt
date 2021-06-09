package uk.gov.dwp.dataworks.egress

import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.future.await
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.text.SimpleDateFormat
import java.util.*

class IntegrationTests: StringSpec() {
    init {
        "Should be able to process files encrypted by EMR" {
            val message = """{
                "Records": [
                    { "s3": { "object": { "key": "dataegress/cbol-report/${todaysDate()}/cbol.csv" } } }
                ]
            }"""

            val request = with(SendMessageRequest.builder()) {
                queueUrl("http://localstack:4566/000000000000/integration-queue")
                messageBody(message)
                build()
            }

            val response = sqs.sendMessage(request).await()
            println("Response: $response")
        }
    }


    companion object {

        private val applicationContext by lazy {
            AnnotationConfigApplicationContext(TestConfiguration::class.java)
        }

        private val sqs = applicationContext.getBean(SqsAsyncClient::class.java)
        private fun todaysDate() = SimpleDateFormat("yyyy-MM-dd").format(Date())
    }
}
