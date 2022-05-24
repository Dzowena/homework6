package com.example.homework6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import com.example.homework6.R.layout.activity_main
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        done_button.setOnClickListener {
            if(count_input.text.isEmpty())
                return@setOnClickListener

            val count = Integer.parseInt(count_input.text.toString())

            val database = Firebase.database
            val myRef = database.getReference("cities")

            // Read from the database
            val citiesRef = myRef.child("cities").limitToFirst(count)
            citiesRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val cities = mutableListOf<String>()
                    var i = 0
                    for (childSnapshot in snapshot.children) {
                        val city = childSnapshot.getValue<String>()
                        if (city != null) {
                            cities.add(city)
                            i++
                            if (i == count) break
                        }
                    }

                    //val adapter = CityAdapter(this@MainActivity, cities as ArrayList<String>)
                    val adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, cities)
                    list_view.adapter = adapter
                    adapter.notifyDataSetInvalidated()

                    done_button.visibility = View.GONE
                    count_input.visibility = View.GONE
                    list_view.visibility = View.VISIBLE
                    back_button.visibility = View.VISIBLE
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }

        back_button.setOnClickListener {
            done_button.visibility = View.VISIBLE
            count_input.visibility = View.VISIBLE
            list_view.visibility = View.GONE
            back_button.visibility = View.GONE
        }
    }
}