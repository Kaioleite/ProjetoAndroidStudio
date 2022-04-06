package com.generation.todo.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.generation.todo.R
import com.generation.todo.mainviewmodel.MainViewModel
import com.generation.todo.model.Tarefa

//Configurar a classe para ser um Adapter
class TarefaAdapter (
                     private val context: Context?,
                     private val taskItemClickListener: TaskItemClickListener,
                      private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    private var listTarefas = emptyList<Tarefa>()

    class TarefaViewHolder (view: View) : RecyclerView.ViewHolder(view){

        val textNome = view.findViewById<TextView>(R.id.textNome)
        val textDescricao = view.findViewById<TextView>(R.id.textDescricao)
        val textResponsavel = view.findViewById<TextView>(R.id.textResponsavel)
        val textData = view.findViewById<TextView>(R.id.textData)
        val switchCardAtivo = view.findViewById<Switch>(R.id.switchCardAtivo)
        val textCategoria = view.findViewById<TextView>(R.id.textCategoria)
        val buttonDeletar = view.findViewById<Button>(R.id.buttonDeletar)

    }

    //Onde vamos dizer qual layout usaremos para os itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_tarefa, parent, false)

        return TarefaViewHolder(layout)
    }

    //Onde vamos processar todos os dados da lista
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefas[position]

        holder.textNome.text = tarefa.nome
        holder.textDescricao.text = tarefa.descricao
        holder.textResponsavel.text = tarefa.responsavel
        holder.textData.text = tarefa.data
        holder.switchCardAtivo.isChecked = tarefa.status
        holder.textCategoria.text = tarefa.categoria.descricao

        holder.itemView.setOnClickListener{
            taskItemClickListener.onTaskClicked(tarefa)

            holder.switchCardAtivo
                .setOnCheckedChangeListener{ compoundButton, ativo ->
                    tarefa.status = ativo
                    mainViewModel.updateTarefa(tarefa)

                }
        }
            holder.buttonDeletar.setOnClickListener{
                    launchAlertDialog(tarefa.id)
                    listTarefas[position]

        }
    }

    //Onde vamos dizer para o Adapter quantos itens temos na lista
    override fun getItemCount(): Int {
        return listTarefas.size
    }

    fun setLista(lista: List<Tarefa>){
        listTarefas = lista
        notifyDataSetChanged()
    }

    private fun launchAlertDialog(id:Long) {
        val builder = AlertDialog.Builder(context!!)
            .setTitle("Alerta: Deletar")
            .setMessage("Deseja deletar?")
            .setPositiveButton("Ok") { dialogInterface, i ->

                mainViewModel.deleteTarefa(id)
                Toast.makeText(context, "Post deletado.", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("Cancel") { dialogInterface, i -> dialogInterface.cancel() }
        builder.show()
    }

}