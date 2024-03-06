package com.example.myrecyclerview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myrecyclerview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.titleTextView
        val imgView = binding.imageView
        val descriptionView = binding.descriptionTextView
        val shareButton = binding.actionShare

        val cat =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra<Cat>("key_cat", Cat::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra<Cat>("key_cat")
            }

        if (cat != null) {
            textView.text = cat.name

            Glide.with(this)
                .load(cat.photo)
                .centerCrop()
                .into(imgView)

            descriptionView.text = cat.description

            shareButton.setOnClickListener {
                // Create a custom link
                val customLink = cat.link

                // Create the sharing Intent with the custom link
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, customLink)

                // Optionally, specify a title for the chooser dialog
                val title = "Share via"

                // Create and start the chooser Intent
                val chooserIntent = Intent.createChooser(shareIntent, title)
                startActivity(chooserIntent)
            }
        }
    }
}