package com.maximichadev.hoilmassnneffer

import com.badlogic.gdx.Game
import com.maximichadev.hoilmassnneffer.screens.GameScreen


class Main : Game() {

    override fun create() {
        setScreen(GameScreen(this))
    }
}
