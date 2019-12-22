package com.azhar.tpk.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.tpk.R
import kotlinx.android.synthetic.main.activity_help.*

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class HelpActivity : AppCompatActivity() {

    val content = "1. Untuk melihat daftar tempat praktek Dokter, Tap bagian Praktek Dokter " +
            "kemudian pilih tempat praktek yang akan didatangi. Tap tombol 'PETUNJUK ARAH' untuk melihat " +
            "arah tempat tersebut dari lokasimu ke tempat tujuan \n\n" +
            "2. Untuk melihat daftar Apotek, Tap bagian Apotek " +
            "kemudian pilih Apotek yang akan didatangi. Tap tombol 'PETUNJUK ARAH' untuk melihat " +
            "arah tempat tersebut dari lokasimu ke tempat tujuan \n\n" +
            "3. Semua tempat yang ditampilkan telah diurutkan berdasarkan yang terdekat."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#00BCD4")
        }

        imgCloseHelp.setOnClickListener {
            this.finish()
        }
        tvContentHelp.text = content

    }
}
