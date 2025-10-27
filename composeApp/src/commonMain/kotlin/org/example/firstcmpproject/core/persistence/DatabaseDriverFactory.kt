package org.example.firstcmpproject.core.persistence

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {

    fun createDriver() : SqlDriver
}