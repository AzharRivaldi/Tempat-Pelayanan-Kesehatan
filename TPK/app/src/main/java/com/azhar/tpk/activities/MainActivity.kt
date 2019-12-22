package com.azhar.tpk.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azhar.tpk.R
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_FINE_LOCATION) || !EasyPermissions.hasPermissions(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

            EasyPermissions.requestPermissions(this, "Aplikasi membutuhkan lokasi mu", 991, android.Manifest.permission.ACCESS_FINE_LOCATION)
            EasyPermissions.requestPermissions(this, "Aplikasi membutuhkan lokasi mu", 992, android.Manifest.permission.ACCESS_COARSE_LOCATION)

        }

        layoutHospitalMain.setOnClickListener {
            startActivity(Intent(applicationContext, ClinicActivity::class.java))
        }
        layoutDrugStoreMain.setOnClickListener {
            startActivity(Intent(applicationContext, DrugsStoreActivity::class.java))
        }
        layoutAboutMain.setOnClickListener {
            startActivity(Intent(applicationContext, AboutActivity::class.java))
        }
        layoutHelpMain.setOnClickListener {
            startActivity(Intent(applicationContext, HelpActivity::class.java))
        }

    }
}
