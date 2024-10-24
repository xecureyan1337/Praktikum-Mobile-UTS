//Copyright (C) 2024 Alfiyan
//All rights reserved.
//
//This file is part of Praktikum-Mobile-UTS.

package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Copyright (C) 2024 Alfiyan
//All rights reserved.
//
//This file is part of Praktikum-Mobile-UTS.

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var foodName: String
    private lateinit var foodDescription: String
    private var foodImageResourceId: Int = 0
//    BARU
    private lateinit var servings: String
    private lateinit var orderingName: String
    private lateinit var additionalNotes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        // Retrieve food data from intent
        foodName = intent.getStringExtra("FOOD_NAME") ?: "Unknown Food"
        foodDescription = intent.getStringExtra("FOOD_DESCRIPTION") ?: "No Description"
        foodImageResourceId = intent.getIntExtra("FOOD_IMAGE", 0)
//        BARU
        servings = intent.getStringExtra("SERVINGS") ?: "0"
        orderingName = intent.getStringExtra("ORDERING_NAME") ?: "Unknown"
        additionalNotes = intent.getStringExtra("ADDITIONAL_NOTES") ?: "None"

        // Set up views to display food data + BARU
        findViewById<TextView>(R.id.tvFoodName).text = foodName
        findViewById<TextView>(R.id.tvFoodDescription).text = foodDescription
        findViewById<TextView>(R.id.tvServings).text = "Number of Servings: $servings"
        findViewById<TextView>(R.id.tvOrderingName).text = "Ordering Name: $orderingName"
        findViewById<TextView>(R.id.tvNotes).text = "Additional Notes: $additionalNotes"

        // Set up the Back to Menu button click listener
        findViewById<Button>(R.id.backtoMenu).setOnClickListener {
            // Finish this activity and return to the previous one
            val intent = Intent(this, ListFoodActivity::class.java)
            startActivity(intent)
            finish() // This will take you back to the previous activity in the stack
        }
    }
}