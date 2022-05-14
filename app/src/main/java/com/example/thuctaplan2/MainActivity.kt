package com.example.thuctaplan2

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }
class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var manhinhthongtin= Intent(this,thongtin::class.java)
        var manhinhdieukhien=Intent(this,dieukhien::class.java)
        
        iconiformationnhe.setOnClickListener(){
        startActivity(manhinhthongtin)
        }
        iconsettingnhe.setOnClickListener(){
            startActivity(manhinhdieukhien)
        }

    }}