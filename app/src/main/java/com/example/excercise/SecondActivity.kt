package com.example.excercise

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //receiving side
        val message: String = intent.getStringExtra(MainActivity.EXTRA_MESSAGE) //get string input from main activity
        textViewReceived.text = String.format("%s : %s", getString(R.string.message),message)

        //press this button send message to previous page
        buttonDone.setOnClickListener{
            val intent = getIntent()// where the intent come from?
            //return message
            if(!editTextReply.text.isEmpty()){
                val reply = editTextReply.text.toString()
                intent.putExtra(MainActivity.EXTRA_REPLY, reply)
                setResult(Activity.RESULT_OK, intent)
            }else
                setResult(Activity.RESULT_CANCELED,intent)


            //close activity
            finish()
        }
    }
}
