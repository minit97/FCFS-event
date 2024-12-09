package org.example.api.service

import org.example.api.producer.CouponCreateProducer
import org.example.api.repository.CouponCountRepository
import org.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
    private val couponCreateProducer: CouponCreateProducer
) {

    fun apply(userId: Long) {
        val count = couponCountRepository.increment() ?:
                        throw NullPointerException("Count is null")
//        if (count > 100) return

//        couponRepository.save(Coupon(userId = userId))
        couponCreateProducer.create(userId)
    }
}