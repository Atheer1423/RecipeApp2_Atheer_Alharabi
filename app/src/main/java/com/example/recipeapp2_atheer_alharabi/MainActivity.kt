package com.example.recipeapp2_atheer_alharabi

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var Inputs: ArrayList<dataRec>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         lateinit var Rc: RecyclerView
//        val responseText = findViewById<View>(R.id.tv1) as TextView
        val bAdd = findViewById<View>(R.id.b1) as Button
        Rc = findViewById(R.id.rv1)
        Inputs=ArrayList()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        bAdd.setOnClickListener {
            addRec()
        }
        if (apiInterface != null) {
            apiInterface.getRecipe()?.enqueue(object : Callback<List<Rec.RecDetails>> {
                override fun onResponse(
                    call: Call<List<Rec.RecDetails>>,
                    response: Response<List<Rec.RecDetails>>
                ) {
                    progressDialog.dismiss()
                    var stringToBePritined:String? = "";
                    for(rec in response.body()!!){
                        Inputs.add(dataRec(rec.title.toString(),rec.author.toString() ,rec.ingre.toString(),rec.instru.toString()))
//                        stringToBePritined = stringToBePritined +rec.title+ "\n"+rec.author + "\n"+rec.ingre+"\n"+rec.instru+"\n"+"\n"
                        Rc.adapter?.notifyDataSetChanged()
                    }
//                    responseText.text= stringToBePritined
                    Rc.adapter = itemClass(applicationContext, Inputs)
                    Rc.layoutManager = LinearLayoutManager(applicationContext)
                }
                override fun onFailure(call: Call<List<Rec.RecDetails>>, t: Throwable) {
                    //  onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }
            })
        }

    }

    fun addRec() {
        intent = Intent(applicationContext, NewRecipe::class.java)
        startActivity(intent)
    }


}
