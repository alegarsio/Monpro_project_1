package com.alegrarsio.proritize_mobpro.model
import java.util.UUID;

data class Project(
    val id: UUID = UUID.randomUUID(),
    val name : String,
    val priority : Int
)
