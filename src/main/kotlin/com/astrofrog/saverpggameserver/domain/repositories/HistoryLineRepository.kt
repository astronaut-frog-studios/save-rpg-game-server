package com.astrofrog.saverpggameserver.domain.repositories

import com.astrofrog.saverpggameserver.domain.entities.HistoryLineDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface HistoryLineRepository : MongoRepository<HistoryLineDocument, UUID> {
    fun findByUserId(id: String): HistoryLineDocument
}
