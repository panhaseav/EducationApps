package com.example.loginregister

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(var list: List<MessageModel>) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // bind the model to view
        val messageText = view.findViewById<TextView>(R.id.messageText)
        val messageTime = view.findViewById<TextView>(R.id.messageTime)
        val faqView = view.findViewById<LinearLayout>(R.id.faqView)
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].sender.trim().equals("user", true)) {
            0
        } else {
            if (list[position].actionMessage) {
                2
            } else {
                1
            }
        }
    }

    // inflate the xml layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType) {
            0 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_sender, parent, false)
            }
            2 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_receiver_faq_section, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_receiver, parent, false)
            }
        }
        context = parent.context
        return ViewHolder(view)
    }

    // number of items to show
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.messageText.text = list[position].messageText
        holder.messageTime.text = getReadableTime(list[position].messageTime)
        if (getItemViewType(position) == 2) {
            holder.faqView.setOnClickListener {
                context.startActivity(Intent(context, FaqActivity::class.java))
            }
        }
    }

    fun getReadableTime(d: Long?): String? {
        val mFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        return mFormat.format(d)
    }

}