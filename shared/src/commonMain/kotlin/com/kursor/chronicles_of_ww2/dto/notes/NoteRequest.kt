package com.kursor.chronicles_of_ww2.dto.notes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreateNoteRequest(
    @SerialName("title") val title: String,
    @SerialName("content") val content: String
)

@Serializable
class EditNoteRequest(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String
)
