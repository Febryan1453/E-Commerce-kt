package com.febryan.ecommerce.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.febryan.ecommerce.R
import com.febryan.ecommerce.helper.Helper
import com.febryan.ecommerce.model.produk.ProdukItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailProdukActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        getDetail()


    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getDetail(){
        val produkItem = intent.getParcelableExtra<ProdukItem>("detail")
        if (produkItem != null){

            val image = "http://172.16.8.174/API-Ecommerce/public/storage/produk/"+produkItem.image
            Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.baju_1)
                    .error(R.drawable.baju_1)
                    .resize(400,400)
                    .into(image_detail)

            tv_nama_produk.text = produkItem.name
            tv_harga.text = produkItem.harga?.let { Helper().formatRupiah(it) }
            tv_deskripsi.text = produkItem.deskripsi

            setSupportActionBar(toolbar_umum)
            supportActionBar?.title = produkItem.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}