package org.example.firstcmpproject.core.persistence

object DatabaseProvider {
    internal lateinit var database: Database

    fun initDatabase(databaseDriverFactory: DatabaseDriverFactory){
        database = Database(databaseDriverFactory)
    }
}