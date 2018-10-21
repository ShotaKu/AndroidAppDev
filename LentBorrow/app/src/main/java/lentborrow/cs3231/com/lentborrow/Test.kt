package lentborrow.cs3231.com.lentborrow

import kotlin.reflect.KFunction

class Test(){
    fun foo(m: String, i: Int,  bar: (m: String, i:Int) -> Unit) {
        bar(m,i)
    }

    // my function to pass into the other
    fun buz(m: String, i:Int) {
        println("another message: $m")
    }

    // someone passing buz into foo
    fun something() {
        foo("hi", 1 , ::buz);
    }
}