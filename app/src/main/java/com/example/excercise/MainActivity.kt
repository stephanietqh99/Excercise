package com.example.excercise

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import com.example.excercise.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityMainBinding
    private val myContact = Contact("Beauty", "0123456789")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //link binding to display UI
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //assign attributes of local variable to UI variable
        binding.contact = myContact

        //update data when button click
        binding.buttonUpdate.setOnClickListener{
            binding.apply{
                contact?.name = "More Beauty"
                contact?.phone = "111111111"
                invalidateAll() //refresh the UI
            }
        }

        //display UI
        //setContentView(R.layout.activity_main)

        //create an event handler for buttonSend

        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage(){
        //Create an Explicit Intent for the SecondActivity
        //one page jump to another page
        val intent = Intent(this, SecondActivity::class.java)

        //prepare extra
        //get message
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)

        //start activity with no return value
        //startActivity(intent)

        //start an activity with return value
        startActivityForResult(intent, REQUEST_REPLY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        //check
        if(requestCode == REQUEST_REPLY) {
            if (resultCode == Activity.RESULT_OK) {
                val reply = data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s : %s" , getString(R.string.reply), reply)
            }
            else{
                textViewReply.text = String.format("%s : %s", getString(R.string.reply), "No reply")
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }


    //LINK EXTRA MESSAGE
    companion object{
        const val EXTRA_MESSAGE = "com.example.excercise.MESSAGE"
        const val REQUEST_REPLY = 1 // an integer (return value)
        const val EXTRA_REPLY = "com.example.excercise.REPLY"
    }
}
