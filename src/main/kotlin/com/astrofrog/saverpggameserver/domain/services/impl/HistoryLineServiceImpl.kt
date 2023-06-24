package com.astrofrog.saverpggameserver.domain.services.impl

import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineRequest
import com.astrofrog.saverpggameserver.application.web.payloads.requests.HistoryLineUpdateRequest
import com.astrofrog.saverpggameserver.domain.entities.HistoryLineDocument
import com.astrofrog.saverpggameserver.domain.exceptions.UpdateSaveNodeException
import com.astrofrog.saverpggameserver.domain.repositories.HistoryLineRepository
import com.astrofrog.saverpggameserver.domain.services.HistoryLineService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service

@Service
class HistoryLineServiceImpl(
    private val historyLineRepository: HistoryLineRepository,
) : HistoryLineService {

    override fun create(historyLineRequest: HistoryLineRequest) {
        try {
            historyLineRepository.save(HistoryLineDocument(historyLineRequest)).also {
                log.info("New save to player id=${it.user.id} created")
            }
        } catch (ex: Exception) {
            log.error("Error while trying create a new save to player id=${historyLineRequest.user.id}")
        }
    }

    override fun updateSaveNode(playerId: String, historyLineUpdateRequest: HistoryLineUpdateRequest) {
        try {
            historyLineRepository.findByUserId(playerId).let {
                log.info("save player with _id=${it._id} was found")

                historyLineRepository.save(
                    it.copy(
                        node = historyLineUpdateRequest.node,
                    ),
                ).also {
                    log.info("player save updated")
                }
            }
        } catch (ex: Exception) {
            log.error("Error while trying update save node of player with id=$playerId. Error: ${ex.message!!}")
            throw UpdateSaveNodeException(ex.message!!)
        }
    }

    companion object {
        val log: Logger = LogManager.getLogger(HistoryLineServiceImpl::class.java)
    }
}
