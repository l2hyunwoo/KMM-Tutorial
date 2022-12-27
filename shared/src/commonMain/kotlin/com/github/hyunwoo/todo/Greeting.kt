package com.github.hyunwoo.todo

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet() = "Guess what it is! > ${platform.name.reversed()}!" +
            "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎆"
}