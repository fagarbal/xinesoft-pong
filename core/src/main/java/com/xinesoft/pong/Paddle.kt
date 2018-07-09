package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.Gdx

class Paddle(color: Color) {
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 10f, 1f,
            Material(ColorAttribute.createDiffuse(color)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)

    fun update(up: Int, down: Int) {
        if(Gdx.input.isKeyPressed(up)) {
            instance.transform.translate(0f, 1f, 0f)
        } else if(Gdx.input.isKeyPressed(down)) {
            instance.transform.translate(0f, -1f, 0f)
        }
    }

    fun setPosition(x: Float, y: Float, z: Float) {
        instance.transform.translate(x, y, z)
    }

    fun dispose() {
        model.dispose()
    }
}