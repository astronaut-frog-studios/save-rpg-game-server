package com.astrofrog.saverpggameserver.domain.exceptions

class UpdateSaveNodeException(reason: String) : ApiException(
    reason = reason,
)
