package uk.gov.dwp.dataworks.egress.domain

data class DataEgressTableEntry(val sourceBucket: String,
                                 val sourcePrefix: String,
                                 val destinationBucket: String,
                                 val destinationPrefix: String,
                                 val transferType: String,
                                 val compress: Boolean = false,
                                 val compressionFormat: String?,
                                 val roleArn: String?)
