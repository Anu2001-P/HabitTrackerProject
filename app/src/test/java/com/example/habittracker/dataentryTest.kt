package com.example.habittracker

import org.junit.Test

class dataentryTest{

    @Test
    fun `valid item returns true`(){
        val res = dataentry.validate(
            "tomotoes",
            0,
            6.0
        )
        assert(res)
    }
}