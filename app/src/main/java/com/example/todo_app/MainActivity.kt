package com.example.todo_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: dbhelper
    private lateinit var adapter: notesadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = dbhelper(this)
        adapter = notesadapter(db.getallnotes(),this)

        binding.noterecycler.layoutManager = LinearLayoutManager(this)
        binding.noterecycler.adapter = adapter

        binding.addbtn.setOnClickListener {
            val intent = Intent(this, addnoteActivity::class.java)
            startActivity(intent)
        }

        }

    override fun onResume() {
        super.onResume()
        adapter.refreshdata(db.getallnotes())
    }
    }
