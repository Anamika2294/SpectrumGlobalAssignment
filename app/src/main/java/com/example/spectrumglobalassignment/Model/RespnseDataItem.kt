package com.example.spectrumglobalassignment.Model

data class RespnseDataItem(
    val _id: String,
    val about: String,
    val company: String,
    val logo: String,
    val members: List<Member>,
    val website: String
)