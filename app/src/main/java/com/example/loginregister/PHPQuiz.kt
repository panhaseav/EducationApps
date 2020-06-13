package com.example.loginregister

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.educationnight.Question
import kotlinx.android.synthetic.main.activity_python_quiz.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException

@Suppress("DEPRECATION")
class PHPQuiz : AppCompatActivity() {


    lateinit var context: Context
    var QuestionList: MutableList<Question> = ArrayList()
    var index = -1
    var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_python_quiz)
        context = this
        btn_next.isEnabled = false
        btn_next.alpha - 0.5.toFloat()

        getQuestions().execute()

    }

    fun UpdateQuestion() {
        val selected = rg_choice.checkedRadioButtonId
        if (selected == -1) {
            Toast.makeText(this, "Please select answer.", Toast.LENGTH_SHORT).show()
            return
        }
        if (index < QuestionList.size) {
            when (selected) {
                rb_choice1.id -> {
                    if (QuestionList[index].Answer == 1)
                        score++

                }
                rb_choice2.id -> {
                    if (QuestionList[index].Answer == 1)
                        score++

                }
                rb_choice3.id -> {
                    if (QuestionList[index].Answer == 1)
                        score++

                }
                rb_choice4.id -> {
                    if (QuestionList[index].Answer == 1)
                        score++

                }
            }
            index++
            if (index < QuestionList.size) {
                tv_question.text = QuestionList[index].Question
                rb_choice1.text = QuestionList[index].Option1
                rb_choice2.text = QuestionList[index].Option2
                rb_choice3.text = QuestionList[index].Option3
                rb_choice4.text = QuestionList[index].Option4
                rg_choice.clearCheck()
                if ((index + 1) == QuestionList.size)
                    btn_next.text = "Finish"

            } else {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("Your Score")
                dialog.setMessage("You have answer correct " + score + "out of " + QuestionList.size + "Question.")
                dialog.setPositiveButton(
                    "Close",
                    DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                        finish()
                    })
                dialog.show()

            }
        }
    }

    internal inner class getQuestions : AsyncTask<Void, Void, String>() {
        lateinit var progressDialog: ProgressDialog
        var hasInternet = false


        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Downloading Question")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }


        override fun doInBackground(vararg p0: Void?): String {
            if (isNetworkAvailable()) {
                hasInternet = true
                val client = OkHttpClient()
                val url =
                    "https://script.googleusercontent.com/macros/echo?user_content_key=1tgBN-ES-vsiLin8Lggs7R094sUSEWlBY3Lv7yLt0KnrexUuaTvreORsTenxGH0HaPDQ0rUkXVqmkc903P_gQrpXCbi98gcsm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnBg4Wj9So2Q_mI0_S0Bm21-AGmcRnplmVaRcxvVzvCi9cnQQJegsnVb9TgJzPufw35cdv3aNHr6K&lib=MKMzvVvSFmMa3ZLOyg67WCThf1WVRYg6Z"
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                return response.body()?.string().toString()
            } else {
                return ""
            }
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            if (hasInternet) {
                try {
                    val resultArray = JSONArray(result)

                    for (i in 0..(resultArray.length() - 1)) {
                        val currentObject = resultArray.getJSONObject(i)
                        val obj = Question()
                        obj.Question = currentObject.getString("Question")
                        obj.Option1 = currentObject.getString("Option1")
                        obj.Option2 = currentObject.getString("Option2")
                        obj.Option3 = currentObject.getString("Option3")
                        obj.Option4 = currentObject.getString("Option4")
                        obj.Answer = currentObject.getInt("Answer")
                        QuestionList.add(obj)
                    }
                    if (index == -1) {
                        index++
                        tv_question.text = QuestionList[index].Question
                        rb_choice1.text = QuestionList[index].Option1
                        rb_choice2.text = QuestionList[index].Option2
                        rb_choice3.text = QuestionList[index].Option3
                        rb_choice4.text = QuestionList[index].Option4
                    }
                    btn_next.isEnabled = true
                    btn_next.alpha = 1.toFloat()

                    btn_next.setOnClickListener({
                        UpdateQuestion()
                    })

                } catch (e: JSONException) {

                }
            }
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
