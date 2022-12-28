package com.github.hyunwoo.todo.shared.cache.space

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = SpaceDatabase.Schema,
            context = context,
            name = "SpaceDatabase.db"
        )
    }
}
