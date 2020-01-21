package com.maximichadev.hoilmassnneffer.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.maximichadev.hoilmassnneffer.Main

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = 108 * 3
        config.height = 234 * 3
        LwjglApplication(Main(), config)
    }
}
