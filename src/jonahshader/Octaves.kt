package jonahshader

import kotlin.math.pow

class Octaves(numOctaves: Int) {
    private val octaves = mutableListOf<Octave>()
    private var maxValue = 0f

    init {
        for (i in 0 until numOctaves) {
            octaves += Octave(2f.pow(i), .5f.pow(i), (Math.random() * Int.MAX_VALUE).toInt(), (Math.random() * Int.MAX_VALUE).toInt())
            maxValue += .5f.pow(i)
        }
    }

    fun get(x: Float, y: Float) : Float {
        var output = 0f
        for (o in octaves)
            output += o.get(x, y)
        return output / maxValue
    }
}