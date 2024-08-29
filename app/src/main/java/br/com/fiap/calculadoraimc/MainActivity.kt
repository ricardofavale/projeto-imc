package br.com.fiap.calculadoraimc

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IMCScreen()
                }
            }
        }
    }
}

@Composable
fun IMCScreen() {

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    var statusImc by remember {
        mutableStateOf("")
    }

    fun limparCampos() {
        peso = ""
        altura = ""
        imc = 0.0
        statusImc = ""
    }

    fun obterCorCard(statusImc: String): Color {
        return when (statusImc) {
            "Peso Ideal" -> Color(0xff329F6B) // Verde
            "Levemente acima do peso" -> Color(0xffFFD700) // Amarelo
            "Abaixo do peso", "Obesidade Grau I", "Obesidade Grau II", "Obesidade Grau III" -> Color(0xffFF6347) // Vermelho
            else -> Color.Gray // Cor padrão caso não se enquadre em nenhuma categoria
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //-----Header-----------
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(colorResource(id = R.color.vermelho_fiap))
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "Logo BMI",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Calculadora IMC",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            //-----Formulário -----------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .offset(y = (-30).dp)
                        .fillMaxWidth(),
                    //.height(300.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    //border = BorderStroke(width = 1.dp, Color.Magenta),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )
                    ) {
                        Text(
                            text = "Seus Dados",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.vermelho_fiap),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = "Seu Peso",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.vermelho_fiap)
                        )
                        OutlinedTextField(
                            value = peso,
                            onValueChange = {
                                peso = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Seu peso em Kg.")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.cor_do_texto)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Sua Altura",
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.vermelho_fiap)
                        )
                        OutlinedTextField(
                            value = altura,
                            onValueChange = {
                                altura = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Sua altura em cm.")
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = colorResource(id = R.color.vermelho_fiap),
                                focusedBorderColor = colorResource(id = R.color.cor_do_texto)
                            ),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                imc = calcularImc(
                                    pesoUsuario = peso.toDouble(),
                                    alturaUsuario = altura.toDouble()
                                )
                                statusImc = obterStatusImc(imcUsuario = imc)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.vermelho_fiap))
                        ) {
                            Text(
                                text = "CALCULAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                limparCampos()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                        ) {
                            Text(
                                text = "LIMPAR",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = obterCorCard(statusImc)),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize()
            ) {
                Column() {
                    Text(
                        text = "Resultado",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = statusImc,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = String.format("%.1f", imc),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}



