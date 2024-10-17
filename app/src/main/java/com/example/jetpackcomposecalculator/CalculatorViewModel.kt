package com.example.jetpackcomposecalculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    fun onButtonClick(btn: String) {
        Log.i("Clicked button:", btn)

        _equationText.value?.let {
            if (btn == "AC") {
                _equationText.value = ""
                _resultText.value = "0"
                return

            }
            if (btn == "C") {

                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)

                }
                return

            }
            if (btn == "=") {
                _equationText.value = _resultText.value
                return
            }
            _equationText.value = it + btn

            try {
                _resultText.value = calculateResult(_equationText.value.toString())

            } catch (e: Exception) {
                println(e.toString())
            }
        }

    }

    private fun calculateResult(equation: String): String {
        val context: org.mozilla.javascript.Context =
            org.mozilla.javascript.ContextFactory().enterContext()
        context.optimizationLevel = -1
        val scriptable: org.mozilla.javascript.Scriptable = context.initStandardObjects()
        val finalResult =
            context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()
        if (finalResult.endsWith(".0")) {
            finalResult.replace(".0", "")
        }

        return finalResult
    }
}