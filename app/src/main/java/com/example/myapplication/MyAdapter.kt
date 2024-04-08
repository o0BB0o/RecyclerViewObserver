package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer
import java.util.TimerTask

class MyAdapter(private val tempElements : ArrayList<String>,private val viewModel: BlankViewModel): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var timerTask: TimerTask? = null
    private val timer = Timer()
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val number: TextView
        val context: TextView
        init {
            number = view.findViewById(R.id.id)
            context = view.findViewById(R.id.content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tempElements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number.text = position.toString()
        holder.context.text = tempElements[position]
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.w("D", "Start Observing ${holder.adapterPosition}")
        timerTask?.cancel()

        timerTask = object : TimerTask() {
            override fun run() {
                viewModel.viewed(holder.adapterPosition)
            }
        }
        timer.schedule(timerTask, 2000)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        timerTask?.cancel()
    }
}