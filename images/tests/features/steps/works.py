import json

from behave import *
import boto3


@given("An sqs message has been sent")
def step_impl(context):
    sqs = sqs_client()
    message = {
        "data": "hello, world"
    }
    message_body = json.dumps(message)
    sqs.send_message(QueueUrl="http://localstack:4566/000000000000/integration-queue",
                     MessageBody=message_body)


def sqs_client():
    return aws_client("sqs")


def aws_client(service_name: str):
    return boto3.client(service_name=service_name,
                        endpoint_url="http://localstack:4566",
                        use_ssl=False,
                        region_name='eu-west-2',
                        aws_access_key_id="accessKeyId",
                        aws_secret_access_key="secretAccessKey")
