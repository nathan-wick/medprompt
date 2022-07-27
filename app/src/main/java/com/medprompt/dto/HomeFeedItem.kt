package com.medprompt.dto

data class HomeFeedItem(
    val documentId: String,
    val screenType: ScreenType,
    val title: String,
    val datetime: String
)
