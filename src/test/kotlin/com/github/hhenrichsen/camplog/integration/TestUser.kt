package com.github.hhenrichsen.camplog.integration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestUser(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Should render a page which contains `() {
        val entity = restTemplate.getForEntity<String>("/user/00000000-0000-0000-0000-00000000000")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>CampLog</h1>", "Hunter Henrichsen's Camping Log", "Hunter&#39;s Other Campout")
    }

}