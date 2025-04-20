package com.kursor.chronicles_of_ww2.core.error_handling

import cmp_notes.composeapp.core.generated.resources.Res
import cmp_notes.composeapp.core.generated.resources.error_deserialization
import cmp_notes.composeapp.core.generated.resources.error_matching_application_not_found
import cmp_notes.composeapp.core.generated.resources.error_no_internet_connection
import cmp_notes.composeapp.core.generated.resources.error_server
import cmp_notes.composeapp.core.generated.resources.error_server_unavailable
import cmp_notes.composeapp.core.generated.resources.error_ssl_handshake
import cmp_notes.composeapp.core.generated.resources.error_unauthorized
import cmp_notes.composeapp.core.generated.resources.error_unexpected
import com.kursor.chronicles_of_ww2.core.strings.StringDesc

/**
 * Returns human readable messages for exceptions.
 */
val Exception.errorMessage: StringDesc
    get() = when (this) {

        is ExternalAppNotFoundException -> StringDesc.Resource(Res.string.error_matching_application_not_found)

        is ServerUnavailableException -> StringDesc.Resource(Res.string.error_server_unavailable)

        is NoInternetException -> StringDesc.Resource(Res.string.error_no_internet_connection)

        is UnauthorizedException -> StringDesc.Resource(Res.string.error_unauthorized)

        is SSLHandshakeException -> StringDesc.Resource(Res.string.error_ssl_handshake)

        is ServerException -> message?.let { StringDesc.Raw(it) }
            ?: StringDesc.Resource(Res.string.error_server)

        is DeserializationException -> StringDesc.Resource(Res.string.error_deserialization)

        else -> StringDesc.Resource(Res.string.error_unexpected)
    }