package com.example.recipeapp2_atheer_alharabi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewRecipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)

        val title = findViewById<View>(R.id.ed1) as EditText
        val author = findViewById<View>(R.id.ed2) as EditText
        val ingre = findViewById<View>(R.id.ed3) as EditText
        val instru = findViewById<View>(R.id.ed4) as EditText
        val savebtn = findViewById<View>(R.id.b2) as Button
        val viewbtn = findViewById<View>(R.id.b3) as Button
        viewbtn.setOnClickListener {
            viewRec()
        }
        savebtn.setOnClickListener {

            var newRec = Rec.RecDetails(title.text.toString(), author.text.toString(),ingre.text.toString(), instru.text.toString())

            addSingleRec(newRec, onResult = {
                title.setText("")
                author.setText("")
                ingre.setText("")
                instru.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
            })
        }
    }
    private fun addSingleRec(newRec: Rec.RecDetails, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@NewRecipe)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addRecipe(newRec).enqueue(object : Callback<Rec.RecDetails> {
                override fun onResponse(
                    call: Call<Rec.RecDetails>,
                    response: Response<Rec.RecDetails>
                ) {

                    onResult()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<Rec.RecDetails>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        }
    }

    fun addRec() {
        intent = Intent(applicationContext, NewRecipe::class.java)
        startActivity(intent)
    }

    fun viewRec() {
        intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}