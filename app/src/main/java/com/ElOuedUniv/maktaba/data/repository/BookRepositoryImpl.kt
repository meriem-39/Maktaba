package com.ElOuedUniv.maktaba.data.repository

import android.R.attr.progress
import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(isbn = "11111", title = "Clean Code", nbPages = 10, imageURL ="https://picsun.photos/200/301" ,progress= 0.9f ),
        Book(isbn = "22222", title = "The Pragmatic Programmer", nbPages = 0,imageURL ="https://picsun.photos/200/302" ,progress= 0.9f ),
        Book(isbn = "33333", title = "Design Patterns", nbPages = 90,imageURL ="https://picsun.photos/200/303" ,progress= 0.9f ),
        Book(isbn = "44444", title = "Refactoring", nbPages = 140,imageURL ="https://picsun.photos/200/304" ,progress= 0.9f ),
        Book(isbn = "55555", title = "Head First Design Patterns", nbPages = 45,imageURL ="https://picsun.photos/200/300" ,progress= 0.9f )
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }
    
    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(2000) // Simulate delay
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }
}
