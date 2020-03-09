package jonahshader

import java.lang.Math.PI
import java.lang.Math.cos

fun interpFull(input: Float, inMin: Float, inMax: Float, outMin: Float, outMax: Float) : Float {
    val range = inMax - inMin
    val inPart = input - inMin
    var percent = inPart / range
    percent = sinSmooth(percent)
    val out = (1-percent) * outMin + (percent * outMax)
    return out
}

fun crossfade(input: Float, min: Float, max: Float) : Float {
    return (1-input) * min + (input * max)
}

fun crossfadeSmooth(input: Float, min: Float, max: Float) : Float {
    return sinSmooth(crossfade(input, min, max))
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
fun bilinear(x: Float, y: Float, topLeft: Float, topRight: Float, botLeft: Float, botRight: Float): Float {
    val xTopInterp = crossfadeSmooth(x, topLeft, topRight)
    val xBotInterp = crossfadeSmooth(x, botLeft, botRight)

    return crossfadeSmooth(y, xBotInterp, xTopInterp)
}

fun sinSmooth(x: Float) : Float {
    return .5f*(1f-cos(PI*x)).toFloat()
}