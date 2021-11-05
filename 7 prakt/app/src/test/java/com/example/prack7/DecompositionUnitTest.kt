package com.example.prack7
//import com.example.prack7.DecompositionIntoPrimeMultiplier
import org.junit.Test

import org.junit.Assert.*

class DecompositionUnitTest {
    @Test
    fun decomposition_isCorrect() {
        //val decompositionObj = DecompositionIntoPrimeMultiplier()
//        val d = DecompositionIntoPrimeMultiplier()

//        assertEquals("2",d.decomposition(2))

        assertEquals("5", DecompositionIntoPrimeMultiplier.decomposition(5))
//        assertEquals("2 * 2", DecompositionIntoPrimeMultiplier.decomposition(4))
//        assertEquals("2 * 2 * 2 * 2 * 2 * 2 * 2 * 2", DecompositionIntoPrimeMultiplier.decomposition(256))
//        assertEquals("", DecompositionIntoPrimeMultiplier.decomposition(0))
//        assertEquals("11", DecompositionIntoPrimeMultiplier.decomposition(11))
//        assertEquals("", DecompositionIntoPrimeMultiplier.decomposition(-4))
    }
}