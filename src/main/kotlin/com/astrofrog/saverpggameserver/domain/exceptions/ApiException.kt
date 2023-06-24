package com.astrofrog.saverpggameserver.domain.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.server.ResponseStatusException

open class ApiException(
    status: HttpStatusCode = HttpStatus.BAD_REQUEST,
    reason: String?,
) : ResponseStatusException(status, reason)
