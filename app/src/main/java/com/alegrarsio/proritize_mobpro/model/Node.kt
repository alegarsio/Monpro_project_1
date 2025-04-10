package com.alegrarsio.proritize_mobpro.model
import java.util.UUID;

data class Node(
    var priority: Int,
    val projects: MutableList<Project> = mutableListOf(),
    var left: Node? = null,
    var right: Node? = null
)
