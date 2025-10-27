package org.example.firstcmpproject.core.persistence

internal class Database(private val sqlDriverFactory: DatabaseDriverFactory) {
    private val appDatabase = AppDatabase(driver = sqlDriverFactory.createDriver())
    val dbQuery = appDatabase.appDatabaseQueries

}