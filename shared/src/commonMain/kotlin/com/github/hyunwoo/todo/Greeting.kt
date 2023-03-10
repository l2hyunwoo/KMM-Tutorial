package com.github.hyunwoo.todo

import com.github.hyunwoo.todo.entity.RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    fun greet() = "Guess what it is! > ${platform.name.reversed()}!" +
            "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎆"

    @Throws(Exception::class)
    suspend fun greeting(): String {
        val rockets: List<RocketLaunch> = httpClient
            .get("https://api.spacexdata.com/v4/launches")
            .body()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎆" +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }
}