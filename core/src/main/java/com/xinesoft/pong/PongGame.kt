package com.xinesoft.pong

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class PongGame : ApplicationAdapter() {
    lateinit var camera: Camera
    lateinit var paddleA: Paddle
    lateinit var paddleB: Paddle

    lateinit var ball: Ball
    lateinit var modelBatch: ModelBatch
    lateinit var environment: Environment
    lateinit var spriteBatch: SpriteBatch

    lateinit var world: World
    var overlapedA = false
    var overlapedB = false
    var scoreA = 0
    var scoreB = 0

    lateinit var font: BitmapFont


    override fun create() {
        spriteBatch = SpriteBatch()
        font = BitmapFont()
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        font.data.scale(4f)
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

        world = World()

        world.setPosition(0f, 0f, -10f)
    }

    override fun render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        camera.cam.update()
        paddleA.update(true)
        paddleB.update(false)
        ball.update()


        modelBatch.begin(camera.cam)
        //modelBatch.render(world.instance, environment)
        modelBatch.render(paddleA.instance, environment)
        modelBatch.render(paddleB.instance, environment)
        modelBatch.render(ball.instance, environment)
        modelBatch.end()

        spriteBatch.begin()
        font.draw(spriteBatch, ball.scoreA.toString(), 150f, Gdx.graphics.height.toFloat() - 20f)
        font.draw(spriteBatch, ball.scoreB.toString(), Gdx.graphics.width.toFloat() - 190f, Gdx.graphics.height.toFloat() - 20f)
        spriteBatch.end()

        val overlapsA = Intersector.overlaps(paddleA.rect, ball.rect)
        val overlapsB = Intersector.overlaps(paddleB.rect, ball.rect)

        if (overlapsA && !overlapedA) {
            ball.direction *= -1
            ball.velocity += 0.03f
            ball.velocityY += 0.05f
            overlapedA = true
            overlapedB = false
        }

        if (overlapsB && !overlapedB) {
            ball.direction *= -1
            ball.velocity += 0.03f
            ball.velocityY += 0.05f
            overlapedB = true
            overlapedA = false
        }

    }

    override fun dispose() {
        modelBatch.dispose()
        ball.dispose()
        paddleA.dispose()
        paddleB.dispose()
    }
}
