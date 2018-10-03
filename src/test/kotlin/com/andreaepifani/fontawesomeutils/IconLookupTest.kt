package com.andreaepifani.fontawesomeutils

import org.junit.Test

class IconLookupTest {

    @Test
    @Throws(Exception::class)
    fun testListFiles() {
        println(IconLookup().contains("fa-star"))
        println(IconLookup().iconData("fa-star"))
    }
}