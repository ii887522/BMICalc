package my.edu.tarc.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link UI to code
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)

        buttonCalculate.setOnClickListener {
            if (editTextWeight.text.isEmpty()) {
                editTextWeight.error = getString(R.string.value_required)
                return@setOnClickListener
            }

            if (editTextHeight.text.isEmpty()) {
                editTextHeight.error = getString(R.string.value_required)
                return@setOnClickListener
            }

            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()

            if (height == 0.0f) {
                editTextHeight.error = getString(R.string.must_not_be_zero)
                return@setOnClickListener
            }

            var bmi = weight / (height / 100).pow(2)

            if (bmi < 18.5) {
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%s: %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = getString(R.string.under)
            } else if (bmi in 18.5..25.0) {
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s: %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = getString(R.string.normal)
            } else {
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s: %.2f", getString(R.string.bmi), bmi)
                textViewStatus.text = getString(R.string.over)
            }
        }

        buttonReset.setOnClickListener {
            textViewBMI.text = getString(R.string.bmi)
            textViewStatus.text = getString(R.string.status)
            imageViewBMI.setImageResource(R.drawable.empty)
            editTextWeight.text.clear()
            editTextHeight.text.clear()
        }
    }
}