package com.astrofrog.saverpggameserver.application.web.payloads.requests

import com.astrofrog.saverpggameserver.domain.entities.Node
import com.astrofrog.saverpggameserver.domain.entities.Player

data class HistoryLineRequest(
    val user: Player,
    val node: Node,
)
