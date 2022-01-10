package com.reference.testing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class ToDoListAcceptanceTest : ToDoListIntegrationTestFixture() {

    val testToDoListItem1User1 = ToDoListItem(id = 1, description = "To do item 1 for user 1", 1)
    val testToDoListItem2User1 = ToDoListItem(id = 2, description = "To do item 2 for user 1", 1)
    val testToDoListItem1User2 = ToDoListItem(id = 3, description = "To do item 1 for user 2", 2)

    val expectedTestToDoListMap = mapOf(
        testToDoListItem1User1.userId to listOf(
            testToDoListItem1User1,
            testToDoListItem2User1
        ), testToDoListItem1User2.userId to listOf(testToDoListItem1User2)
    )

    @Test
    fun givenNoToDoListsExist_whenItemsAreAddedForMultipleUsers_thenListsAreAvailable() {
        // Given
        var toDoListMapBeforeAdditions = getAllToDoLists()
        Assertions.assertThat(toDoListMapBeforeAdditions).isEqualTo(emptyMap<Int, List<ToDoListItem>>())

        addToDoItem(testToDoListItem1User1.description, testToDoListItem1User1.userId)
        addToDoItem(testToDoListItem2User1.description, testToDoListItem2User1.userId)
        addToDoItem(testToDoListItem1User2.description, testToDoListItem1User2.userId)

        // When
        var toDoListMapAfterAdditions = getAllToDoLists()

        // Then
        Assertions.assertThat(toDoListMapAfterAdditions).isEqualTo(expectedTestToDoListMap)
    }
}