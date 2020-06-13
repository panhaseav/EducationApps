package com.example.loginregister

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_payment_using_card.*
import java.util.*

class PaymentUsingCard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_using_card)
    }

    fun payNowClicked(view: View) {

        val fullNameStr = fullName.text.toString().trim()
        val creditCardNumberStr = creditCardNumber.text.toString().trim()
        val expiryMonthStr = expiryMonth.text.toString().trim()
        val expiryYearStr = expiryYear.text.toString().trim()
        val ccvStr = ccv.text.toString().trim()
        val addressStr = address.text.toString().trim()

        var isError = false

        if (addressStr.isEmpty()) {
            address.error = "Please enter address"
            address.requestFocus()
            isError = true
        }

        if (ccvStr.isEmpty()) {
            ccv.error = "Please enter ccv"
            ccv.requestFocus()
            isError = true
        }

        if (expiryYearStr.isEmpty()) {
            expiryYear.error = "Please provide expiry year"
            expiryYear.requestFocus()
            isError = true
        }

        if (expiryMonthStr.isEmpty()) {
            expiryMonth.error = "Please provide expiry month"
            expiryMonth.requestFocus()
            isError = true
        } else {

            try {

                val monthInt = Integer.parseInt(expiryMonthStr)
                if (!(monthInt in 1..12)) {
                    expiryMonth.error = "Please provide valid expiry month"
                    expiryMonth.requestFocus()
                }

            } catch (e: Exception) {
                expiryMonth.error = "Please provide valid expiry month"
                expiryMonth.requestFocus()
            }

        }

        if (creditCardNumberStr.isEmpty() || creditCardNumberStr.length != 16) {
            creditCardNumber.error = "Please enter valid card number"
            creditCardNumber.requestFocus()
            isError = true
        }

        if (fullNameStr.isEmpty()) {
            fullName.error = "Please enter full name"
            fullName.requestFocus()
            isError = true
        }

        if (!isError) {
            showSuccessMessage()
        }

    }

    private fun showSuccessMessage() {
        val message =
            "Payment Made successfully. Your order number is ${generateOrderNumber()}. We will let you know soon."
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            dialogInterface.dismiss()
            finish()
            PaymentPackagesActivity.paymentPackagesActivityObj?.finish()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun generateOrderNumber(): String {
        val size = 5
        val allowedCharacters = "0123456789"
        val random = Random()
        val sb = StringBuilder(size)
        for (i in 0 until size)
            sb.append(allowedCharacters[random.nextInt(allowedCharacters.length)])
        return sb.toString()
    }

}
