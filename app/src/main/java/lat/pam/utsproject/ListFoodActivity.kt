package lat.pam.utsproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Preparing food data
        foodList = listOf(
            Food("Batagor", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("Cheese Cake", "Roti nyoynyoy lumer di mulutt", R.drawable.cheesecake),
            Food("Cireng", "Cireng meleleh asli ngeunah...", R.drawable.cireng),
            Food("Donut", "Donut bulat mantap bisa ditojos tengahna", R.drawable.donut),
            Food("Kepi Uxcaaahhh", "Lamun ngoding teu bari NGOPI, lain programmer", R.drawable.kopi_hitam),
            Food("Mie Goreng", "Lemas panjang panjang tapi ngeunah disruput...", R.drawable.mie_goreng),
            Food("Nasi Goreng", "Nasi lamun digoreng jadina nasi goreng, lamun maneh teu boga duit eta mah NASIBBB", R.drawable.nasigoreng),
            Food("Sparkling Tea", "cobaan weh sparkling tea kumaha rasana... testimoni om", R.drawable.sparkling_tea)
        )

        // Set up the adapter with a click listener
//        @COPYRIGHT ALFIYAN2024
        adapter = FoodAdapter(foodList) { selectedFood ->
            // Start OrderActivity and pass the selected food details
            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtra("FOOD_NAME", selectedFood.name)
                putExtra("FOOD_DESCRIPTION", selectedFood.description)
                putExtra("FOOD_IMAGE", selectedFood.imageResourceId) // Optional: Pass image resource ID
            }
            startActivity(intent)
        }

//        @COPYRIGHT ALFIYAN2024

        recyclerView.adapter = adapter

        fun openWhatsApp() {
            val phoneNumber = "+62815383343576"
            val message = "Hai gw mau order makan"

            val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://wa.me/$phoneNumber?text=$message")
            }

            if (sendIntent.resolveActivity(/* pm = */ packageManager) != null) {
                startActivity(sendIntent)
            } else {
                AlertDialog.Builder(this)
                    .setTitle("WhatsApp belum terinstall bro")
                    .setMessage("Install WA dulu")
                    .setPositiveButton("Yes") { _, _ ->
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whatsapp")))
                        } catch (anfe: ActivityNotFoundException) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")))
                        }
                    }
                    .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        val contactImageView: ImageView = findViewById(R.id.contact)
        contactImageView.setOnClickListener {
            openWhatsApp()
        }

//        @COPYRIGHT ALFIYAN2024
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}