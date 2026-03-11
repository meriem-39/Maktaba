package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category

class CategoryRepositoryImpl : CategoryRepository {

    private val categoriesList = listOf<Category>(
        Category(
            id = "1",
            name = "Programming",
            description = "Books about software development and coding"
        ),
        Category(
            id = "2",
            name = "Algorithms",
            description = "Books about algorithms and data structures"
        ),
        Category(
            id = "3",
            name = "Databases",
            description = "Books about database design and management"
        ),
        Category(
            id = "4",
            name = "Cybersecurity",
            description = "Books about cybersecurity"
        ),
        Category(
            id = "5",
            name = "Computer Networks",
            description = "Books about computer networks"
        ),
        // Add 2 more categories here
    )

    override fun getAllCategories(): List<Category> {
        return categoriesList
    }

    override fun getCategoryById(id: String): Category? {

        return categoriesList.find { it.id == id }
    }
}
