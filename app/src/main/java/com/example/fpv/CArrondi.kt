package com.example.fpv

import kotlin.math.pow
import kotlin.math.roundToInt

class CArrondi {
    fun arrondir(nombre: Float, nbDecimales: Int): Float {

        var n = nombre
        n *= 10.0.pow(nbDecimales.toDouble()).toFloat()
        n = n.roundToInt().toFloat()
        n /= 10.0.pow(nbDecimales.toDouble()).toFloat()

        return n
    }
}