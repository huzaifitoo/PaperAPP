package com.example.paperapp.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.paperapp.R
import com.example.paperapp.Utilis.PdfDownload
import com.example.paperapp.databinding.ActivityContentBinding


class ContentActivity : AppCompatActivity() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var binding: ActivityContentBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_content)

        val topicPdf=intent.getStringExtra("topicPdf")

        val topicName=intent.getStringExtra("topicName")

        binding.optionsTitle.text=topicName

        PdfDownload().execute(topicPdf)

    }
}