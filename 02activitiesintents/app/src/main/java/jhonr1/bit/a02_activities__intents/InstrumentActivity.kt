package jhonr1.bit.a02_activities__intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_instrument.*

class InstrumentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrument)

        val intent = intent
        val instrument = intent.getStringExtra("instrument_name")
        val month_value = intent.getStringExtra("month")
        txtViewInstrumentDetails.text = "You are enrolled for ${instrument} classes in ${month_value}"
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
