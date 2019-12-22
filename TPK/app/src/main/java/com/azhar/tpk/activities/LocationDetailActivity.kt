package com.azhar.tpk.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.tpk.R
import com.azhar.tpk.network.ApiConfig
import com.azhar.tpk.network.response.ResultDetails
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.activity_location_detail.*
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class LocationDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var i: Intent
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#BDBDBD")
        }

        pbLD.indeterminateDrawable = ChromeFloatingCirclesDrawable.Builder(this)
                .colors(resources.getIntArray(R.array.google_colors)).build()

        imgCloseLD.setOnClickListener {
            this.finish()
        }

        val mapLD = supportFragmentManager.findFragmentById(R.id.mapLD) as SupportMapFragment
        mapLD.getMapAsync(this)

        i = intent

        loadDetails()

        btnDirectionLD.setOnClickListener {
            val intent = Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=&daddr=${i.getStringExtra("lat")},${i.getStringExtra("lng")}"))
            startActivity(intent)
        }

    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val lat = i.getStringExtra("lat")
        val lng = i.getStringExtra("lng")
        val location = LatLng(lat.toDouble(), lng.toDouble())
        mMap.addMarker(MarkerOptions().position(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14.0f));
    }

    private fun loadDetails() {

        pbLD.visibility = View.VISIBLE

        ApiConfig().instance().json(ApiConfig.API_KEY, i.getStringExtra("placeId"))
                .enqueue(object : retrofit2.Callback<ResultDetails> {

                    override fun onFailure(call: Call<ResultDetails>?, t: Throwable?) {
                        Log.d("ONFAILURE", t.toString())
                        pbLD.visibility = View.GONE
                        MDToast.makeText(applicationContext, "Koneksi bermasalah", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
                        finish()
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<ResultDetails>?, response: Response<ResultDetails>?) {

                        pbLD.visibility = View.GONE
                        layoutLD.visibility = View.VISIBLE
                        tvNameLD.text = response?.body()?.result?.name
                        tvAddressLD.text = response?.body()?.result?.formatted_address

                        if (response?.body()?.result?.formatted_phone_number == null) {
                            tvPhoneLD.text = "No.Telp tidak tersedia"
                        } else {
                            tvPhoneLD.text = response?.body()?.result?.formatted_phone_number
                        }

                    }

                })

    }
}
