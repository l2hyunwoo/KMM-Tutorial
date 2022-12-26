package com.github.hyunwoo.todo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform