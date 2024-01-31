package com.example.kennyyakalamaoyunu

import android.content.Context
import android.content.DialogInterface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.kennyyakalamaoyunu.databinding.ActivityMainBinding
import com.example.kennyyakalamaoyunu.entitiys.Kenny
import com.example.kennyyakalamaoyunu.viewmodel.MainViewModel
import kotlinx.coroutines.Runnable
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tasarim:ActivityMainBinding
    private lateinit var kennyList:ArrayList<ImageView>
    private var skor:Int=0
    private var zamanSayaci=0
    private  var runnable:Runnable=Runnable{}
    private var handler:Handler= Handler(Looper.getMainLooper())
    private lateinit var countDT:CountDownTimer
    private  var viewModel:MainViewModel= MainViewModel()
    private lateinit var kennyListeVtAlinan:List<Kenny>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tasarim= DataBindingUtil.setContentView(this,R.layout.activity_main)

        tasarim.mainActivityNesnesi=this@MainActivity
        kennyListeVtAlinan= arrayListOf<Kenny>()


        kennyList= arrayListOf(tasarim.imageKenny1,tasarim.imageKenny2,tasarim.imageKenny3,tasarim.imageKenny4,
            tasarim.imageKenny5,tasarim.imageKenny6,tasarim.imageKenny7,tasarim.imageKenny8,tasarim.imageKenny9)

        //---------scor alımı---------
        viewModel.skorGonderVm().observe(this){
            tasarim.scorel="Skor :${it}"
        }

        //----------kennyleri veritabanından alma-----------
        viewModel.kennyListVM.observe(this){

            kennyListeVtAlinan=it

        }




        oyunaBasla()
        kennyVeriTabanıListele()



    }

    fun kennyVeriTabanıListele(){

        viewModel.kennylVM()
        for(i in kennyListeVtAlinan){
            println("veritabanından alınan kenny listesi :${i} ${kennyListeVtAlinan.size}")
        }
    }


    fun kennyGizle(){

        for (i in kennyList){

            i.visibility=View.INVISIBLE
        }
    }

    fun skorArttir(view:View){

        viewModel.skorArttirVm(view)
    }

    fun kennyGoster(){

        var rastgeleKennyIndex= Random.nextInt(0,kennyList.size)
        kennyList[rastgeleKennyIndex].visibility=View.VISIBLE

    }

    fun geriSayim(){

        //var sayacDurumu=false
        object:CountDownTimer(21000,1000){
            override fun onTick(p0: Long) {


                //tasarim.textTime.text = "Süre :${p0 / 1000}"

                zamanSayaci= (p0/1000).toInt()
                tasarim.timel="Süre :${zamanSayaci}"

            }

            override fun onFinish() {


                handler.removeCallbacks(runnable)
               // sayacDurumu=true
                val alertDialog=AlertDialog.Builder(this@MainActivity)

                alertDialog.setTitle("Tekrar Oyna")
                alertDialog.setMessage("PUAN : ${skor}")
                alertDialog.setPositiveButton("EVET"){a,b->

                    oyunaBasla()
                }
                alertDialog.setNegativeButton("HAYIR"){a,b->


                }
                alertDialog.show()
            }


        }.start()
       // return sayacDurumu
    }


    fun oyunaBasla(){

        kennyGizle()
        skor=0
       // tasarim.textScore.text="Skor :${skor}"
        tasarim.scorel="Skor :${skor}"
        val sayacD=geriSayim()

        runnable=object :Runnable{
            override fun run() {
                kennyGizle()
                kennyGoster()
                handler.postDelayed(runnable,700)

            }

        }

        handler.post(runnable)

    }

}