package com.febryan.ecommerce.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.febryan.ecommerce.MainActivity
import com.febryan.ecommerce.R
import com.febryan.ecommerce.activity.LoginActivity
import com.febryan.ecommerce.helper.SharedPreference
import kotlinx.android.synthetic.main.fragment_akun.*

class AkunFragment : Fragment() {

    lateinit var sharedPrefHelper: SharedPreference
    lateinit var btnLogout:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        btnLogout = view.findViewById(R.id.btn_logout)

        sharedPrefHelper = SharedPreference(requireActivity())

        btnLogout.setOnClickListener {
            sharedPrefHelper.setStatusLogin(false)
            Toast.makeText(activity,"Session dihapus !",Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finishAffinity()

//            getActivity().finish(); => kalo java
        }

        return view

//        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

}