package pt.ua.dialer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class SaveDial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_dial)

        //get Info from Mainactivity

        val dialNumber = intent.getCharExtra("dialNumber",' ')

        // get reference to button
        val btn1 = findViewById(R.id.btn1) as Button
        val btn2 = findViewById(R.id.btn2) as Button
        val btn3 = findViewById(R.id.btn3) as Button
        val btn4 = findViewById(R.id.btn4) as Button
        val btn5 = findViewById(R.id.btn5) as Button
        val btn6 = findViewById(R.id.btn6) as Button
        val btn7 = findViewById(R.id.btn7) as Button
        val btn8 = findViewById(R.id.btn8) as Button
        val btn9 = findViewById(R.id.btn9) as Button
        val btn0 = findViewById(R.id.btn0) as Button
        val btnClear = findViewById(R.id.btnClear) as Button
        val btnSave = findViewById(R.id.btnSave) as Button


        val textView: TextView = findViewById(R.id.phoneText) as TextView

        var i : Int = 0
        var max:Int  = 30;
        var phoneNumber : CharArray = CharArray(max)


        phoneNumber.set(i,' ')

        fun digitPress(num: Char) {
            Log.d(null, "Digited $num pressed")
            if(i < max){
                i+=1
                phoneNumber.set(i,num)
                textView.setText(String(phoneNumber))
            }
        }
        fun clearPress() {
            Log.d(null, "button clear clicked")

            if (i in 1 until max){
                phoneNumber.set(i, ' ')
                i -= 1
                textView.setText(String(phoneNumber))
            }
        }
        fun saveDial(){
            val intent = Intent(this, SaveDial::class.java)
            startActivity(intent);
        }

        btn1.setOnClickListener{
            digitPress('1')
        }
        btn2.setOnClickListener{
            digitPress('2')
        }
        btn3.setOnClickListener{
            digitPress('3')
        }
        btn4.setOnClickListener{
            digitPress('4')
        }
        btn5.setOnClickListener{
            digitPress('5')
        }
        btn6.setOnClickListener{
            digitPress('6')
        }
        btn7.setOnClickListener{
            digitPress('7')
        }
        btn8.setOnClickListener{
            digitPress('8')
        }
        btn9.setOnClickListener{
            digitPress('9')
        }
        btn0.setOnClickListener{
            digitPress('0')
        }
        btnClear.setOnClickListener{
            clearPress()

        }


        btnSave.setOnClickListener{
            Log.d(null, "button save pressed")

            val intent = Intent(this, MainActivity::class.java) // from who to who
            intent.putExtra("phoneNumber", phoneNumber)
            intent.putExtra("indexDial", dialNumber )
            intent.putExtra("size", i )

            startActivity(intent);
        }

    }
}