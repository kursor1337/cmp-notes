package com.kursor.chronicles_of_ww2.notes.routing

import com.kursor.chronicles_of_ww2.core.jwtTokenLogin
import com.kursor.chronicles_of_ww2.dto.notes.CreateNoteRequest
import com.kursor.chronicles_of_ww2.dto.notes.CreateNoteResponse
import com.kursor.chronicles_of_ww2.dto.notes.EditNoteRequest
import com.kursor.chronicles_of_ww2.dto.notes.NoteResponse
import com.kursor.chronicles_of_ww2.notes.domain.Note
import com.kursor.chronicles_of_ww2.notes.domain.NoteId
import com.kursor.chronicles_of_ww2.notes.domain.NoteRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureNotesRouting(
    noteRepository: NoteRepository
) {
    routing {
        route("api/v1/notes") {
            authenticate {
                post("create") {
                    call.receive<CreateNoteRequest>()
                        .let {
                            noteRepository.createNote(
                                userLogin = call.jwtTokenLogin,
                                title = it.title,
                                content = it.content
                            )
                        }
                        .let { call.respond(it.toCreateNoteResponse()) }
                }

                post("edit") {
                    call.receive<EditNoteRequest>()
                        .let {
                            noteRepository.editNote(
                                userLogin = call.jwtTokenLogin,
                                noteId = NoteId(it.id),
                                title = it.title,
                                content = it.content
                            )
                        }
                        .let { call.respond(HttpStatusCode.OK) }
                }

                get {
                    noteRepository
                        .getAllNotesForUser(call.jwtTokenLogin)
                        .map { it.toResponse() }
                        .let { call.respond(it) }
                }
            }
        }
    }
}

fun Note.toResponse() = NoteResponse(id.value, title, content)

fun NoteId.toCreateNoteResponse() = CreateNoteResponse(value)
