package com.astrofrog.saverpggameserver.domain.entities

import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineRequest
import nonapi.io.github.classgraph.json.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("history_line")
data class HistoryLineDocument(
    @Id
    val _id: UUID,

    val user: Player,
    val node: Node,
) {
    constructor(historyLineRequest: HistoryLineRequest) : this(
        _id = UUID.randomUUID(),
        user = historyLineRequest.user,
        node = historyLineRequest.node,
    )
}

data class Node(
    val id: Int,
    val name: String,
)
