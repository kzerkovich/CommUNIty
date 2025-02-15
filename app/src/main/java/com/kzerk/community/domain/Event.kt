package com.kzerk.community.domain

import java.util.Date

data class Event(
	val id: Int,
	val date: Date,
	val time: Long,
	val org: String,
	val tags: List<String>,
	val description: String
)
