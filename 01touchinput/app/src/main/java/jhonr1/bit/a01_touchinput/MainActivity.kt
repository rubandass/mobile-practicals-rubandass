package jhonr1.bit.a01_touchinput

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
                txtViewEnrolledDetails.text = "You are enrolled for ${radioButtonValue.text} classes in ${spinnerTxt.toString()}"
            }else{
                txtViewEnrolledDetails.text = "Please select an instrument"
            }
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
                txtViewLoginDetails.text = "${editTxtEmail.text} ${editTxtPassword.text}"
            }
        }

    }
}
