package com.febryan.ecommerce.helper

import android.icu.text.NumberFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class Helper {
    @RequiresApi(Build.VERSION_CODES.N)
    fun formatRupiah(hargaString: String): String{
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(Integer.valueOf(hargaString))
    }
}