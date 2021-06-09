package uk.gov.dwp.dataworks.egress

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import uk.gov.dwp.dataworks.egress.configuration.LocalstackConfiguration

@Import(LocalstackConfiguration::class)
@Configuration
class TestConfiguration {
}
