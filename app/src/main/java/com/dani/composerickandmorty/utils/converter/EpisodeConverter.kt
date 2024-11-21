package com.dani.composerickandmorty.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EpisodeConverter {

    @TypeConverter
    fun episodeListToString(list: List<String>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToEpisodeList(stringList: String?): List<String>? {
        return if (stringList == null) null
        else Gson().fromJson(stringList, object : TypeToken<List<String>>() {}.type)
    }
}
