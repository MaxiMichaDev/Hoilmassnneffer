package com.maximichadev.hoilmassnneffer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

class GameModel {

    init {
        Box2D.init()
    }

    var world: World = World(Vector2(0F, 0F), true)

    private var player: CircleShape
    private var accumulator: Float = 0F


    init {
        world.setContactListener(CustomContactListener())

        val bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.KinematicBody
        bodyDef.position.set((Gdx.graphics.width / 2).toFloat(), 50F)
        val body = world.createBody(bodyDef)
        body.linearVelocity = Vector2(0F, 100F)

        player = CircleShape()
        player.radius = 6f

        val fixtureDef = FixtureDef()
        fixtureDef.shape = player
        body.createFixture(fixtureDef)
    }

    fun doPhysicsStep(deltaTime: Float) {
        // max frame time to avoid spiral of death (on slow devices)
        val frameTime = deltaTime.coerceAtMost(0.25f)
        accumulator += frameTime
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
            accumulator -= TIME_STEP
        }
    }

    class CustomContactListener : ContactListener {

        override fun beginContact(contact: Contact?) {
//            contact?.fixtureA?.body
        }
        override fun endContact(contact: Contact?) {

        }

        override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
        }

        override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
        }

    }

    fun dispose() {
        player.dispose()
    }
}
