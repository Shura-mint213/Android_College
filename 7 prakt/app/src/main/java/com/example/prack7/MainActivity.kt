//практ 7
//Программа разложения числа на простые множители.
//практ 8
//Разработайте автоматические тесты для результата практической работы
//а) выделите вычислительные функции в отдельныйкласс;
//б) разработайте UNIT-тесты для данного класса с использованиеJUnit;
//в) разработайте UI-тест для приложения с использованием Espresso.
package com.example.prack7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

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
                // if (count == resultInt) resultString = getString(R.string.Simple_cannot_be_decomposed)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        textViewResult = findViewById<TextView>(R.id.textViewResult)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        if (savedInstanceState != null){
            textViewResult.text = savedInstanceState.getString(RESULT)
        }
        buttonAdd.setOnClickListener{
            val resultText = editTextNumber.text
            val resultInt = resultText.toString().toIntOrNull()
            if (resultInt != null){
                val resultString = DecompositionIntoPrimeMultiplier.decomposition(resultInt)
                textViewResult.text = resultString

            }
            else {
                Toast.makeText(this,getString(R.string.Enter_a_number),Toast.LENGTH_SHORT).show()
            }
        }


    }
    companion object{
        const val RESULT = "RESULT"
    }
}