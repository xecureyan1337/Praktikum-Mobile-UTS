//Copyright (C) 2024 Alfiyan
//All rights reserved.
//
//This file is part of Praktikum-Mobile-UTS.

package lat.pam.utsproject

//@COPYRIGHT ALFIYAN2024
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//@COPYRIGHT ALFIYAN2024
class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvErrorMessage: TextView

    // dummy creds @COPYRIGHT ALFIYAN2024
    private val correctUsername = "alfiyan"
    private val correctPassword = "aldy1234#"

//    @COPYRIGHT ALFIYAN2024
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvErrorMessage = findViewById(R.id.tvErrorMessage)

        btnLogin.setOnClickListener {
            // Get the input values
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Validate credentials
            if (username == correctUsername && password == correctPassword) {
                // Clear any previous error messages
                tvErrorMessage.visibility = TextView.GONE

                // Start ListFoodActivity when the credentials are correct
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
            } else {
                // Show error message if credentials are incorrect
                tvErrorMessage.text = "Invalid username or password"
                tvErrorMessage.visibility = TextView.VISIBLE
//                @COPYRIGHT ALFIYAN2024
            }
        }

//    @COPYRIGHT ALFIYAN2024
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
//@COPYRIGHT ALFIYAN2024