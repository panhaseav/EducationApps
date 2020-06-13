package com.example.loginregister

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*
import kotlin.collections.ArrayList


class ChatActivity : AppCompatActivity() {

    companion object {
        private const val ACCESS_TOKEN = "8f274fdecdab401e8935ecf304960567"
    }

    private lateinit var list: ArrayList<MessageModel>
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        FuelManager.instance.baseHeaders = mapOf("Authorization" to "Bearer $ACCESS_TOKEN")

        FuelManager.instance.basePath = "https://api.dialogflow.com/v1/"

        FuelManager.instance.baseParams = listOf(
            "v" to "20170712",                  // latest protocol
            "sessionId" to UUID.randomUUID(),   // random ID
            "lang" to "en"                      // English language
        )

        list = ArrayList()
        adapter = ChatAdapter(list)

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

        addMessageToList("Hello! I am bot, from LearningLApp", "bot")
    }

    fun sendMessage(view: View) {

        val faqFilters = listOf(
            "what your app do ?",
            "what is the purpose of this app ?",
            "i need to read FAQ",
            "show me FAQ",
            "show me faq",
            "faq please",
            "how does the app work?",
            "FAQ please",
            "faq",
            "How does the app work"
        )

        val message = et_message.text.toString().trim()
        if (message.isNotEmpty()) {

            addMessageToList(message, "user")
            et_message.setText("")

            if (message.contains("@")) {
                addMessageToList("Thank You. We will let you know on your email.", "bot")
            } else if (faqFilters.contains(message)) {
                addMessageToList("Here is FAQ section", "bot", true)
            } else {
                try {
                    Fuel.get(
                        "/query",
                        listOf("query" to message)
                    ).responseJson { _, _, result ->
                        var reply = result.get().obj()
                            .getJSONObject("result")
                            .getJSONObject("fulfillment")
                            .getString("speech")

                        if (reply.trim().isEmpty()) {
                            reply = "Opps. I could not understand."
                        }

                        addMessageToList(reply, "bot")
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Exception: $e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addMessageToList(message: String, user: String, actionMessage: Boolean = false) {
        list.add(MessageModel(message, System.currentTimeMillis(), user, actionMessage))
        adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(list.size - 1)
    }

}
