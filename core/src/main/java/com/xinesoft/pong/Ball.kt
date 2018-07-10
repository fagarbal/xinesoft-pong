package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.collision.BoundingBox


class Ball {
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 2.5f, 2.5f,
            Material(ColorAttribute.createDiffuse(Color.YELLOW)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)
    var direction = -1
    var position = instance.transform.getTranslation(Vector3())
    var oldPosition = position

    fun update() {
        val position = instance.transform.getTranslation(Vector3())
        oldPosition = Vector3(position)

        if (position.x > 45) direction = -1
        if (position.x < -45) direction = 1

        position.x += 0.5f * direction


        instance.transform.setTranslation(position)

    }

    fun position() {
        position
    }

    fun direction(): Float {
        var angle = Math.toDegrees(Math.atan2(position.y.toDouble() - oldPosition.y, position.x.toDouble() - oldPosition.x)).toFloat()

        if (angle < 0) {
            angle += 360f
        }

        return angle
    }

}