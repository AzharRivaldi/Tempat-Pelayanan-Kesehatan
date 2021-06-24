package com.azhar.tpk.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.azhar.tpk.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var permissionArrays = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val setPermission = Build.VERSION.SDK_INT
        if (setPermission > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (checkIfAlreadyhavePermission() && checkIfAlreadyhavePermission2()) {
            } else {
                requestPermissions(permissionArrays, 101)
            }
        }

        layoutApotek.setOnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, ClinicActivity::class.java)
            startActivity(intent)
        }

        layoutHospital.setOnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, HospitalActivity::class.java)
            startActivity(intent)
        }

        layoutDoctor.setOnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, DoctorActivity::class.java)
            startActivity(intent)
        }

        layoutDrugStore.setOnClickListener { view: View? ->
            val intent = Intent(this@MainActivity, DrugStoreActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkIfAlreadyhavePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun checkIfAlreadyhavePermission2(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

}