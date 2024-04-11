package ru.btpit.nmedia

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SoundEffectConstants
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageButton
import org.jetbrains.annotations.Nullable
import ru.btpit.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "КД-Саунд - мастерская звукоаппаратуры",
            content = "Купили дом, завезли красивую мебель, приобрели навороченную плазму - а звука нет? Тогда вам стоит посетить КД-Саунд в Борисоглебске! Мы делаем Hi-Fi оборудование на заказ на территории Борисоглебска, возможна доставка по области.",
            published = "25 марта в 13:01",
            likes = 999999,
            share = 999,
            likedByMe = false
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = post.likes.toString()
            shareCount.text = post.share.toString()
            if (post.likedByMe) {
                like?.setImageResource(ru.btpit.nmedia.R.drawable.ic_liked_24)
            }
            share?.setOnClickListener {
                post.share++
                shareCount.text = post.share.toString()
                when {
                    post.share<1000 ->shareCount.text =post.share.toString()
                    post.share in 1000..999999 ->shareCount.text ="${post.share/1000}K"
                    else->shareCount.text =String.format("%.1fM",post.share.toDouble()/1000000)
                }

            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) ru.btpit.nmedia.R.drawable.ic_liked_24
                    else ru.btpit.nmedia.R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 ->likeCount.text ="${post.likes/1000}K"
                    post.likes<1000->likeCount.text =post.likes.toString()
                    else->likeCount.text =String.format("%.1fM",post.likes.toDouble()/1000000)
                }
            }
        }
    }

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var share: Int,
        var likedByMe: Boolean = false
    )
}





