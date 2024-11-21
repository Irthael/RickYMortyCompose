package com.dani.composerickandmorty.utils.converter

import androidx.room.TypeConverter
import com.dani.domain.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OriginConverter {

    @TypeConverter
    fun urlItemToString(item: Origin): String? {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun stringToUrlItem(stringList: String?): Origin? {
        return if (stringList == null) null
        else Gson().fromJson(stringList, object : TypeToken<Origin>() {}.type)
    }
}
