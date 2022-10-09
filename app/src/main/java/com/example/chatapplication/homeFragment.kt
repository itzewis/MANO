package com.example.chatapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class homeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}



// this shit over here is not working

    /*private fun pedirPermissao(permission: String,name:String, requestCode:Int){
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(requireActivity(),permission) == PackageManager.PERMISSION_GRANTED ->{
                    Toast.makeText(requireActivity(), "$name permission granted", Toast.LENGTH_SHORT).show()
                }
                shouldShowRequestPermissionRationale(permission) -> showDialog(permission,name,requestCode)

                else -> ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),requestCode)
            }
        }
    }

    private fun onRequestPermissionResult(requestCode: Int,permission: Array<out String>,grantResult: IntArray){
        fun innerCheck(name: String){
            if (grantResult.isEmpty() || grantResult[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireActivity(), "$name permisão negada", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireActivity(), "$name permissão aceita", Toast.LENGTH_SHORT).show()
            }
        }
        when(requestCode){
            FINE_LOCATION_RQ -> innerCheck("location")
        }
    }

    private fun showDialog(permission: String,name: String,requestCode: Int){
        val builder = AlertDialog.Builder(requireActivity())

        builder.apply {
            setMessage("Permissão para acessar sua $name é necessaria para usar o aplicativo")
            setTitle("Permissão necessaria")
            setPositiveButton("OK"){ dialog, witch ->
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission),requestCode)
            }
        }
    }*/
