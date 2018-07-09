package com.xinesoft.pong.desktop

import com.badlogic.gdx.Files.FileType
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.xinesoft.pong.PongGame

/** Launches the desktop (LWJGL) application.  */
object DesktopLauncher {

    private val defaultConfiguration: LwjglApplicationConfiguration
        get() {
            val configuration = LwjglApplicationConfiguration()
            configuration.title = "pong"
            configuration.width = 640
            configuration.height = 480
            for (size in intArrayOf(128, 64, 32, 16)) {
                configuration.addIcon("libgdx$size.png", FileType.Internal)
            }
            return configuration
        }

    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication(): LwjglApplication {
        return LwjglApplication(PongGame(), defaultConfiguration)
    }
}