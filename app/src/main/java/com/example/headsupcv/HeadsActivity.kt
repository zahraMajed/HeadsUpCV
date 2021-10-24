package com.example.headsupcv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_heads.*

class HeadsActivity : AppCompatActivity() {
    lateinit var edName:EditText
    lateinit var edT1:EditText
    lateinit var edT2:EditText
    lateinit var edT3:EditText
    lateinit var btnSubmit:Button
    lateinit var Celebrities :ArrayList<List<String>>
    val dbHelper=DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heads)

        edName=findViewById(R.id.edName)
        edT1=findViewById(R.id.edT1)
        edT2=findViewById(R.id.edT2)
        edT3=findViewById(R.id.edT3)
        btnSubmit=findViewById(R.id.btnSubmit)

        getData()

       btnSubmit.setOnClickListener(){
            if (edName.text.isNotEmpty() && edT1.text.isNotEmpty() &&
                edT2.text.isNotEmpty() && edT3.text.isNotEmpty()){
                var name= edName.text.toString()
                var t1= edT1.text.toString()
                var t2= edT2.text.toString()
                var t3= edT3.text.toString()
                val status= dbHelper.saveData(name,t1,t2,t3)
                Toast.makeText(applicationContext, "data added in $status", Toast.LENGTH_SHORT).show()
                getData()
            }
        }//end listener

    }

    fun getData(){
        rv_add.adapter=RecyclerAdapter(dbHelper.getData())
        rv_add.layoutManager=LinearLayoutManager(this)
    }
}