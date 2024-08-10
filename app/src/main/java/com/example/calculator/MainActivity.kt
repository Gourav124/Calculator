package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import org.mariuszgromada.math.mxparser.Expression


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
               CalculatorScreen()
            }
        }
    }
}
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier){
    var expression by remember {
        mutableStateOf("")
    }
    var result by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)){

        Column (modifier = modifier
            .weight(1f)
            .padding(16.dp)){
            Text(modifier = Modifier.fillMaxWidth(),
                text = expression,
                style = TextStyle(fontSize = 40.sp, color = MaterialTheme.colorScheme.onPrimaryContainer, textAlign = TextAlign.End),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(modifier = Modifier.fillMaxWidth(),
                text = result,
                style = TextStyle(fontSize = 48.sp, color = MaterialTheme.colorScheme.onPrimaryContainer, textAlign = TextAlign.End),
            )

        }
        Column{
            Row (
                modifier = modifier.fillMaxWidth()
            ){
                CalcultorButton(isFunction = true,text = "AC", modifier = modifier.weight(2f),
                    onClick = {
                        expression = ""
                        result  = ""
                    }
                )
                CalcultorButton(isFunction = true,text = "Del", modifier = modifier.weight(1f),
                    onClick = {
                        expression = delCharacter(expression)
                    }
                )
                CalcultorButton(isFunction = true,text = "/", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
            }
            Row (
                modifier = modifier.fillMaxWidth()
            ){
                CalcultorButton(isFunction = true,text = "7", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it

                    }
                )
                CalcultorButton(isFunction = true,text = "8", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "9", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "x", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
            }
            Row (
                modifier = modifier.fillMaxWidth()
            ){
                CalcultorButton(isFunction = true,text = "4", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "5", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "6", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "+", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
            }
            Row (
                modifier = modifier.fillMaxWidth()
            ){
                CalcultorButton(isFunction = true,text = "1", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "2", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "3", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "-", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
            }
            Row (
                modifier = modifier.fillMaxWidth()
            ){
                CalcultorButton(isFunction = true,text = "0", modifier = modifier.weight(2f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = ".", modifier = modifier.weight(1f),
                    onClick = {
                        expression += it
                    }
                )
                CalcultorButton(isFunction = true,text = "=", modifier = modifier.weight(1f),
                    onClick = {
                        if (expression.isEmpty())
                            return@CalcultorButton
                       result = solveExpression(expression)
                    }
                )
            }

        }
    }

}
@SuppressLint("SuspiciousIndentation")
fun solveExpression(expression: String): String {
  var answer = ""
    try {
        answer = Expression(expression
            .replace("x","*")
        ).calculate().toString()

    }catch (e:Exception){
        e.printStackTrace()
        return "Invalid Expression"
    }
    return answer.replace(".0","")
}

fun delCharacter(expression: String): String {
    return if (expression.isNotEmpty()) {
        expression.substring(0, expression.length - 1)
    }else{
        expression
    }
}


@Composable
fun CalcultorButton(modifier: Modifier = Modifier, text: String = "",isFunction : Boolean = false
                    ,onClick : (String) -> Unit = {}
){
    Button(modifier = modifier
        .size(72.dp)
        .clip(CircleShape)
        .padding(4.dp),onClick = {onClick(text)}, colors = ButtonDefaults.buttonColors(
            containerColor = if (isFunction && text == "=" || text == "AC"|| text == "Del"|| text == "/"|| text == "x"|| text == "+"|| text == "-"){
                MaterialTheme.colorScheme.secondary
            }else if (isFunction){
                MaterialTheme.colorScheme.primary
            } else{
                MaterialTheme.colorScheme.tertiary
            }
        )) {
        Text(text = text, style = TextStyle(fontSize = 24.sp,
            color = if(isFunction && text == "=" || text == "AC"){
                MaterialTheme.colorScheme.background
            }else{
                MaterialTheme.colorScheme.onTertiary
            })
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CalculatorButtonPre(){
    CalcultorButton()
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPre(){
    CalculatorScreen()
}