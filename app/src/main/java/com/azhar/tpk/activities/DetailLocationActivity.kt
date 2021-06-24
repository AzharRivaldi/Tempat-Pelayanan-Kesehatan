package com.azhar.tpk.activities

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.azhar.tpk.R
import com.azhar.tpk.data.model.ModelDetail
import com.azhar.tpk.viewmodel.ListResultViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail_location.*

class DetailLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var googleMaps: GoogleMap
    lateinit var strPlaceID: String
    lateinit var strAlamat: String
    lateinit var strTelepon: String
    lateinit var strLokasi: String
    lateinit var progressDialog: ProgressDialog
    lateinit var listResultViewModel: ListResultViewModel
    var strLatitude = 0.0
    var strLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_location)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon tunggu...")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("sedang menampilkan lokasi")

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //get data intent from adapter
        val intent = intent
        val bundle = intent.extras

        if (bundle != null) {
            strPlaceID = bundle["placeId"] as String
            strLatitude = bundle["lat"] as Double
            strLongitude = bundle["lng"] as Double

            //viewmodel
            listResultViewModel = ViewModelProvider(this, NewInstanceFactory()).get(ListResultViewModel::class.java)
            listResultViewModel.setDetailLocation(strPlaceID)
            progressDialog.show()
            listResultViewModel.getDetailLocation().observe(this, { modelResults: ModelDetail ->
                strLokasi = modelResults.name.toString()
                strAlamat = modelResults.formatted_address.toString()
                strTelepon = modelResults.formatted_phone_number.toString()

                tvNamaLokasi.setText(strLokasi)
                tvAlamat.setText(strAlamat)
                tvTelepon.setText(strTelepon)

                btnRute.setOnClickListener { view: View? ->
                    val intentRute = Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=$strLatitude,$strLongitude"))
                    startActivity(intentRute)
                }

                progressDialog.dismiss()
            })
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMaps = googleMap
        val latLng = LatLng(strLatitude, strLongitude)
        googleMaps.addMarker(MarkerOptions().position(latLng).title(strLokasi))
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        googleMaps.uiSettings.setAllGesturesEnabled(true)
        googleMaps.uiSettings.isZoomGesturesEnabled = true
        googleMaps.isTrafficEnabled = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}