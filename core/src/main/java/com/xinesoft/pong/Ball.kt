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

    fun update() {
        position = instance.transform.getTranslation(Vector3())

        if (position.x > 50) position.x = 0f
        if (position.x < -50) position.x = 0f

        position.x += 0.5f * direction

        instance.transform.setTranslation(position)
        rect.setCenter(position.x, position.y)
    }

}