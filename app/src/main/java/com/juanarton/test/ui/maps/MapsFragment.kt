package com.juanarton.test.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.juanarton.test.R
import com.juanarton.test.data.EventDataClass
import com.juanarton.test.databinding.FragmentMapsBinding
import com.juanarton.test.model.ViewModelFactory
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MapsFragment : Fragment(), OnMapReadyCallback {

    private var bindingtmp: FragmentMapsBinding? = null
    private val binding get() = bindingtmp!!
    private lateinit var viewModel: MapsViewModel

    private var carouseltmp: ImageCarousel? = null
    private val carousel get() = carouseltmp!!

    override fun onMapReady(googleMap: GoogleMap) {

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(MapsViewModel::class.java)

        val listEvent = viewModel.getEventDummyData()
        setCarousel(googleMap, listEvent)
        setMarkers(googleMap, listEvent)

        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    private fun setMarkers(googleMap: GoogleMap, data: List<EventDataClass>){
        for(i in data.indices){
            googleMap.addMarker(MarkerOptions().position(data[i].loc).title(data[i].nama).snippet(data[i].tanggal))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(data[i].loc, 17.0F))
        }
    }

    private fun setCarousel(googleMap: GoogleMap, data: List<EventDataClass>){
        carouseltmp = binding.imgcarousel
        carousel.registerLifecycle(lifecycle)
        val carouselList = mutableListOf<CarouselItem>()
        for(i in data.indices){
            val image = context?.resources?.getIdentifier(data[i].image, "drawable",
                context?.packageName
            )
            carouselList.add(
                CarouselItem(
                    image,
                    data[i].nama
                )
            )
        }
        carousel.setData(carouselList)
        carousel.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(data[position].loc, 17.0F))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingtmp = FragmentMapsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }
}