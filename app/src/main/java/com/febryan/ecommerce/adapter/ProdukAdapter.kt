package com.febryan.ecommerce.adapter

import android.content.Intent
import android.icu.text.NumberFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.febryan.ecommerce.R
import com.febryan.ecommerce.activity.DetailProdukActivity
import com.febryan.ecommerce.model.produk.ProdukItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_produk.view.*
import java.util.*

class ProdukAdapter (var produkItems: List<ProdukItem?>?) : RecyclerView.Adapter<ProdukAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgProduk = itemView.findViewById<ImageView>(R.id.img_produk)
        val tvnama = itemView.findViewById<TextView>(R.id.tv_nama)
        val tvharga = itemView.findViewById<TextView>(R.id.tv_harga)
//        val layout= itemView.findViewById<CardView>(R.id.layout_item_produk)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent,false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.tvnama.text = produkItems?.get(position)?.name
//       holder.tvharga.text = "Rp." +produkItems?.get(position)?.harga
       holder.tvharga.text = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(produkItems?.get(position)?.harga))
       val image = "http://172.16.8.174/API-Ecommerce/public/storage/produk/" + produkItems?.get(position)?.image
       Picasso.get()
           .load(image)
           .placeholder(R.drawable.baju_1) //untuk sementara loading maka gambar ini yang di tampilkan
           .error(R.drawable.baju_1)
           .into(holder.imgProduk)

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val i = Intent(context, DetailProdukActivity::class.java)
            i.putExtra("detail", produkItems?.get(position))
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        if (produkItems != null){
            return produkItems!!.size
        }else{
            return 0
        }
    }
}