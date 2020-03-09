package jonahshader

import processing.core.PApplet
import java.util.stream.IntStream
import kotlin.math.pow
import kotlin.system.measureTimeMillis

class App : PApplet() {
//    val octave = Octave(0.25f, 1f, 50)
//    val octaveSet = Octaves(10)
    val octaveSet = Octaves3D(10)
    var scale = .04f

    override fun settings() {
        size(320, 320)
//        fullScreen()
    }

    override fun setup() {
        frameRate(165f)
    }

    override fun draw() {
        val deltaTime = measureTimeMillis {
            loadPixels()
            IntStream.range(0, height).parallel().forEach {
                val y = it
                for (x in 0 until width) {
                    //            pixels[x + y * width] = color(bilinear(x.toFloat(), y.toFloat(), 0f, width.toFloat(), 0f, height.toFloat(), 1f, 0f, 0f, 1f) * 255f)
                    val b = octaveSet.get(x.toFloat() * scale, y.toFloat() * scale, frameCount * scale * 0.33f)
                    //            if (b >= 1f)
                    //                println(b)
                    pixels[x + y * width] = color(255 * b)
                }
            }
            updatePixels()

//            scale *= .99f
        }

        println(deltaTime)

    }
}