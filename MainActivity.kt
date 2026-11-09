package br.com.miraazul

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF071426)
private val Panel = Color(0xFF102943)
private val Blue = Color(0xFF20A7FF)
private val Cyan = Color(0xFF65D8FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MiraAzulApp() }
    }
}

@Composable
fun MiraAzulApp() {
    var geral by remember { mutableFloatStateOf(90f) }
    var ponto by remember { mutableFloatStateOf(85f) }
    var quatroX by remember { mutableFloatStateOf(75f) }
    var awm by remember { mutableFloatStateOf(40f) }
    var fps by remember { mutableStateOf("Alto / máximo disponível") }
    var saved by remember { mutableStateOf(false) }
    val context = LocalContext.current

    MaterialTheme(colorScheme = darkColorScheme(
        primary = Blue, secondary = Cyan, background = Navy, surface = Panel
    )) {
        Surface(Modifier.fillMaxSize(), color = Navy) {
            Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.SportsEsports, null, tint = Cyan, modifier = Modifier.size(34.dp))
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text("MIRA AZUL", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Black)
                        Text("PAINEL DE OTIMIZAÇÃO • SAMSUNG", color = Cyan, fontSize = 10.sp, letterSpacing = 1.3.sp)
                    }
                }
                Card(colors = CardDefaults.cardColors(containerColor = Panel)) {
                    Column(Modifier.padding(16.dp)) {
                        Text("PERFIL DE MIRA", color = Cyan, fontWeight = FontWeight.Bold)
                        Text("Sugestões iniciais para personalizar dentro do Free Fire", color = Color.LightGray, fontSize = 12.sp)
                        Spacer(Modifier.height(12.dp))
                        SensSlider("Geral", geral) { geral = it; saved = false }
                        SensSlider("Ponto vermelho", ponto) { ponto = it; saved = false }
                        SensSlider("Mira 2x / 4x", quatroX) { quatroX = it; saved = false }
                        SensSlider("AWM / sniper", awm) { awm = it; saved = false }
                        Button(onClick = { saved = true }, modifier = Modifier.fillMaxWidth()) {
                            Text("SALVAR PERFIL NESTE APP")
                        }
                        if (saved) Text("Perfil salvo nesta sessão. Transfira os valores manualmente para o jogo.", color = Cyan, fontSize = 12.sp)
                    }
                }
                Card(colors = CardDefaults.cardColors(containerColor = Panel)) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("OTIMIZAÇÃO SEGURA", color = Cyan, fontWeight = FontWeight.Bold)
                        Text("• Feche apps que não estiver usando antes de jogar.\n• Ative o modo de desempenho/jogo disponível no seu Samsung.\n• Evite jogar enquanto o aparelho carrega se estiver aquecendo.\n• Use uma conexão estável e reduza gráficos se houver quedas de FPS.", color = Color.White, fontSize = 13.sp)
                        OutlinedButton(
                            onClick = { context.startActivity(Intent(Settings.ACTION_SETTINGS)) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Settings, null)
                            Spacer(Modifier.width(8.dp))
                            Text("ABRIR CONFIGURAÇÕES DO ANDROID")
                        }
                        OutlinedButton(
                            onClick = { context.startActivity(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.BatteryChargingFull, null)
                            Spacer(Modifier.width(8.dp))
                            Text("CONFIGURAÇÕES DE BATERIA")
                        }
                    }
                }
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF17334B))) {
                    Column(Modifier.padding(14.dp)) {
                        Text("IMPORTANTE", color = Cyan, fontWeight = FontWeight.Bold)
                        Text("Este app não toca na memória do Free Fire, não injeta código, não usa macros, não altera arquivos do jogo e não promete imunidade a banimento. Ele apenas organiza sugestões e abre configurações normais do Android.", color = Color.White, fontSize = 12.sp)
                    }
                }
                Text("MIRA AZUL • versão 1.0", color = Color.Gray, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun SensSlider(label: String, value: Float, onChange: (Float) -> Unit) {
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label, color = Color.White, fontWeight = FontWeight.Medium)
            Text(value.toInt().toString(), color = Cyan, fontWeight = FontWeight.Bold)
        }
        Slider(value = value, onValueChange = onChange, valueRange = 0f..100f)
    }
}
