package com.example.chatapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.chatapplication.databinding.ActivityCadastroBinding
import com.example.chatapplication.databinding.ActivityInicialBinding
import com.example.chatapplication.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*


class Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var storage : FirebaseStorage
    private lateinit var bancoD: FirebaseDatabase
    private lateinit var caixaDialogo : AlertDialog.Builder
    private lateinit var selecionarFoto : Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storage = FirebaseStorage.getInstance()
        bancoD = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        caixaDialogo = AlertDialog.Builder(this)
            .setMessage("Carregando Imagem...")
            .setCancelable(false)

      binding.btnCamera.setOnClickListener{
          val intent = Intent()
          intent.action = Intent.ACTION_GET_CONTENT
          intent.type = "image/*"
          startActivityForResult(intent,1)
      }

         binding.btnCriarConta.setOnClickListener {
             if(binding.editSenhaC.text.toString().isEmpty() || binding.editRua.text.toString().isEmpty() ||
                     binding.editEmailC.text.toString().isEmpty() || binding.editNomeC.text.toString().isEmpty()){
                 Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()

                 if(binding.editSenhaC.text.toString().length < 6){
                     binding.editSenhaC.setError("Minimo 6 caracteres")
                 }
                 else if (selecionarFoto == null){
                     Toast.makeText(this, "Selececione uma imagem", Toast.LENGTH_SHORT).show()
                 }
                 else{
                     criarConta()
                 }
             }
        }

    }

    private fun criarConta(){
        val reference = storage.reference.child("Foto").child(Date().time.toString())
        reference.putFile(selecionarFoto).addOnCompleteListener{
            if(it.isSuccessful){
                reference.downloadUrl.addOnCompleteListener { task ->
                    uploadInfo(task.toString())
                }      
            }
        }
    }

    private fun uploadInfo(imgUrl: String) {
        val usuario = Usuario(auth.uid.toString(), binding.editNomeC.text.toString(), binding.editEmailC.text.toString(), binding.editRua.text.toString(),
            imgUrl,binding.editSenhaC.text.toString())

            bancoD.reference.child("users")
                .child(auth.uid.toString())
                .setValue(usuario)
                .addOnSuccessListener {
                    Toast.makeText(this, "Conta Criada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, inicial::class.java))
                    finish()
                }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null ){
            if (data.data != null){
                selecionarFoto = data.data!!

                binding.btnCamera.setImageURI(selecionarFoto)

            }
        }

    }

}