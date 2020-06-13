package com.example.loginregister

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaypalActivity : AppCompatActivity() {

    private val url = "https://www.paypal.com/signin?returnUri=https%3A%2F%2Fwww.paypal.com%2Fcgi-bin%2Fwebscr%3fcmd%3d_account"
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypal)

        webView = findViewById(R.id.webView)

        val webViewSettings = webView.settings
        webViewSettings.javaScriptEnabled = true

        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY

        val loaderDialog = loader(this, "Please wait")

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                webView.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (loaderDialog.isShowing) {
                    loaderDialog.dismiss()
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                if (loaderDialog.isShowing) {
                    loaderDialog.dismiss()
                }
                showMessage("Something went wrong.\n$error")
            }
        }

        webView.loadUrl(url)
    }

    private fun loader(activity: Activity, message: String): AlertDialog {
        val layoutInflater = LayoutInflater.from(activity)
        val customView = layoutInflater.inflate(R.layout.loading_dialog_layout, null)
        val myBox: AlertDialog.Builder = AlertDialog.Builder(activity)
        myBox.setView(customView)
        val messageTextView: TextView = customView.findViewById(R.id.message)
        if (message.trim().isNotEmpty()) {
            messageTextView.text = message
        }
        val customProgressDialog = myBox.create()
        customProgressDialog.setCancelable(false)
        customProgressDialog.show()
        return customProgressDialog
    }

    private fun showMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            dialogInterface.dismiss()
            finish()
        }

        val alert = builder.create()
        alert.show()
    }
}
