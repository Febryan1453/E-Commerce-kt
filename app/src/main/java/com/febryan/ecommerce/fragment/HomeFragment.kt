package com.febryan.ecommerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.febryan.ecommerce.R
import com.febryan.ecommerce.adapter.AdapterProduk
import com.febryan.ecommerce.adapter.AdapterSlider
import com.febryan.ecommerce.model.Produk


class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvBuku: RecyclerView
    lateinit var rvBaju: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvBuku = view.findViewById(R.id.rv_buku)
        rvBaju = view.findViewById(R.id.rv_baju)

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.carousel2)
        arrSlider.add(R.drawable.carousel3)
        arrSlider.add(R.drawable.carousel4)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider


        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rvBuku.adapter = AdapterProduk(arrBuku)
        rvBuku.layoutManager = layoutManager

        rvBaju.adapter = AdapterProduk(arrBaju)
        rvBaju.layoutManager = layoutManager2

        return view
    }

    val arrBuku: ArrayList<Produk>get() {

        val arr = ArrayList<Produk>()

        val p1 = Produk()
        p1.nama = "Buku NMS"
        p1.harga = "Rp. 99.000"
        p1.gambar = R.drawable.buku_1

        val p2 = Produk()
        p2.nama = "Buku Belajar Intensif IPV 6"
        p2.harga = "Rp. 100.000"
        p2.gambar = R.drawable.buku_2

        val p3 = Produk()
        p3.nama = "Buku VPN"
        p3.harga = "Rp. 105.000"
        p3.gambar = R.drawable.buku_4

        arr.add(p1)
        arr.add(p2)
        arr.add(p3)

        return arr
    }


    val arrBaju: ArrayList<Produk>get() {

        val arrBj = ArrayList<Produk>()

        val pb1 = Produk()
        pb1.nama = "Baju VMWare Keren"
        pb1.harga = "Rp. 99.000"
        pb1.gambar = R.drawable.baju_1

        val pb2 = Produk()
        pb2.nama = "Baju VSchool Cloud"
        pb2.harga = "Rp. 100.000"
        pb2.gambar = R.drawable.baju_2

        val pb3 = Produk()
        pb3.nama = "Jaket Merah Network"
        pb3.harga = "Rp. 105.000"
        pb3.gambar = R.drawable.jaket_1

        val pb4 = Produk()
        pb4.nama = "Jaket Hitam Programming"
        pb4.harga = "Rp. 105.000"
        pb4.gambar = R.drawable.jaket_2
//
//        val pb5 = Produk()
//        pb5.nama = "Baju Kerah Merah Network"
//        pb5.harga = "Rp. 105.000"
//        pb5.gambar = R.drawable.kerah_1
//
//        val pb6 = Produk()
//        pb6.nama = "Baju Kerah Hitam Programming"
//        pb6.harga = "Rp. 105.000"
//        pb6.gambar = R.drawable.kerah_2

        arrBj.add(pb1)
        arrBj.add(pb2)
        arrBj.add(pb3)
        arrBj.add(pb4)
//        arrBj.add(pb5)
//        arrBj.add(pb6)

        return arrBj
    }

}