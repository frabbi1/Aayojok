package com.conv.aayojok.Fragments

import android.app.DatePickerDialog


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.conv.aayojok.Activity.BottomNav
import com.conv.aayojok.Models.Event
import com.conv.aayojok.Models.EventSupplier
import com.conv.aayojok.R
import kotlinx.android.synthetic.main.fragment_new_event.*

import kotlinx.android.synthetic.main.fragment_new_event.view.*
import java.text.SimpleDateFormat
import java.util.*

class RequestEventFragment: Fragment() {


    lateinit var startDate:String
    lateinit var  endDate:String
    lateinit var  currentDate:String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_new_event,null)

        root.btn_submit.setOnClickListener {


           if(root.edt_event_name.toString().trim().isEmpty() || root.edt_sd.toString().trim().isEmpty() || root.tv_edate.text == "Not set yet" || root.sdate_tv.text == "Not set yet"){
                Toast.makeText(this@RequestEventFragment.context,"Fill up the required fields",Toast.LENGTH_SHORT).show()
            }

           else{
               var sdf = SimpleDateFormat("dd/mm/yyyy")
               var d1 = sdf.parse(startDate)
               var d2 = sdf.parse(endDate)
               var cdate = sdf.parse(currentDate)
               if(d1.after(d2) || d1.before(cdate) || d2.before(cdate)){
                   Toast.makeText(this@RequestEventFragment.context,"Check Date Validity",Toast.LENGTH_SHORT).show()
               }
               else{
                   var event = Event("1",
                       root.edt_event_name.text.toString(),
                       "IIT",startDate,endDate,root.edt_sd.text.toString(),
                       "12.22","12.22","1234","8")
                   EventSupplier.addEvent(event)

                   var i = Intent(this@RequestEventFragment.context, BottomNav::class.java)
                   startActivity(i)

               }

            }


        }

        //Date and Time
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        currentDate = "$day/$month/$year"




        root.sdate.setOnClickListener {
            val dpd = DatePickerDialog(this@RequestEventFragment.context!!, DatePickerDialog.OnDateSetListener { _, y, m, d ->

                val dateText = """$d/${(m + 1)}/$y"""
                //Toast.makeText(this@RequestEventFragment.context,dateText,Toast.LENGTH_SHORT).show()
                root.sdate_tv.text = dateText
                startDate = dateText


            }, year, month, day)
            dpd.show()
        }

        root.btn_edate.setOnClickListener {
            val dpd = DatePickerDialog(this@RequestEventFragment.context!!, DatePickerDialog.OnDateSetListener { _, y, m, d ->

                val dateText = """$d/${(m + 1)}/$y"""
                //Toast.makeText(this@RequestEventFragment.context,dateText,Toast.LENGTH_SHORT).show()
                root.tv_edate.text = dateText
                endDate = dateText


            }, year, month, day)
            dpd.show()
        }






        return root


    }


}