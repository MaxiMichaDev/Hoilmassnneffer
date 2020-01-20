package com.maximichadev.hoilmassnneffer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2D
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World

class Main : ApplicationAdapter() {
//    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture
    private lateinit var world: World

    private lateinit var cam: OrthographicCamera

    private var accumultor: Float = 0F

    private lateinit var debugRenderer: Box2DDebugRenderer

    override fun create() {
        Box2D.init()
        world = World(Vector2(0F, 0F), true)
        world.step(1/60F, 6,2)

        val w = Gdx.graphics.width
        val h = Gdx.graphics.height

        cam = OrthographicCamera(30F, 30F * h / w)
        cam.position.set(cam.viewportWidth / 2F, cam.viewportHeight / 2F, 0F)
        cam.update()

        debugRenderer = Box2DDebugRenderer()
//        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
//        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//        batch.begin()
//        batch.draw(img, 0f, 0f)
//        batch.end()
        cam.update()
        debugRenderer.render(world, cam.combined)

    }

    override fun dispose() {
//        batch.dispose()
        img.dispose()
    }
}
