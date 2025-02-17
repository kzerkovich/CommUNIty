package com.kzerk.community.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kzerk.community.databinding.BottomSheetOrganisationBinding
import com.kzerk.community.databinding.FragmentOrganizationsBinding

class OrganizationsFragment : Fragment() {
	private lateinit var binding: FragmentOrganizationsBinding
	private lateinit var bottomSheetDialog: BottomSheetDialog
	private lateinit var bottomSheet: BottomSheetOrganisationBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentOrganizationsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bottomSheetDialog = BottomSheetDialog(requireContext())
		bottomSheet = BottomSheetOrganisationBinding.inflate(layoutInflater)
		bottomSheetDialog.setContentView(bottomSheet.root)

		binding.rvEvents.setOnClickListener {
			bottomSheetDialog.show()
		}
	}
	companion object {
		@JvmStatic
		fun newInstance() = OrganizationsFragment()
	}
}