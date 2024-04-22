package com.example.harrypotter.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class CharacterDetailsResponse : ArrayList<CharacterDetailsResponse.CharacterDetailsResponseItem>(){
    data class CharacterDetailsResponseItem(
        @SerializedName("actor")
        @Expose
        val actor: String,
        @SerializedName("alive")
        @Expose
        val alive: Boolean,
        @SerializedName("alternate_actors")
        @Expose
        val alternateActors: List<Any>,
        @SerializedName("alternate_names")
        @Expose
        val alternateNames: List<String>,
        @SerializedName("ancestry")
        @Expose
        val ancestry: String,
        @SerializedName("dateOfBirth")
        @Expose
        val dateOfBirth: String,
        @SerializedName("eyeColour")
        @Expose
        val eyeColour: String,
        @SerializedName("gender")
        @Expose
        val gender: String,
        @SerializedName("hairColour")
        @Expose
        val hairColour: String,
        @SerializedName("hogwartsStaff")
        @Expose
        val hogwartsStaff: Boolean,
        @SerializedName("hogwartsStudent")
        @Expose
        val hogwartsStudent: Boolean,
        @SerializedName("house")
        @Expose
        val house: String,
        @SerializedName("id")
        @Expose
        val id: String,
        @SerializedName("image")
        @Expose
        val image: String,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("patronus")
        @Expose
        val patronus: String,
        @SerializedName("species")
        @Expose
        val species: String,
        @SerializedName("wand")
        @Expose
        val wand: Wand,
        @SerializedName("wizard")
        @Expose
        val wizard: Boolean,
        @SerializedName("yearOfBirth")
        @Expose
        val yearOfBirth: Int
    ) {
        data class Wand(
            @SerializedName("core")
            @Expose
            val core: String,
            @SerializedName("length")
            @Expose
            val length: Any?,
            @SerializedName("wood")
            @Expose
            val wood: String
        )
    }
}