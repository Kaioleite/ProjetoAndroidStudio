package com.generation.todo.respository

import com.generation.todo.api.RetrofitInstance
import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import retrofit2.Response

class Repository {
    suspend fun listCategoria():Response<List<Categoria>>{
        return RetrofitInstance.api.ListCategoria()
    }

    suspend fun addTarefa(tarefa : Tarefa):Response<Tarefa>{
        return RetrofitInstance.api.addTarefa(tarefa)

    }
    suspend fun listTarefas(): Response<List<Tarefa>>{
         return RetrofitInstance.api.listTarefas()
    }
    suspend fun updateTarefa(tarefa: Tarefa): Response<Tarefa>{
        return RetrofitInstance.api.updateTarefa(tarefa)
    }
    suspend fun deleteTarefa(id: Long): Response<Tarefa>{
        return RetrofitInstance.api.deleteTarefa(id)
    }
}


