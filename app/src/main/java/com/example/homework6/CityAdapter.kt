package com.example.homework6

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class CityAdapter(context: Context, citiesList: ArrayList<String>) : BaseAdapter() {
    val cities: ArrayList<String>
    val inflator: LayoutInflater

    init {
        cities = citiesList
        inflator = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return cities.size
    }

    override fun getItem(position: Int): Any {
        return cities[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        var itemView: View

        if (convertView == null) {
            itemView = inflator.inflate(android.R.layout.simple_list_item_1, container, false)
        } else {
            itemView = convertView
        }

        if (itemView != null) {
            var textView = itemView.findViewById<TextView>(android.R.id.text1)
            textView.text = cities[position]
        }

        return itemView
    }

}