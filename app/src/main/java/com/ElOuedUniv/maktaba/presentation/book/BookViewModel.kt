package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.ElOuedUniv.maktaba.presentation.book.BookUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow

@HiltViewModel
class BookViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase,
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<BookUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            getBooksUseCase()
                .catch {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .collect { bookList ->

                    _uiState.update {
                        it.copy(
                            books = bookList,
                            isLoading = false
                        )
                    }

                }
        }
    }

    /**
     * TODO: Exercise 3 - Handle UI Actions
     */
    fun onAction(action: BookUiAction) {
        when (action) {

            BookUiAction.RefreshBooks -> refreshBooks()

            BookUiAction.OnAddBookClick -> {
                _uiState.update { it.copy(isAddingBook = true) }
            }

            BookUiAction.OnDismissAddBook -> {
                _uiState.update { it.copy(isAddingBook = false) }
            }

            is BookUiAction.OnAddBookConfirm -> {
                viewModelScope.launch {

                    val book = Book(
                        title = action.title,
                        isbn = action.isbn,
                        nbPages = action.nbPages
                    )

                    // إضافة الكتاب
                    addBookUseCase(book)

                    // غلق الـ dialog
                    _uiState.update { it.copy(isAddingBook = false) }

                    // إرسال Event (اختياري لكن مهم)
                    _uiEvent.emit(
                        BookUiEvent.ShowSnackbar("Book added successfully")
                    )
                }
            }
        }
    }

        fun refreshBooks() {
            loadBooks()
        }
    }


