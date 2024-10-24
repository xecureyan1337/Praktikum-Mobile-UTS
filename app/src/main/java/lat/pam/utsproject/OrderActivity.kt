package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat import androidx.core.view.WindowInsetsCompat

class OrderActivity : AppCompatActivity() {
    private lateinit var foodName: String
    private lateinit var foodDescription: String
    private var foodImageResourceId: Int = 0

    // Add variables for additional inputs + BARU TAMBAHAN
    private lateinit var servings: String
    private lateinit var orderingName: String
    private lateinit var additionalNotes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_order)

        // Retrieve food data from intent
        foodName = intent.getStringExtra("FOOD_NAME") ?: "Unknown Food"
        foodDescription = intent.getStringExtra("FOOD_DESCRIPTION") ?: "No Description"
        foodImageResourceId = intent.getIntExtra("FOOD_IMAGE", 0)

        // Set up views to display food data
        // Set up views to display food data
        findViewById<TextView>(R.id.etFoodName).text = foodName // Correctly set the food name
        findViewById<EditText>(R.id.etFoodDescription).setText(foodDescription) // Use setText() for EditText

        // Set up button click listener + BARU TAMBAHAN
        findViewById<Button>(R.id.btnOrder).setOnClickListener {
            // Retrieve additional data from EditText fields
            servings = findViewById<EditText>(R.id.etServings).text.toString()
            orderingName = findViewById<EditText>(R.id.etName).text.toString()
            additionalNotes = findViewById<EditText>(R.id.etNotes).text.toString()

            // Navigate to ConfirmationActivity
            val confirmationIntent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("FOOD_NAME", foodName)
                putExtra("FOOD_DESCRIPTION", foodDescription)
                putExtra("FOOD_IMAGE", foodImageResourceId)
                putExtra("SERVINGS", servings) // Pass the number of servings
                putExtra("ORDERING_NAME", orderingName) // Pass the ordering name
                putExtra("ADDITIONAL_NOTES", additionalNotes) // Pass the additional notes
            }
            startActivity(confirmationIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}