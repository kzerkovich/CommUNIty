package com.kzerk.community.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kzerk.community.BuildConfig
import com.kzerk.community.R
import com.kzerk.community.databinding.ActivityMainBinding
import com.kzerk.community.utils.openFragment
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		onBottomNavClicks()
		MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
		openFragment(EventsFragment.newInstance())
	}

	private fun onBottomNavClicks() {
		binding.bNan.setSelectedItemId(R.id.id_events)

		binding.bNan.setOnItemSelectedListener {
			when(it.itemId) {
				R.id.id_discount -> openFragment(PromoFragment.newInstance())
				R.id.id_calendar -> openFragment(CalendarFragment.newInstance())
				R.id.id_events -> openFragment(EventsFragment.newInstance())
				R.id.id_organizations -> openFragment(OrganizationsFragment.newInstance())
				R.id.id_profile -> openFragment(ProfileFragment.newInstance())
			}
			true
		}
	}
}