package com.example.todo_app

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class notesadapter(private var notes:List<note>,context: Context): RecyclerView.Adapter<notesadapter.notesviewholder>(){
    private val db:dbhelper = dbhelper(context)

    class notesviewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titletext: TextView = itemView.findViewById(R.id.titletext)
        val contenttext: TextView = itemView.findViewById(R.id.contenttext)
        val updatebutton: ImageView = itemView.findViewById(R.id.updatebtn)
        val deletebutton: ImageView = itemView.findViewById(R.id.deletebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return notesviewholder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: notesviewholder, position: Int) {
        val note = notes[position]
        holder.titletext.text = note.title
        holder.contenttext.text = note.content

        holder.updatebutton.setOnClickListener{
            val intent = Intent(holder.itemView.context,updateActivity::class.java).apply{
                putExtra("id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletebutton.setOnClickListener{
            db.deletenote(note.id)
            refreshdata(db.getallnotes())
            Toast.makeText(holder.itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }

    }

    fun refreshdata(newNotes: List<note>){
        notes = newNotes
        notifyDataSetChanged()
    }
}