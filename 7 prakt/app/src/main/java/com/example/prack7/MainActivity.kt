//практ 7
//Программа разложения числа на простые множители.
//практ 8
//Разработайте автоматические тесты для результата практической работы
//а) выделите вычислительные функции в отдельныйкласс;
//б) разработайте UNIT-тесты для данного класса с использованиеJUnit;
//в) разработайте UI-тест для приложения с использованием Espresso.

//реализуйте возможность отправить результат вычисления
//в другое приложение по выбору пользователя.
package com.example.prack7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.prack7.MyIntentService.Companion.startActionDecomposition

//<summary>
//class разложения на простые множители
//в методе decomposition через параметры передается число
//далише вычисляется его простые множители
//</summary>
class DecompositionIntoPrimeMultiplier private constructor(){
    companion object {
        fun decomposition(resultInt: Int): String {
            var count = 2
            var resultMultiplication = 1
            var resultString = ""
            //Программа разложения числа на простые множители.
            while (resultMultiplication < resultInt) {
                if (count % 2 != 0 || count % 3 != 0)
                    if (resultInt % (resultMultiplication * count) == 0) {
                        resultString += " $count *"
                        resultMultiplication *= count
                        count = 1
                    }
                count++
            }
            return if (resultString == "") "" else resultString.drop(1).dropLast(2)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(RESULT,textViewResult.text.toString())
    }
    override fun onPause(){
        super.onPause()
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onStop() {
        super.onStop()
    }
    override fun onStart() {
        super.onStart()
    }
    private val receiver = object:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val res = intent?.getStringExtra(MyIntentService.RESULT)
            textViewResult.text = res.toString()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this).
            registerReceiver(receiver, IntentFilter(MyIntentService.ACTION_RESULT))

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        textViewResult = findViewById<TextView>(R.id.textViewResult)
        val imageView = findViewById<ImageView>(R.id.imageView)

            val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSendText = findViewById<Button>(R.id.buttonSendText)
        val buttonSendImage = findViewById<Button>(R.id.buttonSendImage)
        val buttonPhoto = findViewById<Button>(R.id.buttonPhoto)

        if (savedInstanceState != null){
            textViewResult.text = savedInstanceState.getString(RESULT)
        }
        buttonAdd.setOnClickListener{
            val resultText = editTextNumber.text
            val resultInt = resultText.toString().toIntOrNull()
            if (resultInt != null){
                startActionDecomposition(this, resultInt.toString())
               // val resultString = DecompositionIntoPrimeMultiplier.decomposition(resultInt)
                //textViewResult.text = resultString
            }
            else {
                Toast.makeText(this,getString(R.string.Enter_a_number),Toast.LENGTH_SHORT).show()
            }
        }
        buttonSendText.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, textViewResult.text.toString())
            intent.type = "text/plain"
            //Окно выбор
            val intentCreateChooser = Intent.createChooser(intent, null)
            startActivity(intentCreateChooser)
        }

//        buttonSendImage.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.type = "image/jpeg"
//            //val bitmap = data?.extras?.get("data") as Bitmap
//            intent.putExtra(Intent.EXTRA_STREAM, imageView)
//            startActivity(Intent.createChooser(intent, "Share Image"))
//            //startActivity(intentCreateChooser)
//        }
        buttonPhoto.setOnClickListener {
            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            val intentCreateChooser = Intent.createChooser(intent, null)
            startActivityForResult(intentCreateChooser, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == REQUEST_IMAGE_CAPTURE) && (resultCode == RESULT_OK)){
            val bitmap = data?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(bitmap)
        }
    }

    companion object{
        const val RESULT = "RESULT"
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}