package com.ags.proyectofinal.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ags.proyectofinal.R
import com.ags.proyectofinal.application.ProyectoFinalApp
import com.ags.proyectofinal.data.remote.ProductoRepository
import com.ags.proyectofinal.data.remote.model.DetalleProductoDto
import com.ags.proyectofinal.data.remote.model.ProductoDto
import com.ags.proyectofinal.databinding.FragmentDetalleProductoBinding
import com.ags.proyectofinal.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val PRODUCTO_ID = "producto_id"
class DetalleProductoFragment : Fragment() {

    private var _binding: FragmentDetalleProductoBinding ?= null
    private val binding get() = _binding!!

    private var productoId: String?= null

    private lateinit var repository: ProductoRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleProductoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            productoId = it.getString(PRODUCTO_ID)

            Log.d(Constants.LOGTAG, "Id recibido: $productoId")
            repository = (requireActivity().application as ProyectoFinalApp).productoRepository

            lifecycleScope.launch {
                productoId?.let { id ->
                    val call: Call<DetalleProductoDto> = repository.getDetalleProductoApiary(id)
                    call.enqueue(object : Callback<DetalleProductoDto> {
                        override fun onResponse(
                            call: Call<DetalleProductoDto>,
                            response: Response<DetalleProductoDto>
                        ) {
                            binding.apply {
                                pbLoading.visibility = View.GONE

                                tvName.text = response.body()?.name

                                Picasso.get()
                                    .load(response.body()?.imageURL)
                                    .error(R.drawable.ic_image)
                                    .into(ivProduct)

                                tvDescription.text = response.body()?.description
                                var category = ""
                                when(response.body()?.categoryId){
                                    1.toLong() -> category = getString(R.string.categoria1)
                                    2.toLong() -> category = getString(R.string.categoria2)
                                    3.toLong() -> category = getString(R.string.categoria3)
                                    4.toLong() -> category = getString(R.string.categoria4)
                                    5.toLong() -> category = getString(R.string.categoria5)
                                }
                                tvType.text = category

                                var textoPresentation = ""
                                for (i in response.body()?.presentations!!.indices){
                                    textoPresentation += getString(R.string.presentacionesConPrecio,
                                        response.body()?.presentations?.get(i)?.desc,
                                        response.body()?.presentations?.get(i)?.price.toString())

                                    if (i < (response.body()?.presentations?.size!!) - 1)
                                        textoPresentation += System.getProperty("line.separator")
                                }

                                tvPresentations.text = textoPresentation


                                val mc = MediaController(requireContext())
                                mc.setAnchorView(binding.vvVideo)
                                vvVideo.setVideoURI(Uri.parse(response.body()?.videoURL))
                                vvVideo.setMediaController(mc)
                                vvVideo.setOnPreparedListener{mediaPlayer ->
                                    mediaPlayer.start()
                                }
                                vvVideo.setOnCompletionListener {mediaPlayer ->
                                    mediaPlayer.start()
                                }



                            }

                        }

                        override fun onFailure(call: Call<DetalleProductoDto>, t: Throwable) {
                            binding.pbLoading.visibility = View.GONE
                            Toast.makeText(requireActivity(), getString(R.string.errorConexion), Toast.LENGTH_LONG).show()
                        }
                    })
                }
            }
        }
    }
    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
    companion object {

        @JvmStatic
        fun newInstance(productoId: String ) = DetalleProductoFragment().apply {
            arguments = Bundle().apply {
                putString(PRODUCTO_ID, productoId)
            }
        }
    }
}