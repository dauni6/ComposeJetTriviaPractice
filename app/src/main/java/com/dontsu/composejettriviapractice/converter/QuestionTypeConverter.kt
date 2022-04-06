package com.dontsu.composejettriviapractice.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

object QuestionTypeConverter {

    // DB에 저장할 때 객체를 저장하지 않고 MySQL에서도 보통 INTEGER, VARCHAR를 사용하듯이
    // 저장할때 String형이든 int형이든 바꾸어서 넣어주어야 한다.
    // 네트워크 통신을 통해 DB에 전달되는 쿼리스트링은 항상 String형이다.
    // 따라서 DB에 저장할때도 String형이고 받아올때는 다시 DB에 저장된 데이터를 객체로 변환해주어야 한다.
    // 현재는 네트워크로 저장하는게 아니지만, DB에 보낼때는 String으로 저장할 때를 떠올려보자.

    var gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?) = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = gson.fromJson(value, Array<String>::class.java).toList()

}
