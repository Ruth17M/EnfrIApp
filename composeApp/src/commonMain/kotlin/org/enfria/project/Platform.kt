package org.enfria.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform