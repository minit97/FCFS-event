package org.example.api.service

import org.example.api.producer.CouponCreateProducer
import org.example.api.repository.AppliedUserRepository
import org.example.api.repository.CouponCountRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponCountRepository: CouponCountRepository,
    private val couponCreateProducer: CouponCreateProducer,
    private val appliedUserRepository: AppliedUserRepository
) {

    fun apply(userId: Long) {
        val apply = appliedUserRepository.add(userId)
        if (apply?.toInt() != 1) return

        val count = couponCountRepository.increment() ?:
                        throw NullPointerException("Count is null")
        if (count > 100) return

        couponCreateProducer.create(userId)
    }
}