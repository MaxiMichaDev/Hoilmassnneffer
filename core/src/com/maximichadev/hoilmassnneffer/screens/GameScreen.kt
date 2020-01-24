package com.maximichadev.hoilmassnneffer.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.maximichadev.hoilmassnneffer.GameModel
import com.maximichadev.hoilmassnneffer.Main

class GameScreen(parent: Main) : Screen {

    private var model = GameModel()
    private var cam: OrthographicCamera

    private var debugRenderer = Box2DDebugRenderer()

    init {
        val w = Gdx.graphics.width.toFloat()
        val h = Gdx.graphics.height.toFloat()

        cam = OrthographicCamera(w, h)
        cam.position.set(cam.viewportWidth / 2F, cam.viewportHeight / 2F, 0F)
        cam.update()
    }


    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.07f, 0.15f, 0.19f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        cam.update()
        debugRenderer.render(model.world, cam.combined)
        model.doPhysicsStep(delta)
    }

    override fun dispose() {
        model.dispose()
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }
}