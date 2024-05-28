package com.example.todo_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo_app.databinding.ActivityAddnoteBinding
import com.example.todo_app.databinding.ActivityMainBinding

class addnoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddnoteBinding
    private lateinit var db: dbhelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = dbhelper(this)

        binding.savebtn.setOnClickListener {
            val title = binding.addtitle.text.toString()
            val content = binding.content.text.toString()
            val note = note(0,title,content)
            db.insertnote(note)
            finish()
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
        }

    }
}