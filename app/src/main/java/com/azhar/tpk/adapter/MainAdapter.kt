package com.azhar.tpk.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.azhar.tpk.R
import com.azhar.tpk.activities.DetailLocationActivity
import com.azhar.tpk.adapter.MainAdapter.SearchViewHolder
import com.azhar.tpk.data.model.ModelResults
import com.azhar.tpk.utils.HitungJarak.getDistance
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.list_item_main.view.*
import java.text.DecimalFormat
import java.util.*

/**
 * Created by Azhar Rivaldi on 06-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class MainAdapter(private val context: Context?) : RecyclerView.Adapter<SearchViewHolder>() {

    private val modelResults = ArrayList<ModelResults>()
    lateinit var simpleLocation: SimpleLocation
    var strLatitude = 0.0
    var strLongitude = 0.0

    fun setResultAdapter(items: ArrayList<ModelResults>) {
        modelResults.clear()
        modelResults.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = modelResults[position]

        simpleLocation = SimpleLocation(context)

        //current location
        strLatitude = simpleLocation.latitude
        strLongitude = simpleLocation.longitude
        val strPlaceID = modelResults[position].placeId

        //location destination
        val strLat = modelResults[position].modelGeometry.modelLocation.lat
        val strLong = modelResults[position].modelGeometry.modelLocation.lng
        val strJarak = getDistance(strLat, strLong, strLatitude, strLongitude)

        holder.tvNamaLokasi.text = item.name
        holder.tvAlamat.text = item.vicinity
        holder.tvJarak.text = DecimalFormat("#.##").format(strJarak) + " KM"

        holder.cvListLocation.setOnClickListener { view: View? ->
            val intent = Intent(context, DetailLocationActivity::class.java)
            intent.putExtra("placeId", strPlaceID)
            intent.putExtra("lat", strLat)
            intent.putExtra("lng", strLong)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelResults.size
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvListLocation: CardView
        val tvNamaLokasi: TextView
        val tvAlamat: TextView
        val tvJarak: TextView

        init {
            cvListLocation = itemView.cvListLocation
            tvNamaLokasi = itemView.tvNamaLokasi
            tvAlamat = itemView.tvAlamat
            tvJarak = itemView.tvJarak
        }
    }

}