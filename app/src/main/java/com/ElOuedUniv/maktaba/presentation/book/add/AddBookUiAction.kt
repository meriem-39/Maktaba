package com.ElOuedUniv.maktaba.presentation.book.add

import android.net.Uri
import java.net.URL

sealed class AddBookUiAction {
    data class OnImagePicked(val imageURL: String): AddBookUiAction()
    data class OnTitleChange(val title: String) : AddBookUiAction()
    data class OnIsbnChange(val isbn: String) : AddBookUiAction()
    data class OnPagesChange(val pages: String) : AddBookUiAction()

    data class OnProgressChange(val progress: Float) : AddBookUiAction()

    object OnAddClick : AddBookUiAction()
}
