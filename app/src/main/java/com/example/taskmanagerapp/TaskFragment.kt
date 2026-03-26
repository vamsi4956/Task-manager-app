package com.example.taskmanagerapp

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import android.content.Intent

class TaskFragment : Fragment() {

    private val taskList = mutableListOf("Task 1", "Task 2")
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_task, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val btn = view.findViewById<Button>(R.id.btnAdd)

        // Adapter setup
        adapter = TaskAdapter(taskList)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        val webBtn = view.findViewById<Button>(R.id.btnWeb)

        webBtn.setOnClickListener {
            val intent = Intent(requireContext(), WebActivity::class.java)
            startActivity(intent)
        }

        // Add Task Dialog
        btn.setOnClickListener {
            AddTaskDialog { task ->
                taskList.add(task)
                adapter.notifyDataSetChanged()
            }.show(parentFragmentManager, "dialog")
        }

        // 🔥 FETCH FROM API (Retrofit)
        fetchTasksFromApi()

        return view
    }

    private fun fetchTasksFromApi() {
        lifecycleScope.launch {
            try {
                val apiTasks = RetrofitInstance.api.getTasks()

                taskList.clear()
                taskList.addAll(apiTasks.take(5).map { it.title })

                adapter.notifyDataSetChanged()

            } catch (e: Exception) {
                Toast.makeText(context, "API Failed", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}