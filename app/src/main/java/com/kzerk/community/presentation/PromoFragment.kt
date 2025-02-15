package com.kzerk.community.presentation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kzerk.community.databinding.FragmentPromoBinding
import com.kzerk.community.utils.DialogManager
import com.kzerk.community.utils.checkPermission
import com.kzerk.community.utils.showToast
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer


class PromoFragment : Fragment() {
	private lateinit var mapKit: MapKit
	private lateinit var mapView: MapView
	private lateinit var binding: FragmentPromoBinding
	private lateinit var fusedLocationClient: FusedLocationProviderClient
	private lateinit var pLauncher: ActivityResultLauncher<Array<String>>
	private lateinit var userLocationLayer: UserLocationLayer

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		MapKitFactory.initialize(requireContext().applicationContext)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		mapKit = MapKitFactory.getInstance()
		binding = FragmentPromoBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
		mapView = binding.mapview
		userLocationLayer = mapKit.createUserLocationLayer(mapView.mapWindow)
		mapView.map.addTapListener(geoObjectTapListener)
		registerPermission()
	}

	private fun registerPermission() {
		pLauncher = registerForActivityResult(
			ActivityResultContracts.RequestMultiplePermissions()
		) {
			if (it[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
				getCurrentLocation()
				checkLocationEnabled()
			} else {
				showToast("Нет доступа к геолокации")
			}
		}
	}

	private fun checkLocationEnabled() {
		val lManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
		val isEnabled = lManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

		if (!isEnabled) {
			DialogManager.showLocationDialog(
				activity as AppCompatActivity,
				object : DialogManager.Listener {
					override fun onClick() {
						startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
					}
				}
			)
		}
	}

	override fun onStart() {
		super.onStart()
		mapKit.onStart()
		mapView.onStart()
	}

	override fun onResume() {
		super.onResume()
		checkLocPermission()
	}

	private fun checkLocPermission() {
		if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
			checkAfter10()
		} else {
			checkBefore10()
		}
	}

	@RequiresApi(Build.VERSION_CODES.Q)
	private fun checkAfter10() {
		if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
			&& checkPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
		) {
			getCurrentLocation()
			checkLocationEnabled()
		} else {
			pLauncher.launch(
				arrayOf(
					Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.ACCESS_BACKGROUND_LOCATION
				)
			)
		}
	}

	private fun checkBefore10() {
		if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
			getCurrentLocation()
			checkLocationEnabled()
		} else {
			pLauncher.launch(
				arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
			)
		}
	}

	private fun getCurrentLocation() {
		if (ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_FINE_LOCATION
			) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_COARSE_LOCATION
			) != PackageManager.PERMISSION_GRANTED
		) {
			return
		}
		fusedLocationClient.lastLocation
			.addOnSuccessListener { location ->
				if (location != null) {
					val point = Point(location.latitude, location.longitude)
					mapView.map.move(
						CameraPosition(
							point,
							17.0f,
							0.0f,
							10.0f
						),
						Animation(Animation.Type.SMOOTH, 1.0f),
						null
					)
					userLocationLayer.isVisible = true
					userLocationLayer.isHeadingEnabled = true
				} else {
					throw RuntimeException("location is null")
				}
			}
			.addOnFailureListener { _ ->
				throw RuntimeException("Can't get a last position")
			}
	}

	private val geoObjectTapListener = GeoObjectTapListener { event ->
		val geoObject = event.geoObject
		val name = geoObject.name ?: "Название отсутствует"

		Toast.makeText(
			requireContext(),
			name,
			Toast.LENGTH_LONG
		).show()

		true
	}

	override fun onStop() {
		super.onStop()
		mapView.onStop()
		mapKit.onStop()
	}

	companion object {
		fun newInstance() = PromoFragment()
	}
}