package com.example.thuctaplan2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dongho.*
import kotlinx.android.synthetic.main.activity_thongtin.*

class dongho : AppCompatActivity() {
    var data4: DatabaseReference
    var data5:DatabaseReference
    var data6: DatabaseReference
    var data7:DatabaseReference
    var data8:DatabaseReference
    var data9:DatabaseReference

    init {
        data4= FirebaseDatabase.getInstance().reference
        data5= FirebaseDatabase.getInstance().reference
        data6= FirebaseDatabase.getInstance().reference
        data7= FirebaseDatabase.getInstance().reference
        data8= FirebaseDatabase.getInstance().reference
        data9= FirebaseDatabase.getInstance().reference


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dongho)
        loaddulieu()
        btnxacnhan.setOnClickListener() {
            var laygiotuoi = gio.text.toString()
            var giotuoi=laygiotuoi.toInt();

            data4.child("giohen").setValue(giotuoi)
            var layphuttuoi = phut.text.toString()
            var phuttuoi=layphuttuoi.toInt()
            data5.child("phuthen").setValue(phuttuoi)
            var thoigiantuoidelay = thoigiantuoibaolau.text.toString()
            data5.child("thoigiantuoitudong").setValue(thoigiantuoidelay)
        }
    }
    fun loaddulieu(){

       data6.child("giohen").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var giodahen = snapshot.getValue().toString()
                thongtinhengiohientai1.setText( "           "+giodahen+ "  :")

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data7.child("phuthen").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               var  phutdahen:String =snapshot.getValue().toString()
                thongtinhengiohientai2.setText(phutdahen)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });
        data8.child("thoigiantuoitudong").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var  timetuoitudong:String =snapshot.getValue().toString()
                hienthithoigiantuoitudong.setText("trong "+timetuoitudong+" phút")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        });


    }
}