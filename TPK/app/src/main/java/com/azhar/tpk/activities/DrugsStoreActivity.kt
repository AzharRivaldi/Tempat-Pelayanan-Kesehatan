package com.azhar.tpk.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.tpk.R
import com.azhar.tpk.adapter.RecyclerViewAdapterLocation
import com.azhar.tpk.network.ApiConfig
import com.azhar.tpk.network.response.ResultNearby
import com.azhar.tpk.util.Const
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable
import com.valdesekamdem.library.mdtoast.MDToast
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_drugs_store.*
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class DrugsStoreActivity : AppCompatActivity() {

    lateinit var simpleLocation: SimpleLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drugs_store)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#E91E63")
        }

        rvDS.setHasFixedSize(true)
        rvDS.layoutManager = LinearLayoutManager(this)

        pbDS.indeterminateDrawable = ChromeFloatingCirclesDrawable.Builder(this)
                .colors(resources.getIntArray(R.array.google_colors)).build()

        simpleLocation = SimpleLocation(this)

        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        val lat = simpleLocation.latitude
        val lng = simpleLocation.longitude
        val location = "${lat.toString()},${lng.toString()}"

        Const.lat = lat
        Const.lng = lng

        Log.d("LOCATION", location)

        rvDS.adapter = RecyclerViewAdapterLocation(this, null)

        loadDrugsStoreList(location)

        imgCloseDS.setOnClickListener {
            this.finish()
        }

    }

    override fun onResume() {
        super.onResume()
        simpleLocation.beginUpdates()
    }

    override fun onPause() {
        simpleLocation.endUpdates()
        super.onPause()
    }

    private fun loadDrugsStoreList(myLocation: String?) {

        pbDS.visibility = View.VISIBLE
        ApiConfig().instance().json(ApiConfig.API_KEY, "apotek", myLocation, "distance")
                .enqueue(object : retrofit2.Callback<ResultNearby> {

                    override fun onFailure(call: Call<ResultNearby>?, t: Throwable?) {
                        pbDS.visibility = View.GONE
                        MDToast.makeText(applicationContext, "Koneksi bermasalah", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
                        finish()
                    }

                    override fun onResponse(call: Call<ResultNearby>?, response: Response<ResultNearby>?) {

                        if (response?.body()?.results?.size == 0 || response?.body()?.results == null) {

                            MDToast.makeText(applicationContext, "Data kosong", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
                            finish()

                        }

                        pbDS.visibility = View.GONE
                        val adapter = RecyclerViewAdapterLocation(applicationContext, response?.body()?.results)
                        adapter.notifyDataSetChanged()
                        rvDS.adapter = adapter

                    }

                })

    }
}
