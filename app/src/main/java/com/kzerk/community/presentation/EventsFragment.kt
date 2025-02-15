package com.kzerk.community.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kzerk.community.R
import com.kzerk.community.databinding.FragmentEventsBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

class EventsFragment : Fragment() {
	private lateinit var binding: FragmentEventsBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentEventsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}

	companion object {
		@JvmStatic
		fun newInstance() =	EventsFragment()
	}
}