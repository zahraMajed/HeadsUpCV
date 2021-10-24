package com.example.headsupcv

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvTimer:TextView

    lateinit var LL1:LinearLayout
    lateinit var btnStrat:Button
    lateinit var btnAdd:Button

    lateinit var LL2:LinearLayout
    lateinit var tvRotate:TextView
    lateinit var LL3:LinearLayout
    lateinit var tvName:TextView
    lateinit var tvT1:TextView
    lateinit var tvT2:TextView
    lateinit var tvT3:TextView

    var celebList= arrayListOf<List<String>>()
    var celeb=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer=findViewById(R.id.tvTimer)

        LL1=findViewById(R.id.LL1)
        btnStrat=findViewById(R.id.btnStart)
        btnAdd=findViewById(R.id.btnAdd)

        LL2=findViewById(R.id.LL2)
        tvRotate=findViewById(R.id.tvRotate)

        LL3=findViewById(R.id.LL3)
        tvName=findViewById(R.id.tvName)
        tvT1=findViewById(R.id.tvT1)
        tvT2=findViewById(R.id.tvT2)
        tvT3=findViewById(R.id.tvT3)

        btnStrat.setOnClickListener(){
            LL1.visibility=View.GONE
            LL2.visibility=View.VISIBLE
            countDownTimer()
            getAPIresult()
        }//end btnStart Listener

        btnAdd.setOnClickListener(){
            intent= Intent(this,HeadsActivity::class.java)
            startActivity(intent)
        }

    }//end onCreate()

    fun countDownTimer(){
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000)
            }
            override fun onFinish() {
                tvTimer.setText("Time: -")
                LL2.visibility=View.GONE
                LL3.visibility=View.GONE
                LL1.visibility=View.VISIBLE
            }
        }.start()
    }//end countDownTimer()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            if (LL2.isVisible){
                LL2.visibility=View.GONE
                LL3.visibility=View.VISIBLE
                getCele()
            }
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            if (LL3.isVisible){
                LL2.visibility=View.VISIBLE
                LL3.visibility=View.GONE
                celeb++
            }
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }//end onConfigurationChanged()

    fun getAPIresult(){
        val apiInterface=ApiClint().getClient()?.create(ApiInterface::class.java)

        if (apiInterface != null){
            apiInterface.getDate()?.enqueue(object : Callback<List<myData.celeb>?> {

                override fun onResponse(call: Call<List<myData.celeb>?>, response: Response<List<myData.celeb>?>) {
                    val response=response.body()
                    for (item in response!!){
                        celebList.add(listOf("${item.name}",
                            "${item.taboo1}",
                            "${item.taboo2}",
                            "${item.taboo3}",
                            "${item.pk}"))
                    }
                    getCele()
                }

                override fun onFailure(call: Call<List<myData.celeb>?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Somethimg went wrong!", Toast.LENGTH_LONG).show()
                }

            })
        }
    }//end getAPIresult()

    fun getCele(){
        tvName.text=celebList[celeb][0]
        tvT1.text=celebList[celeb][1]
        tvT2.text=celebList[celeb][2]
        tvT3.text=celebList[celeb][3]
    }

}//end class