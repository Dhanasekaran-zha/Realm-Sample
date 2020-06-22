package com.example.realm_sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var MyDb: Realm? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyDb = RealmApplication.instance!!.getRealmInstance()

        save.setOnClickListener(this)
        search.setOnClickListener(this)
        delete.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            save -> SaveToDB()
            search -> GetfFromDB()
            delete -> DelFromDB()
        }
    }

    private fun SaveToDB() {
        val name = Sname.text.toString()
        val Id = reg_num.text.toString()
        try {
            MyDb!!.beginTransaction()
            val us = Model()
            us.Id = Id.toInt()
            us.Name = name
            MyDb!!.insertOrUpdate(us)
            MyDb!!.commitTransaction()
        } catch (ex: Exception) {
            Log.d("RError", ex.toString())
            Toast.makeText(this, "Error in realm", Toast.LENGTH_SHORT).show()
        }

    }

    private fun GetfFromDB() {
        val Id = reg_num.text.toString()
        val name = Sname.text.toString()
        if(name.isEmpty()) {
            val data = MyDb!!.where<Model>().equalTo("Id", Id.toInt()).findFirst()
            if (data != null) {
                Sname.setText(data.Name)
            } else {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_LONG).show()
            }
        }else{
            val data = MyDb!!.where<Model>().equalTo("Name",name).findFirst()
            if (data != null) {
                reg_num.setText(data.Id.toString())
            } else {
                Toast.makeText(this, "Data Not Found", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun DelFromDB() {
        val Id = reg_num.text.toString()
        val name = Sname.text.toString()
        MyDb!!.beginTransaction()
        val data = MyDb!!.where<Model>().equalTo("Id",Id.toInt()).findAll()
        data!!.deleteAllFromRealm()
        MyDb!!.commitTransaction()
        Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show()

    }

}






