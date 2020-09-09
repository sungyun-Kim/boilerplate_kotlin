package com.devcluster.maker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.main_toolbar.*


class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private val list = ArrayList<ItemIndex>()
    private val user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setSupportActionBar(main_layout_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(true) // 툴바에 타이틀 안보이게
        supportActionBar?.title = "PaperPlane"


        val displayName = user!!.displayName.toString()
        val email = user.email.toString()

        val view: View =  main_navigationView.getHeaderView(0)

        val tvTitle : TextView = view.findViewById(R.id.headerTitle)
        val tvEmail : TextView = view.findViewById(R.id.headerEmail)

        tvTitle.text = displayName
        tvEmail.text = email

        Log.i("Log_activity",displayName+"\n"+email)

        main_navigationView.setNavigationItemSelectedListener(this@MainActivity)



        btnAdd.setOnClickListener{
            showSettingPopup()
        }

        val adapter = RecyclerAdapter(list,"1")
        recyclerView.adapter = adapter

    }

    private fun showSettingPopupItemAmount(){

    }

    private fun showSettingPopup(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alert_popup, null)

        val tvPopUpTitle : TextView = view.findViewById(R.id.tvPopUpTitle)
        val tvPopUpAmount : TextView = view.findViewById(R.id.tvPopUpAmount)


        val alertDialog = AlertDialog.Builder(this)
            .setTitle("send")
            .setPositiveButton("저장"){ dialog, which->
                Toast.makeText(applicationContext, "Pushed Save", Toast.LENGTH_LONG)

            //list.add(YoutubeItem(edtPopUpTitle.text.toString(),edtPopUpAmount.text.toString()+"개",edtPopUpDate.text.toString()))

            }
            .setNeutralButton("취소",null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
            R.id.item_index->{
                //Toast.makeText(this,"item_index clicked",Toast.LENGTH_SHORT).show()

                val intent = Intent(this,IndexActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.item_index){
            val intent = Intent(this,IndexActivity::class.java)
            startActivity(intent)
        }
        else if (item.itemId==R.id.item_progress){

        }
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            main_drawer_layout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked",Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }

}

