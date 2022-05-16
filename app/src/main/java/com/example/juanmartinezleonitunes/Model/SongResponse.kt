package com.example.juanmartinezleonitunes.Model

data class SongResponse(
    var results: List<Song>
)

data class Song(
    val artistName: String,
    val trackName: String,
    val trackPrice: Double,
    val artworkUrl60: String,
    val previewUrl: String
)


