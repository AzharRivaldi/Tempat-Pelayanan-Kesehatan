package com.azhar.tpk.utils

/**
 * Created by Azhar Rivaldi on 10-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

object HitungJarak {

    //Rumus Jarak Haversine
    fun getDistance(currentLatitude: Double,
                    currentLongitude: Double,
                    destinationLatitude: Double,
                    destinationLongitude: Double): Double {
        val earthRadius = 3958.75
        val dLatitude = Math.toRadians(destinationLatitude - currentLatitude)
        val dLongitude = Math.toRadians(destinationLongitude - currentLongitude)
        val a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2) +
                Math.cos(Math.toRadians(currentLatitude)) *
                Math.cos(Math.toRadians(destinationLatitude)) *
                Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val dDistance = earthRadius * c
        val meterConversion = 1609
        val myDistance = dDistance * meterConversion
        return Math.floor(myDistance / 1000)
    }

}