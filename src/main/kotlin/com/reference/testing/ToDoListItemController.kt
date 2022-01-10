package com.reference.testing

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/ToDoListItem"])
class ToDoListItemController(private val toDoListItemService: ToDoListItemService) {

    @PostMapping("/{userId}")
    fun postToDoListItem(@PathVariable(value = "userId") userId: Int, @RequestBody description: String) {
        return toDoListItemService.addToDoListItem(description, userId)
    }

    @GetMapping("/ByUser")
    fun getAccountById(): Map<Int, List<ToDoListItem>> {
        return toDoListItemService.getAllToDoLists()
    }
}