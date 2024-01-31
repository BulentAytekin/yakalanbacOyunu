package com.example.kennyyakalamaoyunu.repositoryy

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.kennyyakalamaoyunu.entitiys.Kenny

class RepositoryKenny () {

    var kennyListR=MutableLiveData<List<Kenny>>()
    var score=MutableLiveData<Int>()
    var skor=0

    init {
        kennyListR= MutableLiveData()
        score= MutableLiveData()

    }
    fun skorGonderR():MutableLiveData<Int>{

        return score
    }
    fun kennyListGonderR():MutableLiveData<List<Kenny>>{

        return kennyListR
    }






    /*val kennyList= arrayListOf(tasarim.imageKenny1,tasarim.imageKenny2,tasarim.imageKenny3,tasarim.imageKenny4,
        tasarim.imageKenny5,tasarim.imageKenny6,tasarim.imageKenny7,tasarim.imageKenny8,tasarim.imageKenny9)*/




    fun skorArttirR(view: View){

        skor+=1
        score .value= skor

        //tasarim.textScore.text="Skor : ${skor}"
       // tasarim.scorel="Skor :${skor}"

    }

    fun listeleR(){
        val k1=Kenny(1,"imageKenny1")
        val k2=Kenny(2,"imageKenny2")
        val k3=Kenny(3,"imageKenny3")
        val k4=Kenny(4,"imageKenny4")
        val k5=Kenny(5,"imageKenny5")
        val k6=Kenny(6,"imageKenny6")
        val k7=Kenny(7,"imageKenny7")
        val k8=Kenny(8,"imageKenny8")
        val k9=Kenny(9,"imageKenny9")

        val kennlList= arrayListOf(k1,k2,k3,k4,k5,k6,k7,k8,k9)
        kennyListR.value=kennlList

    }






}