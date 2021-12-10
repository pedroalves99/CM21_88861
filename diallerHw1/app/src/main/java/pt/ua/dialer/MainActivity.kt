package pt.ua.dialer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.util.Log
import android.view.View
import android.widget.TextView

private var sDial1 : String = "Long press to save a number"
private var sDial2 : String = "Long press to save a number"
private var sDial3 : String = "Long press to save a number"

class MainActivity : AppCompatActivity() {

    val max:Int  = 30;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i : Int = 0

        var phoneNumber : CharArray = CharArray(max)



        val indexDial = intent.getCharExtra("indexDial", ' ')

        Log.d(null, "Index dial: $indexDial")
        val numberSpeed = intent.getCharArrayExtra("phoneNumber")
        val size = intent.getIntExtra("size", 0)

        Log.d(null, "Number speed: $numberSpeed")

        phoneNumber.set(i,' ')

        if (indexDial == '1'){
            if (numberSpeed != null) {
                sDial1 = String(numberSpeed)
                phoneNumber = numberSpeed
                i = size
                Log.d(null, "Size: $i")
            }
        }
        else if(indexDial == '2'){
            if (numberSpeed != null) {
                sDial2 = String(numberSpeed)
                phoneNumber = numberSpeed
                i = size
            }

        }
        else if(indexDial == '3'){
            if (numberSpeed != null) {
                sDial3 = String(numberSpeed)
                phoneNumber = numberSpeed
                i = size
            }

        }


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
        val btnCall = findViewById(R.id.btnCall) as Button
        val speed1 = findViewById(R.id.btnDial1) as Button
        val speed2 = findViewById(R.id.btnDial2) as Button
        val speed3 = findViewById(R.id.btnDial3) as Button
        val textView: TextView = findViewById(R.id.phoneText) as TextView



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

            if (i in 1 until 30){
                phoneNumber.set(i, ' ')
                i -= 1
                textView.setText(String(phoneNumber))
            }
        }
        fun saveDial(number: Char){
           val intent = Intent(this, SaveDial::class.java)
            intent.putExtra("dialNumber", number)
            startActivity(intent);
        }
        fun call() {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse( "tel:" + String(phoneNumber))
            startActivity(dialIntent)
        }

        speed1.setOnLongClickListener{
            saveDial('1')
            true
        }
        speed2.setOnLongClickListener{
            saveDial('2')
            true
        }
        speed3.setOnLongClickListener{
            saveDial('3')
            true
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

        speed1.setOnClickListener{
            if (sDial1 != "Long press to save a number"){
                phoneNumber = sDial1.toCharArray()

            }
            else{
                i = 0
                phoneNumber = CharArray(max)
            }

            textView.setText(sDial1)
        }
        speed2.setOnClickListener{
            if (sDial2 != "Long press to save a number"){
                phoneNumber = sDial2.toCharArray()

            }
            else{
                i = 0
            }

            textView.setText(sDial2)
        }
        speed3.setOnClickListener{
            if (sDial3 != "Long press to save a number"){
                phoneNumber = sDial3.toCharArray()
                i = phoneNumber.lastIndex

            }
            else{
                i = 0
                phoneNumber = CharArray(max)
            }

            textView.setText(sDial3)
        }


        btnCall.setOnClickListener{
            Log.d(null, "button Call clicked")
            call()
        }

    }

}