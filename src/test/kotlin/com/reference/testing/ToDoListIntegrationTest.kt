package com.reference.testing

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ToDoListIntegrationTest : ToDoListIntegrationTestFixture() {

    val testToDoListItem1User1 = ToDoListItem(id = 1, description = "To do item 1 for user 1", 1)
    val testToDoListItem2User1 = ToDoListItem(id = 2, description = "To do item 2 for user 1", 1)
    val testToDoListItem1User2 = ToDoListItem(id = 3, description = "To do item 1 for user 2", 2)

    val testToDoListMap = mapOf(
        testToDoListItem1User1.userId to listOf(
            testToDoListItem1User1,
            testToDoListItem2User1
        ), testToDoListItem1User2.userId to listOf(testToDoListItem1User2)
    )

    @Test
    fun givenMultipleToDoListsExist_whenGetAllLists_thenAllListsAreReturned() {
        // Given
        toDoListItemRepository!!.saveAll(
            listOf(
                testToDoListItem1User1,
                testToDoListItem2User1,
                testToDoListItem1User2
            )
        )

        // When
        var toDoListMapAfterAdditions = getAllToDoLists()

        // Then
        Assertions.assertThat(toDoListMapAfterAdditions).isEqualTo(testToDoListMap)
    }

    @Test
    fun givenNoToDoListsExist_whenAddOneToDoItem_thenDatabaseContainsOnlyTheNewToDoItem() {
        // Given
        val initialCount = toDoListItemRepository!!.count()
        Assertions.assertThat(initialCount).isEqualTo(0)

        // When
        var toDoListMapAfterAdditions = getAllToDoLists()
        addToDoItem(testToDoListItem1User1.description, testToDoListItem1User1.userId)

        // Then
        val updatedCount = toDoListItemRepository!!.count()
        Assertions.assertThat(updatedCount).isEqualTo(1)
        val newlyCreatedToDoListItem = toDoListItemRepository!!.findById(1).get()
        Assertions.assertThat(newlyCreatedToDoListItem.description).isEqualTo(testToDoListItem1User1.description)
        Assertions.assertThat(newlyCreatedToDoListItem.userId).isEqualTo(testToDoListItem1User1.userId)
    }
}