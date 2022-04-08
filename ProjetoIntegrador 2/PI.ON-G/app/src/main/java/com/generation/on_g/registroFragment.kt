package com.generation.on_g

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.generation.on_g.databinding.FragmentRegistroBinding


class registroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistroBinding.inflate(layoutInflater, container, false)

        val intent = Intent(context, MainActivity::class.java)
        binding.ButtonRegistrar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val email = binding.editEmail.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val senha = binding.editSenha.text.toString()
            val confirSenha = binding.editConfiSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()
                || senha.isEmpty() || confirSenha.isEmpty()){
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_LONG
                ).show()

            }
            if(senha.length <= 6 ) {binding.textAviso.text = "A senha deve ser maior que 6 digitos.**"}
            else { binding.textAviso.text = "A senha contém 6 dígitos."}


            val sD = senha.filter { it.isDigit() }

            if (  sD.isEmpty())
            {binding.textDigito.text = "A senha deve ter um número no dígito.**"
            }else { binding.textDigito.text = "A senha tem pelo menos 1 dígito."}


            val sM = senha.filter { it.isUpperCase() }

            if (sM.isEmpty() ) {
                binding.textLetraMai.text = "A senha deve ter letra maiúscula.**"
            } else { binding.textLetraMai.text = "A senha contém letra maiúscula."}


            val sMin = senha.filter { it.isLowerCase() }

            if (sMin.isEmpty())
            {binding.textMinu.text = "A senha deve ter uma letra minúscula.**"
            }else { binding.textMinu.text = "A senha tem letra minúscula."}


            if(telefone.length <=10){
                binding.textvalidaTel.text = "Número não existe! Verifique se o telefone tem DDD.** "
            } else {
                binding.textvalidaTel.text = "Telefone confirmado! "
            }


            if (email.contains('@')){
                binding.textAvisoEmail.text = "O email está correto."
            }else { binding.textAvisoEmail.text = "O email não existe.**"}


            if (confirSenha != senha) {
                Toast.makeText(
                    context, "As senhas devem ser iguais", Toast.LENGTH_SHORT
                ).show()
            }




            if(nome.isNotEmpty() && email.isNotEmpty()&&  telefone.isNotEmpty() && senha.isNotEmpty()&&
                confirSenha.isNotEmpty() &&  senha.length >= 6 &&  sD.isNotEmpty() &&  sM.isNotEmpty() &&
                sMin.isNotEmpty() && telefone.length >=10 && email.contains('@') &&
                senha == confirSenha) {



                binding.ButtonRegistrar.setOnClickListener{
                    startActivity(intent)
                }
            }
        }
        return binding.root

    }
}