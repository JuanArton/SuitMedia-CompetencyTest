package com.juanarton.test.data

object DataDummy {
    fun getEventData(): ArrayList<EventDataClass>{
        val listEvent = ArrayList<EventDataClass>()

        listEvent.add(
            EventDataClass(
            "comedy",
            "Comedy On Train",
            "1 Agustus 2021"
            )
        )

        listEvent.add(
            EventDataClass(
                "podcast",
                "Close The Window",
                "2 Agustus 2021"
            )
        )

        listEvent.add(
            EventDataClass(
                "health",
                "Sehat Dikala Pandemi",
                "3 Agustus 2021"
            )
        )

        listEvent.add(
            EventDataClass(
                "music",
                "Musick",
                "4 Agustus 2021"
            )
        )

        return listEvent
    }
}