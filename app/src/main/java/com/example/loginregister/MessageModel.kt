package com.example.loginregister

data class MessageModel(
    val messageText: String,
    val messageTime: Long,
    val sender: String,
    val actionMessage: Boolean
)