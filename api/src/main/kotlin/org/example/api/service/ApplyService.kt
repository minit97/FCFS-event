package org.example.api.service

import org.example.api.domain.Coupon
import org.example.api.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService(
    private val couponRepository: CouponRepository
) {

    fun apply(userId: Long?) {
        val count = couponRepository.count()

        if (count > 100) return

        couponRepository.save(Coupon(userId = userId))
    }
}