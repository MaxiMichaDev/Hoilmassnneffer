package com.maximichadev.hoilmassnneffer

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*


class Main : ApplicationAdapter() {

    private lateinit var world: World
    private lateinit var cam: OrthographicCamera
    private lateinit var circle: CircleShape

    private var accumulator: Float = 0F

    private lateinit var debugRenderer: Box2DDebugRenderer


    override fun create() {
        Box2D.init()
        world = World(Vector2(0F, 0F), true)
        world.step(1/60F, 6,2)

        val w = Gdx.graphics.width.toFloat()
        val h = Gdx.graphics.height.toFloat()

        cam = OrthographicCamera(w, h)
        cam.position.set(cam.viewportWidth / 2F, cam.viewportHeight / 2F, 0F)
        cam.update()

        debugRenderer = Box2DDebugRenderer()

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.KinematicBody
        bodyDef.position.set(w / 2, 50F)
        val body = world.createBody(bodyDef)
        body.linearVelocity = Vector2(0F, 50F)

        circle = CircleShape()
        circle.radius = 6f

        val fixtureDef = FixtureDef()
        fixtureDef.shape = circle
        body.createFixture(fixtureDef)
    }

    override fun render() {
        Gdx.gl.glClearColor(0.07f, 0.15f, 0.19f, 1f) //0,07, 0,15, 0,19
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        cam.update()
        debugRenderer.render(world, cam.combined)

        val deltaTime = Gdx.graphics.deltaTime
        doPhysicsStep(deltaTime)

    }

    private fun doPhysicsStep(deltaTime: Float) {
        // max frame time to avoid spiral of death (on slow devices)
        val frameTime = deltaTime.coerceAtMost(0.25f)
        accumulator += frameTime
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
            accumulator -= TIME_STEP
        }
    }

    override fun dispose() {
        circle.dispose()
    }
}
