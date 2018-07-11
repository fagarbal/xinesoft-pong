package com.xinesoft.pong

import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.collision.BoundingBox

class Paddle (color: Color, val cam: PerspectiveCamera){
    var modelBuilder = ModelBuilder()
    var model = modelBuilder.createBox(2.5f, 10f, 1f,
            Material(ColorAttribute.createDiffuse(color)),
            Usage.Position.toLong() or Usage.Normal.toLong())
    var instance = ModelInstance(model)
    var position = instance.transform.getTranslation(Vector3())
    var rect = Rectangle(position.x, position.y, 2.5f, 10f)

    fun update(mayor: Boolean) {
        position = instance.transform.getTranslation(Vector3())
        rect.setCenter(position.x, position.y)

        for (i in 0..1) {
            if(Gdx.input.isTouched(i) && if (mayor) Gdx.input.getX(i) <= Gdx.graphics.width / 2 else Gdx.input.getX(i) > Gdx.graphics.width / 2 ) {
                val clickPos = Vector3()
                clickPos.set(0f, Gdx.input.getY(i).toFloat(), 0f)

                cam.unproject(clickPos)
                instance.transform.setToTranslation(position.x, clickPos.y * cam.position.z, position.z)

            }
        }
    }

    fun setPosition(x: Float, y: Float, z: Float) {
        instance.transform.setToTranslation(x, y, z)
    }

    fun dispose() {
        model.dispose()
    }
}