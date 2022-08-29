package com.optic.deliverykotlinudemy.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.optic.deliverykotlinudemy.R
import com.optic.deliverykotlinudemy.activities.models.ResponseHttp
import com.optic.deliverykotlinudemy.activities.models.User
import com.optic.deliverykotlinudemy.activities.providers.UsersProviders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    val TAG ="RegisterActivity"

    var imageViewGoToLogin : ImageView? = null
    var ediTextName :EditText? =null
    var ediTextLastName :EditText? =null
    var ediTextEmail :EditText? =null
    var ediTextPhone :EditText? =null
    var ediTextPassword :EditText? =null
    var ediTextConfirmPassword :EditText? =null
    var buttonRegister:Button? =null

    var userProviders = UsersProviders()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        imageViewGoToLogin = findViewById(R.id.imageview_go_to_login)
        ediTextName = findViewById(R.id.edittext_name)
        ediTextLastName = findViewById(R.id.edittext_lastname)
        ediTextEmail = findViewById(R.id.edittext_email)
        ediTextPhone = findViewById(R.id.edittext_phone)
        ediTextPassword = findViewById(R.id.edittext_password)
        ediTextConfirmPassword = findViewById(R.id.edittext_confirm_password)
        buttonRegister = findViewById(R.id.btn_register)

        imageViewGoToLogin?.setOnClickListener { goToLogin() }
        buttonRegister?.setOnClickListener { register() }
    }
    fun String.isEmailValid():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
    private fun isValidForm
                (name:String,
                 lastname:String,
                 email:String,
                 phone:String,
                 password:String,
                 confirmPassword:String):Boolean{
        if(name.isBlank()){
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false;
        }
        if(lastname.isBlank()){
            Toast.makeText(this, "Debes ingresar el apellido", Toast.LENGTH_SHORT).show()
            return false;
        }
        if(email.isBlank()){
            Toast.makeText(this, "Debes ingresar el correo", Toast.LENGTH_SHORT).show()
            return false;
        }
        if(phone.isBlank()){
            Toast.makeText(this, "Debes ingresar el numero telefonico", Toast.LENGTH_SHORT).show()
            return false;
        }
        if(password.isBlank()){
            Toast.makeText(this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show()
            return  false;
        }
        if(confirmPassword.isBlank()){
            Toast.makeText(this, "Debes ingresar la confirmacion de contraseña", Toast.LENGTH_SHORT).show()
            return  false;
        }
        if(!email.isEmailValid()){
            Toast.makeText(this, "el email no es valido", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password !=confirmPassword){
            Toast.makeText(this, "la contraseña no coinciden", Toast.LENGTH_SHORT).show()
            return  false;
        }   
        return true
    }
    private fun register() {
        val name = ediTextName?.text.toString()
        val lastname = ediTextLastName?.text.toString()
        val email = ediTextEmail?.text.toString()
        val phone = ediTextPhone?.text.toString()
        val password = ediTextPassword?.text.toString()
        val confirmPassword = ediTextConfirmPassword?.text.toString()

        val user = User(
            name = name,
            lastname = lastname,
            email = email,
            phone = phone,
            password = password,

            )
        if (isValidForm(name = name, lastname = lastname, email = email, phone = phone, password = password, confirmPassword = confirmPassword))


            userProviders.register(user)?.enqueue(object : Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    Toast.makeText( this@RegisterActivity, response.body()?.message,Toast.LENGTH_SHORT).show()
                    Log.d(TAG ,  "Response:${response}")
                    Log.d(TAG ,  "Body:${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d(TAG,"se produjo un error ${t.message}")
                    Toast.makeText(this@RegisterActivity,"se produjo un error ${t.message}",Toast.LENGTH_LONG).show()
                }

            })

    }



    private fun goToLogin(){
        var i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}