package com.reference.testing

import org.springframework.stereotype.Service


@Service
class ToDoListItemService(private val toDoListItemRepository: ToDoListItemRepository) {

    fun addToDoListItem(description: String, userId: Int) {

        val newToDoListItem = ToDoListItem(description = description, userId = userId)

        toDoListItemRepository!!.save(newToDoListItem)
    }

    fun getAllToDoLists(): Map<Int, List<ToDoListItem>> {

        val allToDoListItems = toDoListItemRepository!!.findAll()

        return allToDoListItems.groupBy { it.userId }
    }
}