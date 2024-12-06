package org.example.api.service

import org.assertj.core.api.Assertions.assertThat
import org.example.api.repository.CouponRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplyServiceTest(
    @Autowired val applyService: ApplyService,
    @Autowired val couponRepository: CouponRepository
) {

    @Test
    @DisplayName("한번만 응모")
    fun one_apply() {
        applyService.apply(1L)

        val count = couponRepository.count()

        assertThat(count).isEqualTo(1)
    }


}