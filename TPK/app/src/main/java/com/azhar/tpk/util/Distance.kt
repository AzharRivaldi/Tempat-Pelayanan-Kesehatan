package com.azhar.tpk.util

class Distance {

    companion object {

        fun calculate(lat1: Double?, lng1: Double?, lat2: Double?, lng2: Double?): Double {

            var p = 0.017453292519943295
            var a = 0.5 - Math.cos((lat1!! - lat2!!) * p) / 2 + Math.cos(lat1 * p) * Math.cos(lat2 * p) * (1 - Math.cos((lng2!! - lng1!!) * p)) / 2

            return 12742 * Math.asin(Math.sqrt(a))

        }

    }

}