package com.example.thuctaplan2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_thongtin.*

class thongtin : AppCompatActivity() {
    var data1: DatabaseReference
    var data2: DatabaseReference
    var data3: DatabaseReference

    init {
        data1 = FirebaseDatabase.getInstance().reference
        data2 = FirebaseDatabase.getInstance().reference
        data3 = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thongtin)
        data1.child("nhietdo").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var docnhietdo :String =snapshot.getValue().toString()
                    nhietdo.setText("Nhiệt độ hiện tại là : "+ docnhietdo);

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data2.child("doam").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var docdoam :String =snapshot.getValue().toString()
                doam.setText("Nhiệt độ hiện tại là : "+ docdoam);

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data3.child("luongmua").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var docluongmua :String =snapshot.getValue().toString()
                if(docluongmua=="0") {
                    baomua.setText("Trời hôm qua không mưa");
                }
                else if(docluongmua=="1"){
                    baomua.setText("Trời hôm qua co mưa");
                }
                else{
                    baomua.setText("KHONG LOAD DUOC DU LIEU");
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });

    }
}