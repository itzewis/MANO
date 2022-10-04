package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Cadastro : AppCompatActivity() {

    private lateinit var editNomeC: EditText
    private lateinit var editEmailC: EditText
    private lateinit var editSenhaC: EditText
    private lateinit var editConfirmarsenha: EditText
    private lateinit var btnCriarConta: Button
    private lateinit var text_login: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var btnFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editNomeC = findViewById(R.id.editNomeC)
        editEmailC = findViewById(R.id.editEmailC)
        editSenhaC = findViewById(R.id.editSenhaC)
        editConfirmarsenha = findViewById(R.id.editConfirmarSenha)
        btnCriarConta = findViewById(R.id.btnCriarConta)
        text_login = findViewById(R.id.text_login)
        btnFoto = findViewById<ImageView>(R.id.colocarFoto)

        btnCriarConta.setOnClickListener{
            val nome  = editNomeC.text.toString()
            val email = editEmailC.text.toString()
            val senha = editSenhaC.text.toString()

            criarConta(nome, email, senha)
        }

        btnFoto.setOnClickListener{

        }

    }
        private fun criarConta(nome: String, email: String, senha: String) {
            mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        addUsuarioToDatabase(nome, email, mAuth.currentUser?.uid!!)
                        val intent = Intent(this@Cadastro, inicial::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@Cadastro, "Erro ao fazer login", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

                    private fun addUsuarioToDatabase(nome: String, email: String, uid: String){
                    mDbRef = FirebaseDatabase.getInstance().getReference()
                    mDbRef.child("usuario").child(uid).setValue(Usuario(nome, email, uid))
                }

        }