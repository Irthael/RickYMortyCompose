package com.dani.composerickandmorty.utils.converter

import androidx.room.TypeConverter
import com.dani.domain.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationConverter {

    @TypeConverter
    fun urlItemToString(item: Location): String? {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun stringToUrlItem(stringList: String?): Location? {
        return if (stringList == null) null
        else Gson().fromJson(stringList, object : TypeToken<Location>() {}.type)
    }
}
