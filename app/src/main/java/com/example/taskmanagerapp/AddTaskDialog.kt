package com.example.taskmanagerapp

import android.app.Dialog
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.DialogFragment

class AddTaskDialog(val onAdd: (String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_add_task)

        val input = dialog.findViewById<EditText>(R.id.etTask)
        val btn = dialog.findViewById<Button>(R.id.btnSave)

        btn.setOnClickListener {
            onAdd(input.text.toString())
            dismiss()
        }

        return dialog
    }
}