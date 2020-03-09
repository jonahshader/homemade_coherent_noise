package jonahshader

import kotlin.math.abs
import kotlin.math.sin

class Octave3D(private val scale: Float, private val magnitude: Float, private val xOffset: Int, private val yOffset: Int, private val zOffset: Int) {

    fun get(x: Float, y: Float, z: Float) : Float {
        var xf = x * scale
        var yf = y * scale
        var zf = z * scale

        xf += xOffset / Int.MAX_VALUE.toFloat()
        yf += yOffset / Int.MAX_VALUE.toFloat()
        zf += zOffset / Int.MAX_VALUE.toFloat()

        val xMin = kotlin.math.floor(xf).toInt()
        val yMin = kotlin.math.floor(yf).toInt()
        val zMin = kotlin.math.floor(zf).toInt()

        val xMax = xMin + 1
        val yMax = yMin + 1
        val zMax = zMin + 1

        val xp = xf - xMin
        val yp = yf - yMin
        val zp = zf - zMin

        val x0y0z0 = posToVal(xMin, yMin, zMin)
        val x1y0z0 = posToVal(xMax, yMin, zMin)
        val x0y1z0 = posToVal(xMin, yMax, zMin)
        val x1y1z0 = posToVal(xMax, yMax, zMin)

        val x0y0z1 = posToVal(xMin, yMin, zMax)
        val x1y0z1 = posToVal(xMax, yMin, zMax)
        val x0y1z1 = posToVal(xMin, yMax, zMax)
        val x1y1z1 = posToVal(xMax, yMax, zMax)

        val outPreMag = trilinear(xp, yp, zp, x0y0z0, x1y0z0, x0y1z0, x1y1z0, x0y0z1, x1y0z1, x0y1z1, x1y1z1)
        return outPreMag * magnitude
    }

    private fun posToVal(x: Int, y: Int, z: Int) : Float {
//        var seedOut = (((x + xOffset).betterHash() + (y + yOffset).betterHash()) + (z + zOffset).betterHash()).betterHash().nextRand()
//        var xs = (x + xOffset).betterHash().nextRand()
//        var ys = (y + yOffset).betterHash().nextRand().nextRand()
//        var zs = (z + zOffset).betterHash().nextRand().nextRand().nextRand()



//        val sum = (xs + ys + zs).betterHash().nextRand()
        val bigShid = abs(sin((x + xOffset % 1853) * 100f + (y + yOffset % 10239) * 12721 + (z + zOffset % 18901) * 6574f) * 5647f)


        return bigShid - bigShid.toInt()


//        return (sum / (2*Int.MAX_VALUE.toFloat())) + .5f
    }
}