package com.xinesoft.pong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.PerspectiveCamera

class Camera {
    val cam = PerspectiveCamera (67.0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    fun create() {
        cam.position.set(0f, 0f, 50f)
        cam.lookAt(0f, 0f, 0f)
        cam.near = 1f
        cam.far = 300f
        cam.update()
    }
}
