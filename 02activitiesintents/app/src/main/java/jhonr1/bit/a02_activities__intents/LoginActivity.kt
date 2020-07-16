package jhonr1.bit.a02_activities__intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // get value from Main_activity
        val intent = intent
        val email = intent.getStringExtra("email")
        val pass = intent.getStringExtra("password")

        txtViewLoggedInDetails.text = "Email: ${email} \nPassword: ${pass} "
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}
