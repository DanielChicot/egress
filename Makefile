localstack:
	docker-compose up -d localstack
	@{ \
		while ! docker logs localstack 2> /dev/null | grep -q "^Ready\." ; do \
			echo Waiting for localstack.; \
			sleep 2; \
		done; \
	}
	docker-compose up localstack-init

dks:
	docker-compose up -d dks

services: localstack dks
