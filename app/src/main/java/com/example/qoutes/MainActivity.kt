package com.example.qoutes

import android.app.Notification.Action
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainmodel:MainViewModel
    private val quoteText:TextView
    get() =findViewById(R.id.quoteText)
    private val quoteAuthor:TextView
        get() =findViewById(R.id.quoteAuthor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainmodel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setquote(mainmodel.getquote())
    }
    fun setquote(quote: Quote)
    {
        quoteText.text=quote.text
        quoteAuthor.text=quote.author
    }
    fun onPrevious(view:View)
    {
        val quote=mainmodel.prevquote()
        setquote(quote)
    }
    fun onNext(view: View)
    {
        val quote=mainmodel.nextquote()
        setquote(quote)
    }
    fun onShare(view: View)
    {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainmodel.getquote().text)
        startActivity(intent)
    }
}