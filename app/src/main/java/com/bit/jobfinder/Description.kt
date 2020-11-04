package com.bit.jobfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Description : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_main)

        val title = intent.getStringExtra("title")
        val company = intent.getStringExtra("company")
        val description = intent.getStringExtra("description")
        val salary = intent.getStringExtra("salary")
        val location = intent.getStringExtra("location")

        val d_title = findViewById<TextView>(R.id.des_title)
        val d_company = findViewById<TextView>(R.id.des_company)
        val d_description = findViewById<TextView>(R.id.des_description)
        val d_salary = findViewById<TextView>(R.id.des_salary)
        val d_location = findViewById<TextView>(R.id.des_location)

        d_title.text = title
        d_company.text = company
        d_description.text = description
        d_salary.text = "MYR $salary"
        d_location.text = location

        d_location.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.navigation:q=$location")
            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps")

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent)
        }

    }
}