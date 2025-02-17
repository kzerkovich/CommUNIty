package com.kzerk.community.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.CalendarWeekDay
import com.kzerk.community.R
import com.kzerk.community.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment() {
	private lateinit var binding: FragmentCalendarBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCalendarBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val calendarView: CalendarView = binding.calendarView

		calendarView.setHeaderVisibility(View.GONE)
		calendarView.setFirstDayOfWeek(CalendarWeekDay.MONDAY)
		calendarView.setCalendarDayLayout(R.layout.calendar_day)
	}

	companion object {
		@JvmStatic
		fun newInstance() = CalendarFragment()
	}
}