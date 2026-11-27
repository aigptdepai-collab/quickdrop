package com.quickdrop.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Night = Color(0xFF0B0A12)
private val Panel = Color(0xFF171522)
private val Purple = Color(0xFF9B72FF)
private val Pink = Color(0xFFD58BFF)
private val Soft = Color(0xFFAAA5BA)
private val White = Color(0xFFF8F6FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { QuickDropApp() }
    }
}

@Composable
private fun QuickDropApp() {
    var selected by remember { mutableStateOf(0) }
    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Night,
            surface = Panel,
            primary = Purple,
            onBackground = White,
            onSurface = White
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = Night) {
            Column(modifier = Modifier.fillMaxSize()) {
                when (selected) {
                    0 -> HomeScreen(
                        onSend = { selected = 1 },
                        onReceive = { selected = 2 }
                    )
                    1 -> EmptyFeatureScreen(
                        icon = Icons.Rounded.NorthEast,
                        title = "ارسال فایل",
                        subtitle = "در مرحله بعد، انتخاب فایل و پیدا کردن دستگاه‌های نزدیک را اضافه می‌کنیم."
                    )
                    else -> EmptyFeatureScreen(
                        icon = Icons.Rounded.SouthWest,
                        title = "دریافت فایل",
                        subtitle = "در مرحله بعد، دریافت امن فایل از دستگاه‌های اطراف فعال می‌شود."
                    )
                }
                Spacer(Modifier.weight(1f))
                NavigationBar(
                    containerColor = Color(0xFF12111B),
                    contentColor = White
                ) {
                    NavigationBarItem(
                        selected = selected == 0,
                        onClick = { selected = 0 },
                        icon = { Icon(Icons.Rounded.Home, null) },
                        label = { Text("خانه") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Purple,
                            selectedTextColor = Purple,
                            indicatorColor = Color(0xFF302448),
                            unselectedIconColor = Soft,
                            unselectedTextColor = Soft
                        )
                    )
                    NavigationBarItem(
                        selected = selected == 1,
                        onClick = { selected = 1 },
                        icon = { Icon(Icons.Rounded.NorthEast, null) },
                        label = { Text("ارسال") }
                    )
                    NavigationBarItem(
                        selected = selected == 2,
                        onClick = { selected = 2 },
                        icon = { Icon(Icons.Rounded.SouthWest, null) },
                        label = { Text("دریافت") }
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreen(onSend: () -> Unit, onReceive: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(44.dp).background(
                    Brush.linearGradient(listOf(Purple, Pink)),
                    RoundedCornerShape(15.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.FlashOn, null, tint = Color.White, modifier = Modifier.size(27.dp))
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("QUICKDROP", color = White, fontSize = 20.sp, fontWeight = FontWeight.Black, letterSpacing = 1.4.sp)
                Text("SHARE WITHOUT LIMITS", color = Soft, fontSize = 9.sp, letterSpacing = 2.sp)
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier.size(40.dp).background(Panel, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Rounded.Settings, null, tint = Soft)
            }
        }

        Spacer(Modifier.height(38.dp))
        Text("انتقال فایل،", color = White, fontSize = 31.sp, fontWeight = FontWeight.Bold)
        Text("ساده و مستقیم.", color = Purple, fontSize = 31.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text(
            "بدون اینترنت، بدون سرور واسطه؛ فقط دستگاه‌های خودت.",
            color = Soft, fontSize = 13.sp, lineHeight = 22.sp
        )

        Spacer(Modifier.height(28.dp))
        Box(
            modifier = Modifier.fillMaxWidth().height(170.dp).background(
                Brush.linearGradient(listOf(Color(0xFF39265F), Color(0xFF211A35), Color(0xFF171522))),
                RoundedCornerShape(26.dp)
            ).padding(20.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(8.dp).background(Color(0xFF8EE6B0), CircleShape))
                    Spacer(Modifier.width(8.dp))
                    Text("آماده برای اتصال", color = Color(0xFFB8F2CE), fontSize = 11.sp)
                }
                Spacer(Modifier.height(14.dp))
                Text("دستگاهت را
به دنیای اطرافت وصل کن", color = White, fontSize = 18.sp, fontWeight = FontWeight.Bold, lineHeight = 27.sp)
            }
            Icon(
                Icons.Rounded.Devices,
                null,
                tint = Color(0xFFB99AFF),
                modifier = Modifier.align(Alignment.CenterEnd).size(78.dp)
            )
        }

        Spacer(Modifier.height(24.dp))
        Text("شروع انتقال", color = White, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(13.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ActionCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.NorthEast,
                title = "ارسال فایل",
                detail = "فرستادن به اطراف",
                onClick = onSend
            )
            ActionCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Rounded.SouthWest,
                title = "دریافت فایل",
                detail = "از دستگاه‌های دیگر",
                onClick = onReceive
            )
        }

        Spacer(Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Shield, null, tint = Purple, modifier = Modifier.size(19.dp))
            Spacer(Modifier.width(8.dp))
            Text("انتقال مستقیم و خصوصی در شبکه محلی", color = Soft, fontSize = 11.sp)
        }
    }
}

@Composable
private fun ActionCard(
    modifier: Modifier,
    icon: ImageVector,
    title: String,
    detail: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.height(142.dp)
            .background(Panel, RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            Modifier.size(42.dp).background(Color(0xFF302448), RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = Pink)
        }
        Column {
            Text(title, color = White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(detail, color = Soft, fontSize = 10.sp)
        }
    }
}

@Composable
private fun EmptyFeatureScreen(icon: ImageVector, title: String, subtitle: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(110.dp))
        Box(
            Modifier.size(92.dp).background(Color(0xFF302448), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = Pink, modifier = Modifier.size(42.dp))
        }
        Spacer(Modifier.height(24.dp))
        Text(title, color = White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text(subtitle, color = Soft, fontSize = 13.sp, lineHeight = 23.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        Text("به‌زودی", color = Purple, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}
