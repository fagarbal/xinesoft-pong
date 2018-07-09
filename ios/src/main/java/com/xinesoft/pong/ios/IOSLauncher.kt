package com.xinesoft.pong.ios

import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication

import com.badlogic.gdx.backends.iosrobovm.IOSApplication
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration
import com.xinesoft.pong.PongGame

/** Launches the iOS (RoboVM) application.  */
class IOSLauncher : IOSApplication.Delegate() {
    override fun createApplication(): IOSApplication {
        val configuration = IOSApplicationConfiguration()
        return IOSApplication(PongGame(), configuration)
    }

    companion object {

        @JvmStatic
        fun main(argv: Array<String>) {
            val pool = NSAutoreleasePool()
            UIApplication.main<UIApplication, IOSLauncher>(argv, null, IOSLauncher::class.java)
            pool.close()
        }
    }
}