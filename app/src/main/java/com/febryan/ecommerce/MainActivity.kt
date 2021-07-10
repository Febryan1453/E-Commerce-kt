package com.febryan.ecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.febryan.ecommerce.fragment.AkunFragment
import com.febryan.ecommerce.fragment.HomeFragment
import com.febryan.ecommerce.fragment.KeranjangFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val fragmentHome: Fragment = HomeFragment()
    val fragmentAkun: Fragment = AkunFragment()
    val fragmentKeranjang: Fragment = KeranjangFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()

    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()
        fm.beginTransaction().add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.navigation_home ->{
                    Log.d("Response", "Home")
//                    menuItem = menu.getItem(0)
//                    menuItem.isChecked = true
//
//                    fm.beginTransaction().hide(active).show(fragmentHome).commit()
//                    active = fragmentHome

                    callFragment(0, fragmentHome)
                }

                R.id.navigation_keranjang ->{
//                    Log.d("Response", "Keranjang")
//                    menuItem = menu.getItem(1)
//                    menuItem.isChecked = true
//
//                    fm.beginTransaction().hide(active).show(fragmentKeranjang).commit()
//                    active = fragmentKeranjang
                    callFragment(1, fragmentKeranjang)
                }

                R.id.navigation_akun ->{
//                    Log.d("Response", "Akun")
//                    menuItem = menu.getItem(2)
//                    menuItem.isChecked = true
//
//                    fm.beginTransaction().hide(active).show(fragmentAkun).commit()
//                    active = fragmentAkun
                    callFragment(2, fragmentAkun)
                }
            }
            false
        }
    }

    fun callFragment(index: Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}