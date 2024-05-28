package com.example.todo_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.databinding.ActivityUpdateBinding

class updateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: dbhelper
    private var id:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = dbhelper(this)

        id = intent.getIntExtra("id", -1)
        if(id == -1) {
            finish()
            return
        }

        val note = db.getnotebyid(id)
            binding.updateaddtitle.setText(note!!.title)
            binding.updatecontent.setText(note.content)


        binding.updatesavebtn.setOnClickListener {
            val newtitle = binding.updateaddtitle.text.toString()
            val newcontent = binding.updatecontent.text.toString()
            val updatednote = note(id, newtitle, newcontent)

            db.updatenote(updatednote)
            finish()
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
        }



    }
}