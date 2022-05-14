package com.nathan.themoviedatabase.presentation.main.fragments.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nathan.themoviedatabase.R
import com.nathan.themoviedatabase.presentation.main.MainViewModel
import kotlinx.android.synthetic.main.bottom_sheet_search.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        confirm_button.setOnClickListener{
            editText.text ?.let { text ->
                if (!text.isEmpty()){

                } else {
                    Toast.makeText(requireContext(), "Please, insert a name", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}