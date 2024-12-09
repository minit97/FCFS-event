package org.example.api.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponCreateProducer(
    private val kafkaTemplate: KafkaTemplate<String?, Long?>
) {

    fun create(userId: Long) {
        println("박현민 ====박현민 ====박현민 ====박현민 ====박현민 ====박현민 ====")
        kafkaTemplate.send("coupon_create", userId)
    }

}