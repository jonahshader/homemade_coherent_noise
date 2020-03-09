package jonahshader

class Octave(private val scale: Float, private val magnitude: Float, private val xOffset: Int, private val yOffset: Int) {

    fun get(x: Float, y: Float) : Float {
        val xf = x * scale
        val yf = y * scale

        val xMin = kotlin.math.floor(xf).toInt()
        val xMax = xMin + 1
        val yMin = kotlin.math.floor(yf).toInt()
        val yMax = yMin + 1

        val xPercent = xf - xMin
        val yPercent = yf - yMin

        val topLeftVal = posToVal(xMin, yMax)
        val topRightVal = posToVal(xMax, yMax)
        val botLeftVal = posToVal(xMin, yMin)
        val botRightVal = posToVal(xMax, yMin)

        val outPreMag = bilinear(xPercent, yPercent, topLeftVal, topRightVal, botLeftVal, botRightVal)
        return outPreMag * magnitude
    }

    private fun posToVal(x: Int, y: Int) : Float {

//        var seedOut = (.betterHash() + y.betterHash()).nextRand().nextRand()
        var seedOut = ((x + xOffset).betterHash() + (y + yOffset)).betterHash().nextRand()

//        for (i in 1..100) seedOut = seedOut.nextRand()

        return (seedOut / (2*Int.MAX_VALUE.toFloat())) + .5f
    }

}


