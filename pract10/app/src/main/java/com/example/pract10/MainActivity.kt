package com.example.pract10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import java.math.BigInteger

//25. Реализуйте программу вычисления 3
//fn со всеми десятичными знаками,
//где n ∈ [1 . . . 45], где fn – числа Фибоначчи, f1 = f2 = 1.
class Calculations {

    private var job: Job? = null

    private fun calculations(editTextInputNumber: Int):BigInteger {
        val result = generateSequence(Triple(1, 1, 2)) {
            Triple(it.first + it.second, 0 + it.second, it.third + 1)
        }.map { Pair(it.first.toDouble() + it.second.toDouble(), it.third) }
            .takeWhile { it.second < 6 }.map { it.first }
        var first:BigInteger = 1.toBigInteger()
        var second = 0.toBigInteger()
        var fib = 0.toBigInteger()
        var count = 0.toBigInteger()
        while (count < editTextInputNumber.toBigInteger()) {
            fib = first + second
            second = first
            first = fib
            count++
        }
        val three:BigInteger  = 3.toBigInteger()//= setOf(3)
        var result3: BigInteger = 3.toBigInteger()
            //result.last()
//        val kek = generateSequence(3){ it * 3}.takeWhile { it < 40 }.toString()
//
       // val result2 = generateSequence(Pair(3, 0)) { Pair(it.first*3, it.second+1) }.
          //      takeWhile { it.second < 6 }.map { it.first*3 }.toString()
//
//        val result2 = three.map { it*3 }.takeWhile {  }.toString().toBigInteger()
        count = 0.toBigInteger()
        while(count < fib && !(job?.isCancelled!!)) {
            result3 *= three
            count++
        }
        return result3
    }

//        return generateSequence(Pair(1, 1)) {
//            Pair(
//                -it.first,
//                it.second + 2
//            )
//        }.map { 1 / (it.second.toDouble() * it.first.toDouble()) }
//            .takeWhile { (abs(it) > 0.00000001) && !(job?.isCancelled!!) }.sum() * 4


    fun startCalculations(editTextInputNumber: Int) {
        job = GlobalScope.launch {
                val pi = calculations(editTextInputNumber)
                listenerAnswer?.receiveAnswer(pi)
        }
    }

    fun stopCalculations(){
        job?.cancel()
    }

    private var listenerAnswer: ListenerAnswer? = null

    fun register(listenerAnswer: ListenerAnswer){
        this.listenerAnswer = listenerAnswer
    }

    companion object {
        private var calculations: Calculations? = null
        fun getInstance():Calculations{
            if (calculations == null)
                calculations = Calculations()
            return calculations!!
        }
    }
}
interface ListenerAnswer{
    fun receiveAnswer(answer: BigInteger)
}
class MainActivity : AppCompatActivity(),ListenerAnswer {
    private lateinit var textViewCalculation : TextView

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (textViewCalculation.text != "")
            outState.putString(RESULT,textViewCalculation.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Calculations.getInstance().register(this)
        setContentView(R.layout.activity_main)
        val buttonCalculations = findViewById<Button>(R.id.buttonCoroutineCalculations)
        val buttonCancel = findViewById<Button>(R.id.buttonCancel)
        textViewCalculation = findViewById<TextView>(R.id.textViewResultCalculations)
        if (savedInstanceState != null){
            textViewCalculation.text = savedInstanceState.getString("RESULT").toString()
        }

        val editTextInputNumber = findViewById<EditText>(R.id.editTextInputNumber)
        var job: Job? = null
        buttonCalculations.setOnClickListener {
            if (editTextInputNumber.text.toString().toIntOrNull() in 1..45)
                Calculations.getInstance().startCalculations(editTextInputNumber.
                text.toString().toInt())
            else textViewCalculation.text = getString(R.string.enterNumberError)
        }
        buttonCancel.setOnClickListener {
            Calculations.getInstance().stopCalculations()
        }

    }

    override fun receiveAnswer(answer: BigInteger) {
        GlobalScope.launch(Dispatchers.Main) {
            textViewCalculation.text = answer.toString()
        }
    }

    companion object{
        const val RESULT = "RESULT"
    }
}

//            flow
//            GlobalScope.launch(Dispatchers.Main) {
//                generate().filter { it%2 == 0  }.collectLatest{
//                   value -> Toast.makeText(this@MainActivity, value.toString(), Toast.LENGTH_SHORT).show()
//                }
//
//            }
//            GlobalScope.launch { //переменная которая хранит настройки для запутка карутинов
//
//                val channel = Channel<Int>(6)
//
//                launch {
////                    listOf(2, 3, 10).forEach { channel.send(it) }
//                    channel.cancel()
//                }
////                val job = launch
////                job.join()//ожидания окончания выполнения
//                launch(Dispatchers.Main) {
//
//                    for(n in channel) {
//                        textViewCalculation.append(n.toString())
////                        Toast.makeText(this@MainActivity, n.toString(), Toast.LENGTH_SHORT).show()
//                        delay(1000)
//                    }
//                }
//            }
//        }