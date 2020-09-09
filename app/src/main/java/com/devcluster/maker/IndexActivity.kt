package com.devcluster.maker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.main_toolbar.*

class IndexActivity : AppCompatActivity() {

    private val list = ArrayList<ItemIndex>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        setSupportActionBar(main_layout_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(true) // 툴바에 타이틀 안보이게
        supportActionBar?.title = "box"

        reload()


        val adapter = RecyclerAdapter(list,"0")

        recyclerView.adapter = adapter

    }

    fun reload(){
        val docRef = db.collection("item_index")
        docRef.get()
            .addOnSuccessListener { documents->

                for(document in documents){

                    val item = document.data
                    val name: String = document.id
                    var title: String = ""
                    var subTitle: String = ""

                    for((key,value)in item){
                        if(key=="항목"){
                            title = value.toString()
                        }
                        if(key=="종류"){
                            subTitle = value.toString()
                        }
                    }

                    Log.i("Log_index","이름: $name, 항목: $title, 종류: $subTitle")

                    list.add(ItemIndex(title,subTitle,name))

                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Log_index", "get failed with ", exception)
            }
    }

}

