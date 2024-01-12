package com.theartemis.fusion.ui.fragments.addpost

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.theartemis.fusion.R
import com.theartemis.fusion.data.DataRepository
import com.theartemis.fusion.data.remote.RemoteDataSource
import com.theartemis.fusion.databinding.FragmentAddPostBinding
import com.theartemis.fusion.ui.signin.SignInViewModel
import com.theartemis.fusion.utility.ViewModelFactory

@Suppress("DEPRECATION")
class AddPostFragment : BottomSheetDialogFragment() {

    private var selectedImageUri: Uri? = null
    private lateinit var viewModel: AddPostViewModel
    private lateinit var binding: FragmentAddPostBinding
    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            selectedImageUri = data?.data
            binding.imgPlaceholder.setImageURI(selectedImageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddPostBinding.inflate(inflater, container , false)

        return binding.root
    }

    private fun imagesChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        pickImage.launch(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dataRepository = DataRepository(RemoteDataSource())
        val viewModelFactory = ViewModelFactory(dataRepository)


        viewModel = ViewModelProvider(this,viewModelFactory)[AddPostViewModel::class.java]
        binding.apply {
            lyProgress.visibility = View.INVISIBLE
            val adapterSDG = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sdgs,
                R.layout.item_sdg
            )

            adapterSDG.setDropDownViewResource(R.layout.item_sdg)
            spSdgs.adapter = adapterSDG

            btnSelectImages.setOnClickListener {
                imagesChooser()
            }
            btnPost.setOnClickListener {
                lyProgress.visibility = View.VISIBLE
                val title = edtTitle.text.toString()
                val description = edtDesc.text.toString()
                val sdg = spSdgs.selectedItem.toString()
                viewModel.addPost(sdg,title,description,selectedImageUri!!).observe(viewLifecycleOwner){
                    if (it.id.isNotEmpty()) {
                        dismiss()
                        lyProgress.visibility = View.INVISIBLE
                    } else {
                        Toast.makeText(context, "Gagal Upload Postingan",Toast.LENGTH_SHORT).show()
                        dismiss()
                        lyProgress.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

}