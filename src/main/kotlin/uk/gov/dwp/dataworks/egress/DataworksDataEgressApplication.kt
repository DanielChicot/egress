package uk.gov.dwp.dataworks.egress

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest

@SpringBootApplication
class DataworksDataEgressApplication(private val sqs: SqsAsyncClient,
									 private val sqsQueueUrl: String): CommandLineRunner {
	override fun run(vararg args: String?) {
		println("sqsQueueUrl: '$sqsQueueUrl'.")
//		val request = ReceiveMessageRequest.builder().queueUrl()
		//sqs.receiveMessage()
		println(sqs)
	}
}

fun main(args: Array<String>) {
	runApplication<DataworksDataEgressApplication>(*args)
}
