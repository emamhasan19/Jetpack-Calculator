package com.example.jetpackcomposecalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ".", "=",
)

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val equationText = viewModel.equationText.observeAsState()
    val resutlText = viewModel.resultText.observeAsState()

    Box(modifier = modifier) {
        Column(
            modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.End
        ) {
            Text(
                equationText.value ?: "",
                style = TextStyle(
                    fontSize = 40.sp,
                    textAlign = TextAlign.End,
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 16.dp)

            )
            Spacer(modifier = Modifier.weight(1f))


            Text(
                resutlText.value ?: "",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 16.dp)
            )

//            Spacer(modifier = Modifier.height(20.dp))




            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
            ) {
                items(buttonList.size) {
                    CalculatorButton(buttonList[it], onClick = {

                        viewModel.onButtonClick(buttonList[it])
                    }
                    )
                }
            }
        }
    }
}


@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            contentColor = Color.White,
            containerColor = getColor(btn),
        ) {
            Text(
                text = btn, style = TextStyle(
                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            )

        }
    }
}

@Composable
fun getColor(btn: String): Color {

    if (btn == "C" || btn == "AC") {
        return Color(0xFFF44336)
    }
    if (btn == "=" || btn == "+" || btn == "-" || btn == "*" || btn == "/") {
        return Color(0xFFFF7F1E)
    }
    if (btn == "(" || btn == ")") {
        return Color.Gray
    }
    return Color(0xFF24B6E8)

}