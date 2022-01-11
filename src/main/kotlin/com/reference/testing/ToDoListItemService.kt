package com.reference.testing

import org.springframework.stereotype.Service


@Service
class ToDoListItemService(private val toDoListItemRepository: ToDoListItemRepository) {

    // TODO: 4 - delete the method
    fun addToDoListItem(description: String, userId: Int) {

        val newToDoListItem = ToDoListItem(description = description, userId = userId)

        toDoListItemRepository!!.save(newToDoListItem)
    }

    // TODO: 8 - delete the test method body
    fun getAllToDoLists(): Map<Int, List<ToDoListItem>> {

        val allToDoListItems = toDoListItemRepository!!.findAll()

        return allToDoListItems.groupBy { it.userId }
    }
}