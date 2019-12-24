package vinay.com.newsapidemoapp.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vinay.com.newsapidemoapp.model.Source


public class SourceConverter {
    @TypeConverter
    fun fromString(value: String): Source {
        val listType = object : TypeToken<Source>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromSource(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}