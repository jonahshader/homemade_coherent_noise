package jonahshader

import java.lang.Math.PI
import java.lang.Math.sqrt
import kotlin.math.pow
import kotlin.math.tanh

//fun interpFull(input: Float, inMin: Float, inMax: Float, outMin: Float, outMax: Float) : Float {
//    val range = inMax - inMin
//    val inPart = input - inMin
//    var percent = inPart / range
//    percent = sinSmooth(percent)
//    val out = (1-percent) * outMin + (percent * outMax)
//    return out
//}

fun linear(input: Float, min: Float, max: Float) : Float {
    return (1-input) * min + (input * max)
}

fun linearSmooth(input: Float, min: Float, max: Float) : Float {
//    return linear(input, min, max)
    return sinSmooth(linear(input, min, max))
}

//fun interp(input: Float, inMin: Float, inMax: Float) : Float {
//    return (input - inMin) / (inMax - inMin)
//}

//fun bilinear(x: Float, y: Float, xMin: Float, xMax: Float, yMin: Float, yMax: Float, topLeft: Float, topRight: Float, botLeft: Float, botRight: Float): Float {
//    val yPercent = interp(y, yMin, yMax)
//
//    val xTopInterp = interpFull(x, xMin, xMax, topLeft, topRight)
//    val xBotInterp = interpFull(x, xMin, xMax, botLeft, botRight)
//
//    return interpFull(yPercent, 0f, 1f, xBotInterp, xTopInterp)
//}

// x and y from 0 to 1
fun bilinear(x: Float, y: Float, x0y0: Float, x1y0: Float, x0y1: Float, x1y1: Float): Float {
    val y0 = linearSmooth(x, x0y0, x1y0)
    val y1 = linearSmooth(x, x0y1, x1y1)

    return linearSmooth(y, y0, y1)
}

fun trilinear(x: Float, y: Float, z: Float, x0y0z0: Float, x1y0z0: Float, x0y1z0: Float, x1y1z0: Float,
                                            x0y0z1: Float, x1y0z1: Float, x0y1z1: Float, x1y1z1: Float) : Float {
    val z0 = bilinear(x, y, x0y0z0, x1y0z0, x0y1z0, x1y1z0)
    val z1 = bilinear(x, y, x0y0z1, x1y0z1, x0y1z1, x1y1z1)

    return linearSmooth(z, z0, z1)
}

fun sinSmooth(x: Float) : Float {
    return x
//    return .5f*(1f- kotlin.math.cos(PI * x)).toFloat()
//    return 1f-kotlin.math.sqrt(1f - x.pow(2))
//    return .5f * (tanh((x-.5f)*2f)+1f)
}