package org.dor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform