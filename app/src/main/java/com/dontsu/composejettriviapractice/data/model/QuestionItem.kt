package com.dontsu.composejettriviapractice.data.model


import com.dontsu.composejettriviapractice.data.entity.QuestionEntity
import com.google.gson.annotations.SerializedName

data class QuestionItem(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("choices")
    val choices: List<String>,
    @SerializedName("question")
    val question: String
) {
    fun toEntity(): QuestionEntity {
        return QuestionEntity(
            answer = this.answer,
            category = this.category,
            choices = this.choices,
            question = this.question
        )
    }
}
