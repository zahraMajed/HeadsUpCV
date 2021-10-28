package com.example.headsupcv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_heads.*

class HeadsActivity : AppCompatActivity() {
    //my views
    lateinit var edName:EditText
    lateinit var edT1:EditText
    lateinit var edT2:EditText
    lateinit var edT3:EditText
    lateinit var btnSubmit:Button
    lateinit var btnUpdata:Button
    lateinit var btnDelete:Button
    lateinit var tvNoData:TextView

    //my variables
    lateinit var name:String
    lateinit var t1:String
    lateinit var t2:String
    lateinit var t3:String

    val dbHelper=DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heads)

        edName=findViewById(R.id.edName)
        edT1=findViewById(R.id.edT1)
        edT2=findViewById(R.id.edT2)
        edT3=findViewById(R.id.edT3)
        btnSubmit=findViewById(R.id.btnSubmit)
        btnUpdata=findViewById(R.id.btnUpdate)
        btnDelete=findViewById(R.id.btnDelete)
        tvNoData=findViewById(R.id.tvNoData)

        getData()

       btnSubmit.setOnClickListener(){
            if (edName.text.isNotEmpty() && edT1.text.isNotEmpty() &&
                edT2.text.isNotEmpty() && edT3.text.isNotEmpty()){
                name= edName.text.toString() ;  t1= edT1.text.toString()
                t2= edT2.text.toString() ;  t3= edT3.text.toString()
                val status= dbHelper.saveData(name,t1,t2,t3)
                Toast.makeText(applicationContext, "data added in $status", Toast.LENGTH_SHORT).show()
                getData()
                getClearEditText()
            }else
                Toast.makeText(applicationContext, "please fill the missing entries", Toast.LENGTH_SHORT).show()
        }//end listener

        btnUpdata.setOnClickListener(){
            if (edName.text.isNotEmpty() && edT1.text.isNotEmpty() &&
                edT2.text.isNotEmpty() && edT3.text.isNotEmpty()) {
                name = edName.text.toString(); t1 = edT1.text.toString()
                t2 = edT2.text.toString() ; t3 = edT3.text.toString()
                dbHelper.updateData(name,t1,t2,t3)
                getData()
                getClearEditText()
            }else
                Toast.makeText(applicationContext, "please fill the missing entries", Toast.LENGTH_SHORT).show()

        }//end btnUpdate Listener

        btnDelete.setOnClickListener() {
            if (edName.text.isNotEmpty()) {
                name = edName.text.toString()
                dbHelper.delCeleb(name)
                getData()
                getClearEditText()
            }else
                Toast.makeText(applicationContext, "please fill the missing entries", Toast.LENGTH_SHORT).show()
        }//end btnDel listener

    }

    fun getClearEditText(){
        edName.text.clear(); edT1.text.clear()
        edT2.text.clear(); edT3.text.clear()
    }

    fun getData(){
        if (dbHelper.getData().isNotEmpty()){
            tvNoData.visibility=View.INVISIBLE
            rv_add.adapter=RecyclerAdapter(dbHelper.getData())
            rv_add.layoutManager=LinearLayoutManager(this)
        }else{
            tvNoData.visibility=View.VISIBLE
            rv_add.adapter=RecyclerAdapter(dbHelper.getData())
            rv_add.layoutManager=LinearLayoutManager(this)
        }

    }


}