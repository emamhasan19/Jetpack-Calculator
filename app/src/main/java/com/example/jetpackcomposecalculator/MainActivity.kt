package com.example.jetpackcomposecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposecalculator.ui.theme.JetpackComposeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            JetpackComposeCalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    //App bar can be added here

//                    topBar = {
//                        TopAppBar(
//                            title = {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(30.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text(
//                                        "Calculator", style = TextStyle(
//                                            fontWeight = FontWeight.SemiBold,
//                                            fontSize = 24.sp,
//                                            textAlign = TextAlign.Center
//                                        )
//                                    )
//                                }
//                            },
//                            colors = TopAppBarDefaults.topAppBarColors(
//                                containerColor = Color(0xFF24B6E8)
//                            )
//                        )
//                    }
                ) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = calculatorViewModel,

                        )
                }
            }
        }
    }
}