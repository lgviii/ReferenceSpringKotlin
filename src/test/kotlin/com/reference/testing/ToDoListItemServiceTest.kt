package com.reference.testing

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToDoListItemServiceTest {

    @Test
    fun givenToDoItemData_whenAddToDoItem_ThenRepositorySaveCalledWithNewItemData() {
        // given
        val description = "test description1"
        val userId = 1
        val slot = slot<ToDoListItem>()
        val mockToDoListItemRepository = mockk<ToDoListItemRepository>()
        every { mockToDoListItemRepository.save(capture(slot)) } returns ToDoListItem()
        val toDoListItemService = ToDoListItemService(mockToDoListItemRepository)

        // when
        toDoListItemService.addToDoListItem(description, userId)

        // then
        verify(exactly = 1) { mockToDoListItemRepository.save(allAny()) }
        assertThat(slot.captured.description).isEqualTo(description)
        assertThat(slot.captured.userId).isEqualTo(userId)
    }

    @Test
    fun givenRepositoryHasOneList_whenGetAllToDoLists_ThenListIsReturned() {
        // given
        val description = "test description2"
        val userId = 2
        val mockToDoListItemRepository = mockk<ToDoListItemRepository>()
        every { mockToDoListItemRepository.findAll() } returns listOf(
            ToDoListItem(
                description = description,
                userId = userId
            )
        )
        val toDoListItemService = ToDoListItemService(mockToDoListItemRepository)

        // when
        val resultToDoList = toDoListItemService.getAllToDoLists()

        // then
        verify(exactly = 1) { mockToDoListItemRepository.findAll() }
        assertThat(resultToDoList[2]!![0].description).isEqualTo(description)
        assertThat(resultToDoList[2]!![0].userId).isEqualTo(userId)
    }
}