package jhonr1.bit.a02_activities__intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val months = resources.getStringArray(R.array.month)
        populateSpinner(spinnerMonth, months)
        btnLogin.setOnClickListener(LoginButtonOnClickHandler())
        btnEnrol.setOnClickListener(EnrolButtonOnClickHandler())
        btnGoogleSearch.setOnClickListener(SearchButtonOnClickHandler())

    }

    private fun populateSpinner(spinner: Spinner, array: Array<String>){
        val layoutID: Int = android.R.layout.simple_spinner_item
        spinner.adapter = ArrayAdapter(this@MainActivity, layoutID, array)

    }

    inner class EnrolButtonOnClickHandler : View.OnClickListener {
        override fun onClick(v: View?) {
            val spinnerTxt = spinnerMonth.selectedItem
            // Get the checked radio button id from radio group
            var id: Int = radioGroup.checkedRadioButtonId
            if (id != -1) {
                var radioButtonValue:RadioButton = findViewById(id)

                val intent = Intent(this@MainActivity, InstrumentActivity::class.java)
                intent.putExtra("instrument_name", radioButtonValue.text)
                intent.putExtra("month", spinnerTxt.toString())
                startActivity(intent)
            }
        }

    }

    inner class SearchButtonOnClickHandler : View.OnClickListener {
        override fun onClick(v: View?) {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("www.google.com")
//            intent.type = "text/plain"
//            startActivity(Intent.createChooser(intent,"Choose application"))
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com") ))
        }

    }

    inner class LoginButtonOnClickHandler : View.OnClickListener {
        override fun onClick(v: View?) {
            if (editTxtEmail.text.isBlank()){
                Toast.makeText( this@MainActivity, "Email field is blank",Toast.LENGTH_LONG).show()
            }
            else if(editTxtPassword.text.isBlank()){
                Toast.makeText(this@MainActivity,"Password field is blank", Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.putExtra("email", editTxtEmail.text.toString())
                intent.putExtra("password", editTxtPassword.text.toString())
                startActivity(intent)
            }
        }

    }
}

