package com.ElOuedUniv.maktaba.presentation.book.add

import android.R
import java.net.URL

data class AddBookUiState(
    val title: String = "",
    val isbn: String = "",
    val nbPages: String = "",
    val imageURL: String="",
    val progress: Float= 0.9f,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
