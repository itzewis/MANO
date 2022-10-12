package com.example.chatapplication

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class inicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val homeFragment = home_Fragment()
        val chatFragment = ChatFragment()
        val criarPubliFragment = criarpubliFragment()
        val pedidosFragment = PedidosFragment()
        val perfilFragment = PerfilFragment()

        makeCurrentFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btnHome -> makeCurrentFragment(homeFragment)
                R.id.btnChat ->makeCurrentFragment(chatFragment)
                R.id.btnCriar ->makeCurrentFragment(criarPubliFragment)
                R.id.btnPedidos ->makeCurrentFragment(pedidosFragment)
                R.id.btnPerfil ->makeCurrentFragment(perfilFragment)

            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameInicio,fragment)
            commit()
        }
}