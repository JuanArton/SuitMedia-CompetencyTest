package com.juanarton.test.model.remote

import com.juanarton.test.API.API
import com.juanarton.test.data.GuestDataClass
import retrofit2.Response
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getGuestList(callback: LoadGuestCallback){
        val listData = ArrayList<GuestDataClass>()
        val dummyImage: List<String> = listOf(
            "https://i.kym-cdn.com/entries/icons/original/000/009/754/PhotogenicGuy.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPskRnzG0cgA3RyATy8iuLDLISpRtmyhJ1Cg&usqp=CAU",
            "https://i.kym-cdn.com/photos/images/newsfeed/000/128/847/400x.jpg?1306898671",
            "https://video-images.vice.com/articles/60d4708307f404009440c39d/lede/1624536388711-what-its-like-to-be-the-actual-face-of-disappointment.jpeg?crop=0.6xw:1xh;0.1708xw,0xh",
            "https://i.pinimg.com/736x/8b/70/ad/8b70ad7d1af64736c78b8d310230ca7d.jpg"
        )
        API.services.getPopular().await().let { response ->
            for (i in response.indices){
                val event = response[i]
                val data = GuestDataClass(
                    dummyImage[i],
                    event.id,
                    event.name,
                    event.birthdate
                )
                listData.add(data)
            }
            callback.onGuestReceived(listData)
        }
    }

    interface LoadGuestCallback {
        fun onGuestReceived(guestResponse: ArrayList<GuestDataClass>)
    }
}