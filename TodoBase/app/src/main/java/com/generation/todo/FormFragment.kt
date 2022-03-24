package com.generation.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.todo.databinding.FragmentFormBinding
import com.generation.todo.mainviewmodel.MainViewModel
import com.generation.todo.respository.Repository

class FormFragment : Fragment() {
    private  lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(
            layoutInflater, container, false
        )
        val  repository = Repository()
        mainViewModel = MainViewModel(repository)
        mainViewModel.listCategoria()
        mainViewModel.responseListCategoria.observe(viewLifecycleOwner){
            response ->  Log.d("Requisicao",response.body().toString())
        }

        binding.buttonSalvar.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }
        
        return binding.root
    }
}