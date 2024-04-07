package com.example.qoutes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset
class MainViewModel(val context:Context): ViewModel() {
    private  var quotelist:Array<Quote>
    private var index=0
    init {
        quotelist=readquote()
    }
    private fun readquote(): Array<Quote> {
        val inputstream=context.assets.open("quotes.json")
        val size:Int=inputstream.available()
        val buffer=ByteArray(size)
        inputstream.read(buffer)
        inputstream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson=Gson()
       return gson.fromJson(json,Array<Quote>::class.java)
    }
    fun getquote()=quotelist[index]
    fun nextquote()=quotelist[++index]
    fun prevquote()=quotelist[--index]
}