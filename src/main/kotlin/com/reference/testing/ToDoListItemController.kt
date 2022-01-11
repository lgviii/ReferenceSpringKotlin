package com.reference.testing

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/ToDoListItem"])
class ToDoListItemController(private val toDoListItemService: ToDoListItemService) {

    // TODO: 5 - delete the method
    @PostMapping("/{userId}")
    fun postToDoListItem(@PathVariable(value = "userId") userId: Int, @RequestBody description: String) {
        return toDoListItemService.addToDoListItem(description, userId)
    }

    // TODO: 9 - delete the test method body
    @GetMapping("/ByUser")
    fun getAccountById(): Map<Int, List<ToDoListItem>> {
        return toDoListItemService.getAllToDoLists()
    }
}