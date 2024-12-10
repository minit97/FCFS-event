package org.example.api.service

import org.assertj.core.api.Assertions.assertThat
import org.example.api.repository.CouponRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

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

    @Test
    @DisplayName("여러명 응모")
    fun many_apply() {
        val threadCount = 1000
        val executorService: ExecutorService = Executors.newFixedThreadPool(threadCount)
        val latch = CountDownLatch(threadCount)

        for (i in 0..<threadCount) {
            val userId = i.toLong()
            executorService.submit({
                try {
                    applyService.apply(userId)
                } finally {
                    latch.countDown()
                }
            })
        }

        latch.await()

        Thread.sleep(10000)

        val count = couponRepository.count()

        assertThat(count).isEqualTo(100)
    }
}