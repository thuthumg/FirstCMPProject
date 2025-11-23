package org.example.firstcmpproject.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob

object AppCoroutineScope {

    val supervisorJob = SupervisorJob()

    val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)
}