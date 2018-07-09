package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage

class Ball {
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 2.5f, 2.5f,
            Material(ColorAttribute.createDiffuse(Color.YELLOW)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)

    fun update() {

    }
}