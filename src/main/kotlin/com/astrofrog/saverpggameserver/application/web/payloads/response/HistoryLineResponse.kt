package com.astrofrog.saverpggameserver.application.web.payloads.response

import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineRequest
import com.astrofrog.saverpggameserver.domain.entities.Node
import com.astrofrog.saverpggameserver.domain.entities.Player

data class HistoryLineResponse(
    val user: Player,
    val node: Node,
) {
    constructor(historyLineRequest: HistoryLineRequest) : this(
        user = historyLineRequest.user,
        node = historyLineRequest.node,
    )
}
