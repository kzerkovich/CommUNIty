package com.kzerk.community.utils

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kzerk.community.R

fun Fragment.openFragment(fragment: Fragment) {
	(activity as AppCompatActivity).supportFragmentManager
		.beginTransaction()
		.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
		.replace(R.id.fragment_container, fragment)
		.commit()
}

fun AppCompatActivity.openFragment(fragment: Fragment) {
	if (supportFragmentManager.fragments.isNotEmpty()) {
		if (supportFragmentManager.fragments[0].javaClass == fragment.javaClass) return
	}
	supportFragmentManager
		.beginTransaction()
		.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
		.replace(R.id.fragment_container, fragment)
		.commit()
}

fun Fragment.showToast(message: String) {
	Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.checkPermission(permission: String): Boolean {
	return when(PackageManager.PERMISSION_GRANTED) {
		ContextCompat.checkSelfPermission(activity as AppCompatActivity, permission) -> true
		else -> false
	}
}