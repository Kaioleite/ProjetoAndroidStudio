package com.generation.on_g

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.generation.on_g.adapter.PostAdapter
import com.generation.on_g.adapter.TaskItemClickListener
import com.generation.on_g.databinding.FragmentPostagemBinding
import com.generation.on_g.modelo.Postagem
import com.generation.on_g.mvvm.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PostagemFragment : Fragment(), TaskItemClickListener{

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentPostagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel.postagem()

        binding = FragmentPostagemBinding.inflate(
                        layoutInflater, container,false
        )

        val intentMaps = Intent(context, MapsMainActivity::class.java)






         /*
        val view = inflater.inflate(R.layout.fragment_postagem, container, false)
        val listPostagem = mutableListOf(Postagem(
            "Doação de Roupas",
            "Rua dois, Zona Leste - SP",
            "Doações feitas online",
            "Vestimentas",
            "Campanha do Agasalho",
            "22-03-2022 / 09:00"
        )
          */

        //val floatingAdd = view.findViewById<FloatingActionButton>(R.id.floatingAdd)

        binding.floatingAdd.setOnClickListener{
            mainViewModel.postagemSelecionada = null
            findNavController().navigate(R.id.action_postagemFragment_to_formularioPostFragment)
        }

       binding.bMaps.setOnClickListener {
           startActivity(intentMaps)
       }

        val recyclerPost = binding.recyclerPost
        val postAdapter = PostAdapter(context,this, mainViewModel)

        binding.recyclerPost.adapter = postAdapter
        binding.recyclerPost.setHasFixedSize(true)
        //postAdapter.attList(listPostagem)
        binding.recyclerPost.layoutManager = LinearLayoutManager(context)

        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner, {

                response -> if(response != null){
                    postAdapter.attList(response.body()!!)
        }
        })

        return binding.root
    }

    override fun onTaskClicked(postagem: Postagem) {
        mainViewModel.postagemSelecionada = postagem
        findNavController().navigate(R.id.action_postagemFragment_to_formularioPostFragment)
    }
}