package jonahshader

import processing.core.PApplet

class App : PApplet() {
//    val octave = Octave(0.25f, 1f, 50)
    val octaveSet = Octaves(14)
    var scale = 1f

    override fun settings() {
        size(640, 640)
//        fullScreen()
    }

    override fun setup() {

    }

    override fun draw() {
        loadPixels()
        for (y in 0 until height) for (x in 0 until width) {
//            pixels[x + y * width] = color(bilinear(x.toFloat(), y.toFloat(), 0f, width.toFloat(), 0f, height.toFloat(), 1f, 0f, 0f, 1f) * 255f)
            val b = octaveSet.get(x.toFloat() * scale, y.toFloat() * scale)
//            if (b >= 1f)
//                println(b)
            pixels[x + y * width] = color(255 * b)
        }
        updatePixels()

        scale *= .9f
    }
}