package com.astrofrog.saverpggameserver.domain.services

import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineRequest
import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineUpdateRequest

interface HistoryLineService {
    fun create(historyLineRequest: HistoryLineRequest)
    fun updateSaveNode(playerId: String, historyLineUpdateRequest: HistoryLineUpdateRequest)
}
