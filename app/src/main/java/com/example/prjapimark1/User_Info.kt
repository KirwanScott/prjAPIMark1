package com.example.prjapimark1

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class User_Info(private val name: String, private val surname: String, private val amount: Int) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.activity_user_info, null)

        view.findViewById<TextView>(R.id.nameTextView).text = "$name"
        view.findViewById<TextView>(R.id.surnameTextView).text = "$surname"
        view.findViewById<TextView>(R.id.amountTextView).text = "R$amount"

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}