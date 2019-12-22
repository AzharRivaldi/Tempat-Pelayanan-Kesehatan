package com.azhar.tpk.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.tpk.R
import com.azhar.tpk.activities.LocationDetailActivity
import com.azhar.tpk.network.model.Results
import com.azhar.tpk.util.Const
import com.azhar.tpk.util.Distance
import kotlinx.android.synthetic.main.list_location.view.*
import java.text.DecimalFormat

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

class RecyclerViewAdapterLocation(private val context: Context, private val arrayList: ArrayList<Results>?) : RecyclerView.Adapter<RecyclerViewAdapterLocation.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_location, parent, false))
    }

    override fun getItemCount(): Int = arrayList?.size ?: 0

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val location = arrayList?.get(position)
        holder.v.tvNameLL.text = location?.name
        holder.v.tvAddressLL.text = location?.vicinity

        val distance = Distance.calculate(Const.lat, Const.lng, location?.geometry?.location?.lat?.toDouble(), location?.geometry?.location?.lng?.toDouble())

        holder.v.tvDistanceLL.text = "" + DecimalFormat("#.##").format(distance) + " KM"

        holder.v.setOnClickListener {

            val i = Intent(context, LocationDetailActivity::class.java)
            i.putExtra("placeId", location?.place_id)
            i.putExtra("lat", location?.geometry?.location?.lat)
            i.putExtra("lng", location?.geometry?.location?.lng)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }

    }

    class Holder(val v: View) : RecyclerView.ViewHolder(v)

}
