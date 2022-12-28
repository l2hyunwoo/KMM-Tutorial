package com.github.hyunwoo.todo

import com.github.hyunwoo.todo.entity.RocketLaunch
import com.github.hyunwoo.todo.network.SpaceXApi
import com.github.hyunwoo.todo.shared.cache.space.DatabaseDriverFactory
import com.github.hyunwoo.todo.shared.cache.space.SpaceDatabaseWrapper

class SpaceXSDK(
    factory: DatabaseDriverFactory
) {
    private val database = SpaceDatabaseWrapper(factory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}
