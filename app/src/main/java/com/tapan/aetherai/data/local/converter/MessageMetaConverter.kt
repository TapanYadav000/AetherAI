package com.tapan.aetherai.data.local.converter

import androidx.room.TypeConverter
import com.tapan.aetherai.domain.model.MessageMeta
import kotlinx.serialization.json.Json

class MessageMetaConverter {

    @TypeConverter
    fun fromMessageMeta(
        meta: MessageMeta
    ): String {

        return Json.encodeToString(
            MessageMeta.serializer(),
            meta
        )
    }

    @TypeConverter
    fun toMessageMeta(
        value: String
    ): MessageMeta {

        return Json.decodeFromString(
            MessageMeta.serializer(),
            value
        )
    }
}