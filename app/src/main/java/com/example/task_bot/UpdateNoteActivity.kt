package com.example.task_bot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.task_bot.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db : NoteDataBaseHelper
    private var noteId :Int  = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }


        val note = db.getNoteByID(noteId)
        binding.editnotetitle.setText(note.title)
        binding.editdescription.setText(note.content)

        binding.updatebtn.setOnClickListener{
            val newtitle = binding.editnotetitle.text.toString()
            val newcontent = binding.editdescription.text.toString()
            val updateNote = Note(noteId,newtitle,newcontent)

            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Change Saved",Toast.LENGTH_SHORT).show()

        }
    }
}