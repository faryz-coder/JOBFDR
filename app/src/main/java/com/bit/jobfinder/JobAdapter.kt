package com.bit.jobfinder

import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class JobAdapter(private val job: MutableList<Job>) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.row_title)
        val company: TextView = itemView.findViewById(R.id.row_company)
        val salary: TextView = itemView.findViewById(R.id.row_salary)
        val jobLayout = itemView.findViewById<ConstraintLayout>(R.id.job_row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_row, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = job[position].title
        holder.company.text = job[position].company
        holder.salary.text = "MYR ${job[position].salary}"

        holder.jobLayout.setOnClickListener {
            d("bomoh", "press ${job[position].title}")
            val intent = Intent(it.context, Description::class.java)
            intent.putExtra("title", job[position].title)
            intent.putExtra( "description", job[position].description)
            intent.putExtra("company", job[position].company)
            intent.putExtra( "salary", job[position].salary)
            intent.putExtra("location", job[position].location)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = job.size

}
