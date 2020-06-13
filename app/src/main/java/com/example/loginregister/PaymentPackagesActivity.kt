package com.example.loginregister

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class PaymentPackagesActivity : AppCompatActivity() {

    companion object {

        var paymentPackagesActivityObj: PaymentPackagesActivity? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_packages)

        paymentPackagesActivityObj?.finish()
        paymentPackagesActivityObj = this

    }

    fun goldPackageClicked(view: View) {
        showPaymentOptionDialog()
    }

    fun silverPackageClicked(view: View) {
        showPaymentOptionDialog()
    }

    fun bronzePackageClicked(view: View) {
        showPaymentOptionDialog()
    }

    private fun showPaymentOptionDialog() {
        val layoutInflater = LayoutInflater.from(this@PaymentPackagesActivity)
        val customView = layoutInflater.inflate(R.layout.payment_options_layout, null)
        val myBox: AlertDialog.Builder = AlertDialog.Builder(this@PaymentPackagesActivity)
        myBox.setView(customView)

        val dialog = myBox.create()
        dialog.show()

        val paypalView = customView.findViewById<LinearLayout>(R.id.paypalView)
        paypalView.setOnClickListener {
            startActivity(Intent(applicationContext, PaypalActivity::class.java))
            dialog.dismiss()
        }

        val cardView = customView.findViewById<LinearLayout>(R.id.cardView)
        cardView.setOnClickListener {
            startActivity(Intent(applicationContext, PaymentUsingCard::class.java))
            dialog.dismiss()
        }

    }

}
