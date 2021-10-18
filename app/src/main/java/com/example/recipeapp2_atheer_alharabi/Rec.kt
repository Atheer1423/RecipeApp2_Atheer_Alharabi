package com.example.recipeapp2_atheer_alharabi
import com.google.gson.annotations.SerializedName
class Rec {

    var data: List<RecDetails>? = null

    class RecDetails {

        @SerializedName("title")
        var title: String? = null

        @SerializedName("author")
        var author: String? = null
        @SerializedName("ingredients")
        var ingre: String? = null

        @SerializedName("instructions")
        var instru: String? = null

        constructor(title: String?, author: String?,ingre: String?, instru: String?) {
            this.title = title
            this.author = author
            this.ingre = ingre
            this.instru = instru
        }
    }
}