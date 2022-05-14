package com.example.thuctaplan2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dieukhien.*
import java.util.*

class dieukhien : AppCompatActivity() {
    val REQUEST_CODE_SPEECH_INPUT=100
    var data2: DatabaseReference = FirebaseDatabase.getInstance().reference
    var data3: DatabaseReference = FirebaseDatabase.getInstance().reference
    var data1: DatabaseReference

    init {
        data1 = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieukhien)
        dodulieu()
        val manhinhdongho= Intent(this,dongho::class.java)
        hengio.setOnClickListener() { startActivity(manhinhdongho)}
        hamgiongnoi()
        dieukhientay()


    }
    fun dieukhientay(){
        tuoinuoccactang.setOnClickListener{
            if(tuoinuoccactang.isChecked)
            {

                tuoinuoctang1bat()
                tuoinuoctang2bat()
                tuoinuoctang3bat()
            }
            else{
                tuoinuoctang1tat()
                tuoinuoctang2tat()
                tuoinuoctang3tat()
            }

        }

        tuoinuoctang1.setOnClickListener{
            if(tuoinuoctang1.isChecked)
            {

                tuoinuoctang1bat()
            }
            else{

                tuoinuoctang1tat()
            }


        }
        tuoinuoctang2.setOnClickListener{
            if(tuoinuoctang2.isChecked)
            {

                tuoinuoctang2bat()
            }
            else{
                tuoinuoctang2tat()
            }
        }
        tuoinuoctang3.setOnClickListener{
            if(tuoinuoctang3.isChecked)
            {

                tuoinuoctang3bat()
            }
            else{
                tuoinuoctang3tat()
            }
        }
    }

    fun hamgiongnoi(){
        giongnoi.setOnClickListener(){
            speak()
        }

    }
    fun dodulieu(){

        data1.child("nuoctang1").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var trangthainuoctang1 :String =snapshot.getValue().toString()

                findViewById<Switch>(R.id.tuoinuoctang1).isChecked = trangthainuoctang1=="1"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data2.child("nuoctang2").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var trangthainuoctang2 :String =snapshot.getValue().toString()

                findViewById<Switch>(R.id.tuoinuoctang2).isChecked = trangthainuoctang2=="1"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data3.child("nuoctang3").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var trangthainuoctang3 :String =snapshot.getValue().toString()

                if(trangthainuoctang3=="1"){
                    findViewById<Switch>(R.id.tuoinuoctang3).isChecked=true
                }
                else {
                    Toast.makeText(applicationContext, "dang khong tuoi ", Toast.LENGTH_SHORT).show()
                    findViewById<Switch>(R.id.tuoinuoctang3).isChecked=false

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
    }

    fun speak(){
        val dulieugiognoi= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        dulieugiognoi.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        dulieugiognoi.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        dulieugiognoi.putExtra(RecognizerIntent.EXTRA_PROMPT,"Bạn muốn điều khiển gì ")
        try {

            startActivityForResult(dulieugiognoi,REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e:Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
        }}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
        val hienthi = findViewById<TextView>(R.id.voice)
        when(requestCode){

            REQUEST_CODE_SPEECH_INPUT->{
                if (data != null&& resultCode == Activity.RESULT_OK) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    hienthi.text = result!![0]
                    var day = hienthi.text
                    if (day == "tưới nước tầng 1") {
                        tuoinuoctang1bat()
                    } else if (day == "tắt nước tầng 1") {
                        tuoinuoctang1tat()
                    } else if (day == "tưới nước tầng 2") {
                        tuoinuoctang2bat()
                    } else if (day == "tắt nước tầng 2") {
                        tuoinuoctang2tat()
                    } else if (day == "tưới nước tầng 3") {
                        tuoinuoctang3bat()
                    } else if (day == "tắt nước tầng 3") {
                        tuoinuoctang3tat()
                    } else if (day == "tưới nước toàn bộ") {
                        tuoinuoctang2bat()
                        tuoinuoctang1bat()
                        tuoinuoctang3bat()


                    } else {
                        tuoinuoctang1tat()
                        tuoinuoctang2tat()
                        tuoinuoctang3tat()

                    }
                }

            }}
    }

    private fun startActivityForResult() {
        TODO("Not yet implemented")
    }

    fun tuoinuoctang1bat(){


        data1.child("nuoctang1").setValue(1)

    }
    fun tuoinuoctang1tat(){


        data1.child("nuoctang1").setValue(0)


    }
    fun tuoinuoctang2bat(){


        data2.child("nuoctang2").setValue(1)

    }
    fun tuoinuoctang2tat(){


        data2.child("nuoctang2").setValue(0)

    }
    fun tuoinuoctang3bat(){


        data3.child("nuoctang3").setValue(1)

    }
    fun tuoinuoctang3tat(){

        data3.child("nuoctang3").setValue(0)

    }


}
