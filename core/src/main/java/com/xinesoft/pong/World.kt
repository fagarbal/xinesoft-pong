package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.math.Vector3

class World (){
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 75f, 1f,
            Material(ColorAttribute.createDiffuse(Color.WHITE)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)

    fun setPosition(x: Float, y: Float, z: Float) {
        instance.transform.setToTranslation(x, y, z)
    }

    fun dispose() {
        model.dispose()
    }
}