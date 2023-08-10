package com.example.prjapimark1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val planIdEditText = findViewById<EditText>(R.id.planIdEditText)
        val fetchButton = findViewById<Button>(R.id.fetchButton)


        fetchButton.setOnClickListener {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute{

                val url = URL("https://wordapidata.000webhostapp.com/Dis.php")
                val json = url.readText()
                val usersList = Gson().fromJson(json, Array<Users>::class.java).toList()

                val desiredPlanID = planIdEditText.text.toString().toIntOrNull()

                val desiredUser = usersList.find { it.PlanID == desiredPlanID }

                Handler(Looper.getMainLooper()).post{
                    if (desiredUser != null) {
                        // Show the floating dialog with user information
                        val userInfo = User_Info(desiredUser.Name, desiredUser.Surname, desiredUser.Amount)
                        userInfo.show(supportFragmentManager, "userInfo")
                    } else {
                        Toast.makeText(this, "No data was found",Toast.LENGTH_LONG).show()
                        Log.d("DesiredUser", "User with PlanID $desiredPlanID not found.")

                    }
                }
            }
        }
    }
}