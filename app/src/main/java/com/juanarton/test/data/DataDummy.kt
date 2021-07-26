package com.juanarton.test.data

import com.google.android.gms.maps.model.LatLng

object DataDummy {
    fun getEventData(): ArrayList<EventDataClass>{
        val listEvent = ArrayList<EventDataClass>()

        listEvent.add(
            EventDataClass(
            "comedy",
            "Comedy On Train",
            "1 Agustus 2021",
                LatLng(-6.898667, 107.609793)
            )
        )

        listEvent.add(
            EventDataClass(
                "podcast",
                "Close The Window",
                "2 Agustus 2021",
                LatLng(-6.896867, 107.611220)
            )
        )

        listEvent.add(
            EventDataClass(
                "health",
                "Sehat Dikala Pandemi",
                "3 Agustus 2021",
                LatLng(-6.897165, 107.613130)
            )
        )

        listEvent.add(
            EventDataClass(
                "music",
                "Musick",
                "4 Agustus 2021",
                LatLng(-6.898432, 107.612476)
            )
        )

        return listEvent
    }
}