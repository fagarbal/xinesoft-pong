package com.xinesoft.pong

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight

class PongGame : ApplicationAdapter() {

    lateinit var camera: Camera
    lateinit var paddleA: Paddle
    lateinit var paddleB: Paddle

    lateinit var ball: Ball
    lateinit var modelBatch: ModelBatch
    lateinit var environment: Environment

    override fun create () {
        camera = Camera()
        paddleA = Paddle(Color.RED, camera.cam)
        paddleB = Paddle(Color.GREEN, camera.cam)

        ball = Ball()
        modelBatch = ModelBatch()
        environment = Environment()

        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f))

        camera.create()

        paddleA.setPosition(-40f, 0f, 0f)
        paddleB.setPosition(40f, 0f, 0f)
    }

    override fun render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        camera.cam.update()
        paddleA.update(true)
        paddleB.update(false)
        ball.update()

        checkBallCollision()

        modelBatch.begin(camera.cam)
        modelBatch.render(paddleA.instance, environment)
        modelBatch.render(paddleB.instance, environment)
        modelBatch.render(ball.instance, environment)
        modelBatch.end()
    }

    override fun dispose () {
        modelBatch.dispose()
        paddleA.dispose()
        paddleB.dispose()
    }

    fun checkBallCollision () {
        val biggerBox = getBiggerBox();
        if(isBallTouchingPaddel(biggerBox)){
            changeDirectionOfBall();
        }
    }
}