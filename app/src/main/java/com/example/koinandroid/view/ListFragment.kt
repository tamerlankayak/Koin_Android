package com.example.koinandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinandroid.databinding.FragmentListBinding
import com.example.koinandroid.model.CryptoModel
import com.example.koinandroid.service.CryptoAPI
import com.example.koinandroid.viewmodel.CryptoViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment(), RecyclerViewAdapter.Listener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf(), this)
    private lateinit var viewModel: CryptoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.getDataFromAPI()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(requireContext(), "Clicked on : ${cryptoModel.currency}", Toast.LENGTH_SHORT)
            .show()
    }
}