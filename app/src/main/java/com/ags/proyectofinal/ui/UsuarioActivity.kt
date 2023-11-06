package com.ags.proyectofinal.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ags.proyectofinal.R
import com.ags.proyectofinal.databinding.ActivityUsuarioBinding

class UsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsuarioBinding

    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.deepstonelullaby_d2_bl)
        mediaPlayer.start()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fgContainerView, ConfiguracionFragment())
                .commit()
        }

        binding.bottomNavigationView.setOnItemReselectedListener{menu ->
            when(menu.itemId){
                R.id.menuHome ->{
                    val intent = Intent(this, CatalogoActivity::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }
                R.id.menuPedido -> {
                    val intent = Intent(this, PedidoActivity::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }

            }
        }

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

}