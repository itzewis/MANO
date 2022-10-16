package com.example.chatapplication.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chatapplication.fragments.Avaliacao
import com.example.chatapplication.fragments.Postagem

class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { Avaliacao() }
            1 -> { Postagem() }
            else ->{
                Fragment()
            }
        }
    }

}