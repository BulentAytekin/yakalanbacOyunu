package com.example.kennyyakalamaoyunu.viewmodel

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kennyyakalamaoyunu.databinding.ActivityMainBinding
import com.example.kennyyakalamaoyunu.entitiys.Kenny
import com.example.kennyyakalamaoyunu.repositoryy.RepositoryKenny
import kotlin.random.Random

class MainViewModel () :ViewModel() {

    val krepo=RepositoryKenny()
    var kennyListVM= MutableLiveData<List<Kenny>>()
    var scoreVm= MutableLiveData<Int>()
    var skor=0

    init {
        kennyListVM= krepo.kennyListGonderR()
        scoreVm= MutableLiveData()

    }

    fun skorGonderVm():MutableLiveData<Int>{
        return krepo.skorGonderR()
    }



    fun kennylVM(){

        krepo.listeleR()
    }




    fun skorArttirVm(view:View){

      krepo.skorArttirR(view)

    }

}