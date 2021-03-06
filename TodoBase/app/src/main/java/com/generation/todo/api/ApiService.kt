package com.generation.todo.api

import com.generation.todo.model.Categoria
import com.generation.todo.model.Tarefa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("categoria")
    suspend fun ListCategoria(): Response<List<Categoria>>
   // Adicionar nova Tarefa
    @POST("tarefa")
    suspend fun addTarefa(
     @Body tarefa : Tarefa
    ): Response<Tarefa>
  //Requisições Tarefas
    @GET("tarefa")
    suspend fun listTarefas(): Response<List<Tarefa>>
}
