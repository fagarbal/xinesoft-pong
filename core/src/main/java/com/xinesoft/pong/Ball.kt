package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3


class Ball {
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 2.5f, 2.5f,
            Material(ColorAttribute.createDiffuse(Color.YELLOW)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)
    var direction = -1
    var position = instance.transform.getTranslation(Vector3())
    var rect = Rectangle(position.x, position.y, 2.5f, 2.5f)
    var velocity = 0.5f
    var velocityY = 0f
    var directionY = -1
    var scoreA = 0
    var scoreB = 0

    fun update() {
        position = instance.transform.getTranslation(Vector3())

        if (position.y > 30) {
            directionY *= -1
        }

        if (position.y < -30) {
            directionY *= -1
        }


        if (position.x > 50) {
            scoreA++
        }

        if (position.x < -50) {
            scoreB++
        }

        if (position.x > 50 || position.x < -50) {
            position.x = 0f
            position.y = 0f
            velocity = 0.5f
            velocityY = 0f
        }
        position.x += velocity * direction
        position.y += velocityY * directionY
        rect.setCenter(position.x, position.y)

        instance.transform.setTranslation(position)
    }

    fun dispose() {
        model.dispose()
    }

}