package com.example.chatapplication

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.chatapplication.databinding.ActivityInicialBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class inicial : AppCompatActivity() {

    private lateinit var fragmentAtual: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        supportFragmentManager.beginTransaction().replace(R.id.layoutFrame,homeFragment()).commit()
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
    }

    val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.btnHome -> {
                fragmentAtual = homeFragment()
            }
            R.id.btnPerfil ->{
                fragmentAtual = perfilFragment()
            }
            R.id.btnCriar ->{
                fragmentAtual = criarPublicacaoFragment()
            }
            R.id.btnPedidos ->{
            fragmentAtual = pedidosFragment()
            }
            R.id.btnChat ->{
                fragmentAtual = chatFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.layoutFrame,fragmentAtual).commit()
        true
    }
}