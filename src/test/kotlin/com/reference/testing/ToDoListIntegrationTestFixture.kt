package com.reference.testing

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ToDoListIntegrationTestFixture {
    @Autowired
    protected val testRestTemplate: TestRestTemplate? = null

    @Autowired
    protected val toDoListItemRepository: ToDoListItemRepository? = null

    protected fun getAllToDoLists(): Map<Int, List<ToDoListItem>>? {
        val response = testRestTemplate!!.getForEntity("/ToDoListItem/ByUser", String::class.java)
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        val mapper = ObjectMapper()
        val typeRef = object : TypeReference<Map<Int, List<ToDoListItem>>>() {}

        return mapper.readValue(response.body.toString(), typeRef)
    }

    protected fun addToDoItem(description: String, userId: Int) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val requestEntity: HttpEntity<String> = HttpEntity<String>(description, headers)
        val response = testRestTemplate!!.postForEntity("/ToDoListItem/${userId}", requestEntity, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}