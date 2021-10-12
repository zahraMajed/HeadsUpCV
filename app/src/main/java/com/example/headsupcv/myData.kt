package com.example.headsupcv

import com.google.gson.annotations.SerializedName

class myData {
    var data: List<celeb>?=null

    class celeb{
        @SerializedName("pk")
        var pk:Int?=null
        @SerializedName("name")
        var name:String?=null
        @SerializedName("taboo1")
        var taboo1:String?=null
        @SerializedName("taboo2")
        var taboo2:String?=null
        @SerializedName("taboo3")
        var taboo3:String?=null
        constructor(pk:Int, name:String, t1:String , t2:String, t3:String){
            this.pk=pk
            this.name=name
            this.taboo1=t1
            this.taboo2=t2
            this.taboo3=t3
        }
        constructor(name:String, t1:String , t2:String, t3:String){
            this.name=name
            this.taboo1=t1
            this.taboo2=t2
            this.taboo3=t3
        }

    }

}