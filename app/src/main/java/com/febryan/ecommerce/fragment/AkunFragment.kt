package com.febryan.ecommerce.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.febryan.ecommerce.MainActivity
import com.febryan.ecommerce.R
import com.febryan.ecommerce.activity.LoginActivity
import com.febryan.ecommerce.activity.WelcomeActivity
import com.febryan.ecommerce.helper.SharedPreference

class AkunFragment : Fragment() {

    lateinit var sharedPrefHelper: SharedPreference
    lateinit var btnLogout:TextView
    lateinit var nama:TextView
    lateinit var telp:TextView
    lateinit var email:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)

        init(view)

        sharedPrefHelper = SharedPreference(requireActivity())

        btnLogout.setOnClickListener {
            sharedPrefHelper.setStatusLogin(false)
            Toast.makeText(activity,"Logout",Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, WelcomeActivity::class.java))
            activity?.finishAffinity()
//            getActivity().finish(); => kalo java
        }
        setData()
        return view
//        return inflater.inflate(R.layout.fragment_akun, container, false)
    }

    private fun setData() {

        if (sharedPrefHelper.getUser() == null){
            val i = Intent(activity, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            return
        }

        val user = sharedPrefHelper.getUser()
        nama.text = user?.name
        email.text = user?.email
        telp.text = user?.telp
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.tv_logout)
        nama = view.findViewById(R.id.tv_febryan)
        telp = view.findViewById(R.id.tv_telp_febryan)
        email = view.findViewById(R.id.tv_email_febryan)
    }

}