package com.astrofrog.saverpggameserver.application.web.controllers

import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineRequest
import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineUpdateRequest
import com.astrofrog.saverpggameserver.application.web.payloads.response.HistoryLineResponse
import com.astrofrog.saverpggameserver.domain.services.HistoryLineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/history-line")
class HistoryLineController(
    private val historyLineService: HistoryLineService,
) {

    @PostMapping
    fun create(
        @RequestBody historyLineRequest: HistoryLineRequest,
        uriComponentsBuilder: UriComponentsBuilder,
    ): ResponseEntity<HistoryLineResponse> {
        val uri = uriComponentsBuilder.path("/history-line/{playerId}")
            .buildAndExpand(historyLineRequest.user.id).toUri()

        historyLineService.create(historyLineRequest)

        return ResponseEntity.created(uri).body(HistoryLineResponse(historyLineRequest))
    }

    @PutMapping("/{playerId}")
    fun updateSaveNode(
        @PathVariable playerId: String,
        @RequestBody historyLineUpdateRequest: HistoryLineUpdateRequest,
    ) {
        historyLineService.updateSaveNode(playerId, historyLineUpdateRequest)
    }
}
