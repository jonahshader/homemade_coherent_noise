package jonahshader

//wikipedia first xorshift
fun xorshift(previousNum: Int) : Int {
    var newNum = previousNum
    newNum = newNum xor (newNum shl 13)
    newNum = newNum xor (newNum shr 17)
    newNum = newNum xor (newNum shl 5)
    return newNum
}

fun Int.nextRand(): Int {
    return xorshift(this)
}

//fun Int.hash() : Int {
//    return (this * 2654435761).toInt()
//}

//https://stackoverflow.com/questions/664014/what-integer-hash-function-are-good-that-accepts-an-integer-hash-key
fun Int.betterHash() : Int {
    var out = this
    out = ((out shr 16) xor out) * 0x45d9f3b
    out = ((out shr 16) xor out) * 0x45d9f3b
    out = (out shr 16) xor out
    return out
}