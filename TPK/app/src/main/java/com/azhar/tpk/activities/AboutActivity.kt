package com.azhar.tpk.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.tpk.R
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#FFC107")
        }

        imgCloseAbout.setOnClickListener {
            this.finish()
        }

    }
}
