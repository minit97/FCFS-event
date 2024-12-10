package org.example.consumer.consumer

import io.github.oshai.kotlinlogging.KotlinLogging
import org.example.consumer.domain.Coupon
import org.example.consumer.domain.FailedEvent
import org.example.consumer.repository.CouponRepository
import org.example.consumer.repository.FailedEventRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class CouponCreatedConsumer(
    private val couponRepository: CouponRepository,
    private val failedEventRepository: FailedEventRepository
) {

    private val logger = LoggerFactory.getLogger(CouponCreatedConsumer::class.java)

    @KafkaListener(topics = ["coupon_create"], groupId = "group_1")
    fun listener(userId: Long) {
        runCatching {
            couponRepository.save(Coupon(userId = userId))
        }.onFailure {
            logger.error("Failed to create coupon::$userId")
            failedEventRepository.save(FailedEvent(userId = userId))
        }
    }


}