package com.example.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("com.example.test", Context.MODE_PRIVATE)
        val etFileName: EditText = findViewById(R.id.etFileName)
        val etFileContents: EditText = findViewById(R.id.etFileContents)
        val saveButton: Button = findViewById(R.id.saveButton)
        val resetButton: Button = findViewById(R.id.resetButton)

        saveButton.setOnClickListener {
            if (sharedPreferences.getInt("IS_SAVED", 0) == 0) {
                val file = File(this.getExternalFilesDir(null), etFileName.text.toString())
                val fileContents = etFileContents.text.toString()
                val writer = FileWriter(file)
                writer.apply {
                    append(fileContents)
                    flush()
                    close()
                }
                sharedPreferences.edit().putInt("IS_SAVED", 1).apply()
                Toast.makeText(this, "File created in ${file.path}", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "File already exists", Toast.LENGTH_LONG).show()
            }
        }

        resetButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            Toast.makeText(this, "Preferences cleared", Toast.LENGTH_LONG).show()
        }

    }
}