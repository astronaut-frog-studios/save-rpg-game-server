package com.astrofrog.saverpggameserver.application.web.payloads.requests

import com.astrofrog.saverpggameserver.domain.entities.Node

data class HistoryLineUpdateRequest(
    val node: Node,
)
