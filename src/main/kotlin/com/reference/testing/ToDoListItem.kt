package com.reference.testing

import javax.persistence.*

@Entity
@Table(name = "TO_DO_LIST_ITEM")
data class ToDoListItem(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = -1,
	val description: String = "",
	val userId: Int = -1)
