package com.example.crudadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.updateButton.setOnClickListener {
            val referencePhone = binding.referencePhone.text.toString()
            val updateName = binding.updateName.text.toString()
            val updateLocation = binding.updateLocation.text.toString()
            val updateOperator = binding.updateOperator.text.toString()

            if (referencePhone.isEmpty()) {
                Toast.makeText(this, "please enter mobile number", Toast.LENGTH_SHORT).show()
            } else {
                updateData(referencePhone, updateName, updateLocation, updateOperator)

            }
        }

    }

    private fun updateData(phone: String, name: String, operator: String, location: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Phone Directory")
        val user =
            mapOf<String, String>("name" to name, "operator" to operator, "location" to location)
        databaseReference.child(phone).updateChildren(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateOperator.text.clear()
            binding.updateLocation.text.clear()

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
        }
    }
}