package com.ags.proyectofinal.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ags.proyectofinal.R
import com.ags.proyectofinal.databinding.ActivityCatalogoBinding


class CatalogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatalogoBinding

    private lateinit var mediaPlayer: MediaPlayer

    private var playing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCatalogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.discipline_d2_lf)
        mediaPlayer.start()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fgContainerView, ListaProductosFragment())
                .commit()
        }

        binding.bottomNavigationView.setOnItemReselectedListener { menu ->
            when (menu.itemId) {
                R.id.menuPedido -> {
                    val intent = Intent(this, PedidoActivity::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }

                R.id.menuUser -> {
                    val intent = Intent(this, UsuarioActivity::class.java).apply {
                    }
                    startActivity(intent)
                    finish()
                }

            }
        }

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.start()
        }


        /*val bundle = intent.getBundleExtra("EXTRA_BUNDLE")
        val type = intent.getStringExtra("KEY_INFO")

        if (type == "Login"){
            binding.tvInicio.text = getString(R.string.bienvenida,"de nuevo","estos son los datos con los que ingresaste:")
            binding.tvEmail.text = getString(R.string.datos,"Correo",bundle?.getString("EXTRA_EMAIL"))
            binding.tvPassword.text = getString(R.string.datos,"Contraseña",bundle?.getString("EXTRA_PASSWORD"))
            binding.tvName.visibility = View.GONE
            binding.tvLastName.visibility = View.GONE
            binding.tvSex.visibility= View.GONE
        }
        if (type == "Registro"){
            binding.tvInicio.text = getString(R.string.bienvenida,"a nuestra aplicación","te has registrado satisfactoriamente con los siguientes datos:")
            binding.tvName.text = getString(R.string.datos,"Nombre",bundle?.getString("EXTRA_NAME"))
            binding.tvLastName.text = getString(R.string.datos,"Apellido",bundle?.getString("EXTRA_LASTNAME"))
            binding.tvSex.text = getString(R.string.datos,"Sexo",bundle?.getString("EXTRA_SEX"))
            binding.tvEmail.text = getString(R.string.datos,"Correo",bundle?.getString("EXTRA_EMAIL"))
            binding.tvPassword.text = getString(R.string.datos,"Contraseña",bundle?.getString("EXTRA_PASSWORD"))
        }
        if(type == "Invitado"){
            binding.tvInicio.text = getString(R.string.bienvenida,"usuario invitado","recuerda que para poder hacer compras deberás iniciar sesión")
            binding.tvName.visibility = View.GONE
            binding.tvLastName.visibility = View.GONE
            binding.tvSex.visibility = View.GONE
            binding.tvEmail.visibility = View.GONE
            binding.tvPassword.visibility = View.GONE
        }*/


        /*binding.btMisPedidos.setOnClickListener {
            val intent = Intent(this, PedidoActivity::class.java)
            startActivity(intent)

        }*/

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