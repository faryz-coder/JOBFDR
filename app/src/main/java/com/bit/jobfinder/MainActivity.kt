package com.bit.jobfinder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        val job = mutableListOf<Job>()
//        for (i in 0..5) {
//            job.add(0,Job("Dev${i}", "Bomoh iT", "sleep", "PJ", "3000"))
//        }
        fun recycler() {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = JobAdapter(job)
            }
        }

        fun getJob() {
            job.clear()
            db.collection("Job")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val title = document.getField<String>("title").toString()
                        val company = document.getField<String>("company").toString()
                        val description = document.getField<String>("description").toString()
                        val salary = document.getField<String>("salary").toString()
                        val location = document.getField<String>("location").toString()
                        job.add(0, Job(title, company, description, location, salary))
                    }
                    recycler()
                }
        }


        val docRef = db.collection("Job")
        docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (snapshot != null) {
                d("bomoh", "file added")
                getJob()
            } else {
                d("bomoh", "current data: null")
            }
        }

    }
}