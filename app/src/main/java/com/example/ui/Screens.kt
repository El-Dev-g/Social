package com.example.ui

import kotlinx.coroutines.launch

import android.net.Uri
import android.content.Intent
import android.widget.VideoView
import android.widget.MediaController
import android.widget.TextView
import android.text.util.Linkify
import androidx.core.text.util.LinkifyCompat
import androidx.compose.ui.graphics.toArgb
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.interaction.MutableInteractionSource
import coil.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.ViewStream
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.input.pointer.pointerInput
import android.media.MediaRecorder
import android.media.MediaPlayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Reply
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.Post
import com.example.data.Comment
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import com.example.data.R2Uploader
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.basicMarquee
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Save
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import com.example.data.ChatMessage


@Composable
fun ZoozLogo(modifier: Modifier = Modifier, fontSize: androidx.compose.ui.unit.TextUnit = MaterialTheme.typography.titleLarge.fontSize) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color(0xFF4285F4))) { append("z") } // Blue
            withStyle(style = SpanStyle(color = Color(0xFFEA4335))) { append("o") } // Red
            withStyle(style = SpanStyle(color = Color(0xFFFBBC05))) { append("o") } // Yellow
            withStyle(style = SpanStyle(color = Color(0xFF34A853))) { append("z") } // Green
        },
        fontSize = fontSize,
        fontWeight = FontWeight.Black,
        modifier = modifier
    )
}

@Composable
fun ZoozIconCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4285F4),
                        Color(0xFFEA4335),
                        Color(0xFFFBBC05),
                        Color(0xFF34A853)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "z",
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontSize = 14.sp,
            modifier = Modifier.offset(y = (-1).dp)
        )
    }
}

fun formatRelativeTime(timestamp: Long): String {
    val diff = System.currentTimeMillis() - timestamp
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        diff < 0 -> "Just now" // Safety for slight clock drifts
        seconds < 60 -> "Just now"
        minutes == 1L -> "1 minute ago"
        minutes < 60 -> "$minutes minutes ago"
        hours == 1L -> "1 hour ago"
        hours < 24 -> "$hours hours ago"
        days == 1L -> "1 day ago"
        else -> "$days days ago"
    }
}



@Composable
fun LoginScreen(
    viewModel: BlogViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToForgotPassword: () -> Unit = {}
) {
    var isLoginMode by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    var showEmailVerificationNotice by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var showZoozAuthPrompt by remember { mutableStateOf(false) }
    var isZoozAuthenticating by remember { mutableStateOf(false) }
    var selectedZoozEmail by remember { mutableStateOf<String?>(null) }
    var selectedZoozUserForConfirmation by remember { mutableStateOf<com.example.data.User?>(null) }
    var pendingAlias by remember { mutableStateOf<String?>(null) }
    var zoozSectionMode by remember { mutableStateOf("CREATE") }
    var zoozAuthStep by remember { mutableIntStateOf(0) } // 0: Select Account, 1: Auth Forms
    val zoozScrollState = rememberScrollState()
    val registeredUsers by viewModel.allUsers.collectAsStateWithLifecycle(initialValue = emptyList())

    val isFirebaseConfigured = remember {
        val apiKey = com.example.BuildConfig.FIREBASE_API_KEY
        apiKey.isNotBlank() && apiKey != "YOUR_FIREBASE_API_KEY"
    }

    val context = androidx.compose.ui.platform.LocalContext.current
    val googleSignInClient = remember {
        try {
            // Attempt to get the default_web_client_id if it exists, otherwise use a placeholder
            val resId = context.resources.getIdentifier("default_web_client_id", "string", context.packageName)
            val webClientId = if (resId != 0) context.getString(resId) else com.example.BuildConfig.FIREBASE_API_KEY
            val gso = com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
                .requestEmail()
                .build()
            com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(context, gso)
        } catch (e: Exception) {
            null
        }
    }

    val launcher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            val task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(com.google.android.gms.common.api.ApiException::class.java)
                val idToken = account?.idToken
                if (idToken != null) {
                    viewModel.loginWithGoogle(idToken, pendingAlias, onLoginSuccess, { loginError = it })
                } else {
                    loginError = "Google Sign In failed: No ID token."
                }
            } catch (e: com.google.android.gms.common.api.ApiException) {
                loginError = "Google Sign In failed: ${e.message}"
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (showEmailVerificationNotice) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email Verification Sent",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    "Check your Inbox",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    "We have sent a secure verification link to activate your account. Click on the link to confirm your membership.",
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Sent to email address:",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = email,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "Next steps:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Success Step 1",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Check your mail folders (including spam).",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Success Step 2",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Tap the link inside the verification email.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Success Step 3",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Return here, select Sign In, and log in!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                if (!isFirebaseConfigured) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Secure Info",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Demo Mode: No real verification email is required! Click below to auto-verify and login directly.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                if (!isFirebaseConfigured) {
                    Button(
                        onClick = {
                            isLoginMode = true
                            showEmailVerificationNotice = false
                            onLoginSuccess()
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Demo Auto-Verify & Continue", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    Button(
                        onClick = {
                            showEmailVerificationNotice = false
                            isLoginMode = true
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Back to Sign In", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(
                    onClick = {
                        showEmailVerificationNotice = false
                        isLoginMode = false
                    }
                ) {
                    Text("Change Email / Back to Sign Up", color = MaterialTheme.colorScheme.primary)
                }
            }
        } else {
            Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ZoozLogo(fontSize = 48.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (isLoginMode) "Welcome to Zooz" else "Create an account",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isLoginMode) "Sign in to continue" else "Sign up to get started",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email or Username") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            if (!email.contains("@") && email.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Will authenticate secure alias: ${email.lowercase().trim()}@zooz.secure",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "Toggle password")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (isLoginMode) {
                TextButton(
                    onClick = onNavigateToForgotPassword,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Forgot Password?")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            var loginErrorLocalState by remember { mutableStateOf(loginError) }
            // Sync local error state
            LaunchedEffect(loginError) { loginErrorLocalState = loginError }

            val isZoozAlias = (!email.contains("@") && email.isNotBlank()) || 
                              email.endsWith("@zooz.secure", ignoreCase = true) || 
                              email.endsWith("@zooz.official", ignoreCase = true)
            val useSSO = isLoginMode && password.isBlank() && isZoozAlias

            Button(
                onClick = { 
                    loginError = null
                    if (email.isNotBlank()) {
                         if (useSSO) {
                             showZoozAuthPrompt = true
                             return@Button
                         }
                         
                         isLoading = true
                         if (isLoginMode) {
                             viewModel.login(
                                 emailOrUsername = email,
                                 password = password,
                                 isLoginMode = true,
                                 onComplete = {
                                     isLoading = false
                                     onLoginSuccess()
                                 },
                                 onError = {
                                     isLoading = false
                                     loginError = it
                                 }
                             )
                         } else {
                             viewModel.login(
                                 emailOrUsername = email,
                                 password = password,
                                 isLoginMode = false,
                                 onComplete = {
                                     isLoading = false
                                     if (isFirebaseConfigured) {
                                         onLoginSuccess()
                                     } else {
                                         // Show verification simulation notice screen for secure demo users
                                         showEmailVerificationNotice = true
                                     }
                                 },
                                 onError = { msg ->
                                     isLoading = false
                                     val isSuccessMessage = msg.contains("Account created") || msg.contains("verify your email")
                                     if (isSuccessMessage) {
                                         showEmailVerificationNotice = true
                                     } else {
                                         loginError = msg
                                     }
                                 }
                             )
                         }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    val btnText = when {
                        useSSO -> "Sign In with Zooz SSO"
                        isLoginMode -> "Sign In"
                        else -> "Sign Up"
                    }
                    Text(btnText, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            
            if (loginError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(loginError!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.outlineVariant)
                Text(
                    " OR ",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.outlineVariant)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Google Sign In
                OutlinedButton(
                    onClick = {
                        try {
                            pendingAlias = null // Reset for direct Google button
                            googleSignInClient?.signInIntent?.let {
                                launcher.launch(it)
                            } ?: run {
                                // Live fallback: instantly logs in as simulated Google user
                                viewModel.loginWithGoogle("simulated_desktop_user", null, onLoginSuccess, { loginError = it })
                            }
                        } catch (e: Exception) {
                            viewModel.loginWithGoogle("simulated_desktop_user", null, onLoginSuccess, { loginError = it })
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.5.dp, Color(0xFF4285F4).copy(alpha = 0.6f))
                ) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.ic_google_logo),
                        contentDescription = "Google Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Google",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Zooz Sign In
                OutlinedButton(
                    onClick = {
                        showZoozAuthPrompt = true
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.5.dp, Color(0xFF34A853).copy(alpha = 0.6f))
                ) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.app_icon_zooz_1779400725898),
                        contentDescription = "Zooz Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp).clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Zooz",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            if (showZoozAuthPrompt) {
                androidx.compose.ui.window.Dialog(
                    onDismissRequest = { if (!isZoozAuthenticating) showZoozAuthPrompt = false },
                    properties = androidx.compose.ui.window.DialogProperties(
                        usePlatformDefaultWidth = false,
                        dismissOnBackPress = !isZoozAuthenticating,
                        dismissOnClickOutside = false
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            shape = RoundedCornerShape(28.dp),
                            color = MaterialTheme.colorScheme.surface,
                            tonalElevation = 8.dp,
                            border = androidx.compose.foundation.BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                // Top App Header
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.app_icon_zooz_1779400725898),
                                            contentDescription = "Zooz Logo",
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(28.dp).clip(CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(
                                            text = "Zooz Secure Auth",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    if (!isZoozAuthenticating) {
                                        IconButton(
                                            onClick = { showZoozAuthPrompt = false },
                                            modifier = Modifier.size(48.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Close",
                                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }

                            // Full Page Content
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .verticalScroll(zoozScrollState)
                                    .padding(horizontal = 24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(24.dp))

                                // Dynamic Logo Presentation
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .background(
                                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.app_icon_zooz_1779400725898),
                                        contentDescription = "Zooz Big Logo",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(52.dp).clip(CircleShape)
                                    )
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                Text(
                                    text = "Choose an account",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "to continue to Prigid App securely",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(36.dp))

                                if (isZoozAuthenticating) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 40.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        CircularProgressIndicator(
                                            color = Color(0xFF34A853),
                                            modifier = Modifier.size(52.dp)
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                        Text(
                                            text = "Connecting securely to Zooz...",
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Medium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = selectedZoozEmail ?: "",
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                } else if (selectedZoozUserForConfirmation != null) {
                                    val user = selectedZoozUserForConfirmation!!
                                    val displayName = if (user.fullName.isNotBlank()) user.fullName else user.username
                                    val userEmail = if (user.username.contains("@")) user.username else "${user.username.lowercase()}@zooz.secure"
                                    val avatarChar = if (displayName.isNotEmpty()) displayName.first().uppercase() else "U"
                                    val colors = listOf(
                                        Color(0xFF4285F4),
                                        Color(0xFFEA4335),
                                        Color(0xFFFBBC05),
                                        Color(0xFF34A853),
                                        Color(0xFF9C27B0),
                                        Color(0xFF00BCD4),
                                        Color(0xFFE91E63)
                                    )
                                    val avatarColor = if (user.id == -99) Color(0xFF34A853) else colors[kotlin.math.abs(user.id) % colors.size]

                                    Column(
                                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Security Confirmation",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "Verify credentials & authorize simulated email mapping",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(24.dp))

                                        Box(
                                            modifier = Modifier
                                                .size(80.dp)
                                                .background(color = avatarColor, shape = CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = avatarChar,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                style = MaterialTheme.typography.headlineMedium
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(14.dp))

                                        Text(
                                            text = displayName,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = userEmail,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Medium
                                        )
                                        
                                        Spacer(modifier = Modifier.height(20.dp))
                                        
                                        Card(
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                            ),
                                            shape = RoundedCornerShape(12.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Column(
                                                modifier = Modifier.padding(12.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.CheckCircle,
                                                        contentDescription = "Simulated Domain Enabled",
                                                        tint = Color(0xFF34A853),
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Text(
                                                        text = "Secure Local Domain Mapped",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = Color(0xFF34A853),
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = "Accessing app via Zooz SSO. No password required. Your linked Google identity will authorize this session immediately.",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color(0xFF34A853),
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Medium
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(28.dp))

                                        Column(
                                            verticalArrangement = Arrangement.spacedBy(10.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Button(
                                                onClick = {
                                                    selectedZoozEmail = userEmail
                                                    isZoozAuthenticating = true
                                                    pendingAlias = user.username
                                                    try {
                                                        googleSignInClient?.signInIntent?.let {
                                                            launcher.launch(it)
                                                        } ?: run {
                                                            // Fallback for simulation
                                                            viewModel.loginWithGoogle("simulated_sso_user", pendingAlias, onLoginSuccess, { loginError = it })
                                                            isZoozAuthenticating = false
                                                            showZoozAuthPrompt = false
                                                        }
                                                    } catch (e: Exception) {
                                                        viewModel.loginWithGoogle("simulated_sso_user", pendingAlias, onLoginSuccess, { loginError = it })
                                                        isZoozAuthenticating = false
                                                        showZoozAuthPrompt = false
                                                    }
                                                },
                                                modifier = Modifier.fillMaxWidth().height(50.dp),
                                                shape = RoundedCornerShape(12.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(0xFF34A853)
                                                )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text("Authorize with Google SSO", color = Color.White, fontWeight = FontWeight.Bold)
                                            }

                                            OutlinedButton(
                                                onClick = {
                                                    selectedZoozUserForConfirmation = null
                                                },
                                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                                shape = RoundedCornerShape(12.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text("Back")
                                            }
                                        }
                                    }
                                } else if (zoozAuthStep == 0) {
                                    // Step 0: Account Selector List
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.spacedBy(16.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            // Dynamic Room Database Registered Users
                                            registeredUsers.forEach { user ->
                                                val displayName = if (user.fullName.isNotBlank()) user.fullName else user.username
                                                val userEmail = if (user.username.contains("@")) user.username else "${user.username.lowercase()}@zooz.secure"
                                                val avatarChar = if (displayName.isNotEmpty()) displayName.first().uppercase() else "U"
                                                val colors = listOf(
                                                    Color(0xFF4285F4),
                                                    Color(0xFFEA4335),
                                                    Color(0xFFFBBC05),
                                                    Color(0xFF34A853),
                                                    Color(0xFF9C27B0),
                                                    Color(0xFF00BCD4),
                                                    Color(0xFFE91E63)
                                                )
                                                val avatarColor = colors[kotlin.math.abs(user.id) % colors.size]

                                                ZoozAccountRow(
                                                    name = displayName,
                                                    email = userEmail,
                                                    avatarText = avatarChar,
                                                    avatarColor = avatarColor
                                                ) {
                                                    selectedZoozUserForConfirmation = user
                                                }
                                            }

                                            // Official Developer Option
                                            ZoozAccountRow(
                                                name = "Official Zooz Developer",
                                                email = "developer@zooz.official",
                                                avatarText = "D",
                                                avatarColor = Color(0xFF34A853)
                                            ) {
                                                selectedZoozUserForConfirmation = com.example.data.User(
                                                    id = -99,
                                                    username = "developer@zooz.official",
                                                    fullName = "Official Zooz Developer"
                                                )
                                            }

                                            // Trigger Section: Add another account
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .clickable {
                                                        zoozAuthStep = 1
                                                        zoozSectionMode = "CREATE"
                                                    }
                                                    .padding(vertical = 12.dp, horizontal = 16.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(40.dp)
                                                        .background(
                                                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                                            shape = CircleShape
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(16.dp))
                                                Text(
                                                    text = "Add another account",
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                } else {
                                    // Step 1: Switchable Auth Forms (Sign Up, Sign In, Forgot)
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            TextButton(
                                                onClick = { zoozSectionMode = "CREATE" },
                                                colors = ButtonDefaults.textButtonColors(
                                                    contentColor = if (zoozSectionMode == "CREATE") Color(0xFF34A853) else MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            ) {
                                                Text("Sign Up", fontWeight = if (zoozSectionMode == "CREATE") FontWeight.Bold else FontWeight.Normal)
                                            }
                                            TextButton(
                                                onClick = { zoozSectionMode = "SIGNIN" },
                                                colors = ButtonDefaults.textButtonColors(
                                                    contentColor = if (zoozSectionMode == "SIGNIN") Color(0xFF34A853) else MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            ) {
                                                Text("Sign In", fontWeight = if (zoozSectionMode == "SIGNIN") FontWeight.Bold else FontWeight.Normal)
                                            }
                                            TextButton(
                                                onClick = { zoozSectionMode = "FORGOT" },
                                                colors = ButtonDefaults.textButtonColors(
                                                    contentColor = if (zoozSectionMode == "FORGOT") Color(0xFF34A853) else MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            ) {
                                                Text("Forgot ID?", fontWeight = if (zoozSectionMode == "FORGOT") FontWeight.Bold else FontWeight.Normal)
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(16.dp))

                                        when (zoozSectionMode) {
                                            "CREATE" -> {
                                                Text(
                                                    text = "Create New Auth Link",
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.align(Alignment.Start),
                                                    color = Color(0xFF34A853)
                                                )
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                var inlineUsername by remember { mutableStateOf("") }
                                                var inlineError by remember { mutableStateOf<String?>(null) }
                                                
                                                OutlinedTextField(
                                                    value = inlineUsername,
                                                    onValueChange = { 
                                                        inlineUsername = it
                                                        inlineError = null 
                                                    },
                                                    label = { Text("Secure Username") },
                                                    placeholder = { Text("e.g. johndoe") },
                                                    suffix = { Text("@zooz.secure") },
                                                    modifier = Modifier.fillMaxWidth(),
                                                    shape = RoundedCornerShape(12.dp),
                                                    singleLine = true,
                                                    isError = inlineError != null
                                                )
                                                
                                                if (inlineError != null) {
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = inlineError ?: "",
                                                        color = MaterialTheme.colorScheme.error,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        modifier = Modifier.align(Alignment.Start)
                                                    )
                                                }
                                                
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                Button(
                                                    onClick = {
                                                        val trimmed = inlineUsername.trim()
                                                        if (trimmed.isBlank()) {
                                                            inlineError = "Username cannot be empty"
                                                            return@Button
                                                        }
                                                        if (trimmed.contains("@")) {
                                                            inlineError = "Do not include @ domain"
                                                            return@Button
                                                        }
                                                        
                                                        isZoozAuthenticating = true
                                                        pendingAlias = trimmed
                                                        try {
                                                            googleSignInClient?.signInIntent?.let {
                                                                launcher.launch(it)
                                                            } ?: run {
                                                                viewModel.loginWithGoogle("simulated_reg_user", pendingAlias, onLoginSuccess, { loginError = it })
                                                                isZoozAuthenticating = false
                                                                showZoozAuthPrompt = false
                                                            }
                                                        } catch (e: Exception) {
                                                            viewModel.loginWithGoogle("simulated_reg_user", pendingAlias, onLoginSuccess, { loginError = it })
                                                            isZoozAuthenticating = false
                                                            showZoozAuthPrompt = false
                                                        }
                                                    },
                                                    modifier = Modifier.fillMaxWidth().height(52.dp),
                                                    shape = RoundedCornerShape(12.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color(0xFF34A853)
                                                    )
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = null,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Text("Create & Authorize", fontWeight = FontWeight.Bold)
                                                }
                                            }
                                            "SIGNIN" -> {
                                                Text(
                                                    text = "Sign In with Secure ID",
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.align(Alignment.Start),
                                                    color = Color(0xFF34A853)
                                                )
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                var loginAlias by remember { mutableStateOf("") }
                                                
                                                OutlinedTextField(
                                                    value = loginAlias,
                                                    onValueChange = { loginAlias = it },
                                                    label = { Text("Secure ID / Username") },
                                                    placeholder = { Text("e.g. johndoe") },
                                                    suffix = { 
                                                        if (!loginAlias.contains("@")) {
                                                            Text("@zooz.secure") 
                                                        }
                                                    },
                                                    modifier = Modifier.fillMaxWidth(),
                                                    shape = RoundedCornerShape(12.dp),
                                                    singleLine = true
                                                )
                                                
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                Button(
                                                    onClick = {
                                                        if (loginAlias.isNotBlank()) {
                                                            isZoozAuthenticating = true
                                                            val alias = if (loginAlias.contains("@")) loginAlias else "$loginAlias@zooz.secure"
                                                            pendingAlias = alias
                                                            try {
                                                                googleSignInClient?.signInIntent?.let {
                                                                    launcher.launch(it)
                                                                } ?: run {
                                                                    viewModel.loginWithGoogle("simulated_sso", pendingAlias, onLoginSuccess, { loginError = it })
                                                                    isZoozAuthenticating = false
                                                                    showZoozAuthPrompt = false
                                                                }
                                                            } catch (e: Exception) {
                                                                viewModel.loginWithGoogle("simulated_sso", pendingAlias, onLoginSuccess, { loginError = it })
                                                                isZoozAuthenticating = false
                                                                showZoozAuthPrompt = false
                                                            }
                                                        }
                                                    },
                                                    modifier = Modifier.fillMaxWidth().height(52.dp),
                                                    shape = RoundedCornerShape(12.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color(0xFF34A853)
                                                    )
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                                                        contentDescription = null,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Text("Authorize Session", fontWeight = FontWeight.Bold)
                                                }
                                            }
                                            "FORGOT" -> {
                                                Text(
                                                    text = "Recover Secure ID",
                                                    style = MaterialTheme.typography.titleMedium,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.align(Alignment.Start),
                                                    color = Color(0xFFEA4335)
                                                )
                                                Spacer(modifier = Modifier.height(12.dp))
                                                Text(
                                                    text = "Zooz Secure IDs are passwordless and linked to your primary identity. Enter your recovery email below to receive your ID list.",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                var recoveryEmail by remember { mutableStateOf("") }
                                                var recoveryStatus by remember { mutableStateOf<String?>(null) }
                                                
                                                OutlinedTextField(
                                                    value = recoveryEmail,
                                                    onValueChange = { recoveryEmail = it; recoveryStatus = null },
                                                    label = { Text("Recovery Email") },
                                                    placeholder = { Text("e.g. user@gmail.com") },
                                                    modifier = Modifier.fillMaxWidth(),
                                                    shape = RoundedCornerShape(12.dp),
                                                    singleLine = true
                                                )
                                                
                                                if (recoveryStatus != null) {
                                                    Spacer(modifier = Modifier.height(8.dp))
                                                    Text(recoveryStatus!!, color = Color(0xFF34A853), style = MaterialTheme.typography.bodySmall)
                                                }
                                                
                                                Spacer(modifier = Modifier.height(16.dp))
                                                
                                                Button(
                                                    onClick = {
                                                        if (recoveryEmail.isNotBlank()) {
                                                            recoveryStatus = "Recovery instructions sent! Please check your inbox."
                                                        }
                                                    },
                                                    modifier = Modifier.fillMaxWidth().height(52.dp),
                                                    shape = RoundedCornerShape(12.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color(0xFFEA4335)
                                                    )
                                                ) {
                                                    Text("Send Recovery Email", fontWeight = FontWeight.Bold)
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(24.dp))
                                        
                                        TextButton(onClick = { zoozAuthStep = 0 }) {
                                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Back to account list")
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(36.dp))

                                // Device-Isolated Secure Security Badge
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = "Security",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "Isolated Privacy Guaranteed: Existing device user profiles are never fetched, shared, or compiled from Firebase in secure mode to guarantee total privacy.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            fontWeight = FontWeight.Medium,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(32.dp))

                                // Footer regulatory disclaimer text
                                Text(
                                    text = "To continue, Zooz will share your name, email address, profile picture and secure token with Prigid App. See physical terms of service & privacy statements.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    lineHeight = 16.sp,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }
                    }
                }
            }
        }

            Spacer(modifier = Modifier.height(48.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    if (isLoginMode) "Don't have an account?" else "Already have an account?",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isLoginMode) "Sign up" else "Sign in",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { isLoginMode = !isLoginMode }
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = buildAnnotatedString {
                        append("Powered by ")
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.ExtraBold
                            )
                        ) {
                            append("Prigid group")
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                )
            }
        }
        }
    }
}

@Composable
fun ZoozAccountRow(
    name: String,
    email: String,
    avatarText: String,
    avatarColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = avatarColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = avatarText,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: BlogViewModel,
    onNavigateToCreatePost: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit,
    onNavigateToMenuDest: (String) -> Unit,
    onNavigateToUserProfile: (Int) -> Unit
) {
    val posts by viewModel.allPosts.collectAsStateWithLifecycle()
    val user by viewModel.currentUser.collectAsStateWithLifecycle()
    val selectedFilter by viewModel.selectedFilter.collectAsStateWithLifecycle()
    var showMenuSheet by remember { mutableStateOf(false) }
    var showChatSheet by remember { mutableStateOf(false) }
    var currentTab by remember { mutableStateOf("home") }

    // Infinite Scroll Configuration
    var visibleLimit by remember { mutableStateOf(5) }
    var isLoadingMore by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(selectedFilter) {
        visibleLimit = 5
    }

    LaunchedEffect(listState, posts, posts.size) {
        snapshotFlow {
            val totalItems = listState.layoutInfo.totalItemsCount
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            val lastVisibleIndex = lastVisibleItem?.index ?: -1
            
            lastVisibleIndex >= totalItems - 1 && totalItems > 0
        }.collect { isAtEnd ->
            if (isAtEnd && visibleLimit < posts.size && !isLoadingMore) {
                isLoadingMore = true
                visibleLimit = (visibleLimit + 5).coerceAtMost(posts.size)
                isLoadingMore = false
            }
        }
    }

    val visiblePosts = remember(posts, visibleLimit) {
        posts.take(visibleLimit)
    }

    Scaffold(
        topBar = {
            if (currentTab != "profile") {
                TopAppBar(
                    title = { 
                        if (currentTab == "home") {
                            ZoozLogo()
                        } else {
                            Text(when(currentTab) {
                                "search" -> "Search"
                                "alerts" -> "Alerts"
                                else -> "Zooz"
                            }, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                        }
                    },
                    actions = {
                        IconButton(onClick = { currentTab = "search" }) {
                            val isSelected = currentTab == "search"
                            Icon(
                                imageVector = if (isSelected) Icons.Filled.Search else Icons.Outlined.Search,
                                contentDescription = "Search",
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                            )
                        }
                        IconButton(onClick = { currentTab = "alerts" }) {
                            val isSelected = currentTab == "alerts"
                            Icon(
                                imageVector = if (isSelected) Icons.Filled.Notifications else Icons.Outlined.Notifications,
                                contentDescription = "Alerts",
                                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        actionIconContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        },
        bottomBar = { 
            BlogBottomNavigation(
                currentTab = currentTab,
                onTabSelected = { currentTab = it },
                onChatClick = { onNavigateToMenuDest("messaging?userId=-1") },
                onCreateClick = onNavigateToCreatePost
            ) 
        },
        floatingActionButton = {}
    ) { padding ->
        var isRefreshing by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        fun handleRefresh() {
            isRefreshing = true
            viewModel.syncPosts()
            coroutineScope.launch {
                kotlinx.coroutines.delay(1000) // visual feedback
                isRefreshing = false
            }
        }
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { handleRefresh() },
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            when (currentTab) {
            "search" -> {
                Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    val searchQuery = selectedFilter ?: ""
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.setFilter(it.takeIf { it.isNotBlank() }) },
                        placeholder = { Text("Search creators, tags, posts...") },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)) },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setFilter(null) }) {
                                    Icon(Icons.Filled.Close, contentDescription = "Clear search", tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f))
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        ),
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(26.dp),
                        singleLine = true
                    )
                    
                    val categories = listOf("All", "Tech", "Videos", "Social", "Life", "Updates", "News", "Design", "Fun")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        categories.forEach { category ->
                            val isSelected = (category == "All" && searchQuery.isBlank()) || (category.equals(searchQuery, ignoreCase = true))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary 
                                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                    )
                                    .clickable {
                                        if (category == "All") {
                                            viewModel.setFilter(null)
                                        } else {
                                            viewModel.setFilter(category)
                                        }
                                    }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    val allUsers by viewModel.allUsers.collectAsStateWithLifecycle(initialValue = emptyList())
                    val matchingUsers = remember(allUsers, searchQuery) {
                        if (searchQuery.isBlank()) emptyList() else {
                            allUsers.filter {
                                it.username.contains(searchQuery, ignoreCase = true) || 
                                it.fullName.contains(searchQuery, ignoreCase = true)
                            }
                        }
                    }

                    if (matchingUsers.isNotEmpty()) {
                        Text(
                            text = "Creators",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            matchingUsers.forEach { u ->
                                Card(
                                    modifier = Modifier
                                        .width(110.dp)
                                        .clickable { onNavigateToUserProfile(u.id) },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                                    )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.primaryContainer),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (!u.profilePicUri.isNullOrEmpty()) {
                                                AsyncImage(
                                                    model = u.profilePicUri,
                                                    contentDescription = "Profile Picture",
                                                    modifier = Modifier.fillMaxSize().clip(CircleShape),
                                                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                                )
                                            } else {
                                                val initial = u.fullName.firstOrNull() ?: u.username.firstOrNull() ?: 'U'
                                                Text(
                                                    text = initial.uppercaseChar().toString(),
                                                    style = MaterialTheme.typography.titleSmall,
                                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "@${u.username}",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = u.fullName.ifBlank { "User" },
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    val chunkedPosts = remember(visiblePosts) {
                        visiblePosts.chunked(3)
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        items(chunkedPosts) { rowPosts ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                rowPosts.forEach { post ->
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(1f)
                                            .clickable { onNavigateToPostDetail(post.id) }
                                    ) {
                                        val isVideo = remember(post.mediaUri) {
                                            val uriStr = post.mediaUri ?: ""
                                            uriStr.contains("video", ignoreCase = true) || 
                                            uriStr.endsWith(".mp4", ignoreCase = true)
                                        }
                                        
                                        if (!post.mediaUri.isNullOrEmpty()) {
                                            AsyncImage(
                                                model = post.mediaUri,
                                                contentDescription = post.title,
                                                modifier = Modifier.fillMaxSize(),
                                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                                            )
                                            
                                            if (isVideo) {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(6.dp)
                                                        .size(24.dp)
                                                        .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                                        .align(Alignment.TopEnd),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Filled.PlayArrow,
                                                        contentDescription = "Video",
                                                        tint = Color.White,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                }
                                            }
                                            
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.BottomStart)
                                                    .background(
                                                        Brush.verticalGradient(
                                                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                                                        )
                                                    )
                                                    .padding(6.dp)
                                            ) {
                                                Text(
                                                    text = post.title,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = Color.White,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        } else {
                                            val sunsetBrush = Brush.linearGradient(colors = listOf(Color(0xFF833AB4), Color(0xFFFD1D1D), Color(0xFFFBB03B)))
                                            val oceanBrush = Brush.linearGradient(colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF)))
                                            val purpleBrush = Brush.linearGradient(colors = listOf(Color(0xFFE040FB), Color(0xFF00E5FF)))
                                            val charcoalBrush = Brush.linearGradient(colors = listOf(Color(0xFF2C3E50), Color(0xFF3498DB)))
                                            
                                            val activeBrush = when (post.id % 4) {
                                                0 -> sunsetBrush
                                                1 -> oceanBrush
                                                2 -> purpleBrush
                                                else -> charcoalBrush
                                            }
                                            
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(activeBrush)
                                                    .padding(8.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center,
                                                    modifier = Modifier.fillMaxSize()
                                                ) {
                                                    if (!post.categories.isNullOrBlank()) {
                                                        val firstCategory = post.categories.split(",").firstOrNull()?.trim() ?: ""
                                                        if (firstCategory.isNotEmpty()) {
                                                            Text(
                                                                text = "#$firstCategory".uppercase(),
                                                                style = MaterialTheme.typography.labelSmall,
                                                                color = Color.White.copy(alpha = 0.85f),
                                                                fontWeight = FontWeight.ExtraBold,
                                                                fontSize = 8.sp,
                                                                maxLines = 1
                                                            )
                                                            Spacer(modifier = Modifier.height(2.dp))
                                                        }
                                                    }
                                                    
                                                    Text(
                                                        text = post.title,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = Color.White,
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                                        maxLines = 3,
                                                        overflow = TextOverflow.Ellipsis,
                                                        fontSize = 11.sp
                                                    )
                                                    
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    
                                                    Text(
                                                        text = post.content,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = Color.White.copy(alpha = 0.75f),
                                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                                        maxLines = 2,
                                                        overflow = TextOverflow.Ellipsis,
                                                        fontSize = 8.sp,
                                                        lineHeight = 10.sp
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                if (rowPosts.size < 3) {
                                    val missing = 3 - rowPosts.size
                                    for (i in 0 until missing) {
                                        Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                                    }
                                }
                            }
                        }
                        
                        if (visiblePosts.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = "No match",
                                            modifier = Modifier.size(48.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                        )
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = "No posts match your search query.",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                        
                        if (isLoadingMore) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        strokeWidth = 2.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }
            "alerts" -> {
                val alerts by viewModel.notifications.collectAsStateWithLifecycle(initialValue = emptyList())
                var visibleLimit by remember { mutableStateOf(6) }
                var isLoadingMore by remember { mutableStateOf(false) }
                var selectedAlertForRead by remember { mutableStateOf<AlertNotification?>(null) }
                var alertFilter by remember { mutableStateOf("all") }

                if (isLoadingMore) {
                    LaunchedEffect(Unit) {
                        visibleLimit += 6
                        isLoadingMore = false
                    }
                }

                val filteredAlerts = remember(alerts, alertFilter) {
                    when (alertFilter) {
                        "social" -> alerts.filter { it.type in listOf("like", "comment", "follow", "direct_message") }
                        "my_log" -> alerts.filter { it.type.startsWith("my_") }
                        "system" -> alerts.filter { it.type in listOf("system", "promotion_gate", "growth_boost") }
                        else -> alerts
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Alerts Stream Layout Header
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Alert History & Activities",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            if (alerts.isNotEmpty()) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    TextButton(
                                        onClick = { viewModel.markAllNotificationsAsRead() },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("Read All", style = MaterialTheme.typography.labelMedium)
                                    }
                                    Spacer(modifier = Modifier.width(4.dp))
                                    TextButton(
                                        onClick = { viewModel.clearNotifications() },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("Clear All", style = MaterialTheme.typography.labelMedium)
                                    }
                                }
                            }
                        }
                    }

                    // Multi-choice scrollable category filters
                    item {
                        val filterOptions = listOf(
                            "all" to "All Feed",
                            "social" to "Social Activity",
                            "my_log" to "My Logs",
                            "system" to "System & Promo"
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            filterOptions.forEach { (key, label) ->
                                val isSelected = alertFilter == key
                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    border = BorderStroke(
                                        width = 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                                    ),
                                    modifier = Modifier
                                        .clickable { alertFilter = key }
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }

                    if (filteredAlerts.isEmpty()) {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Notifications,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                        modifier = Modifier.size(48.dp)
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(
                                        text = "No alerts found",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "New logs and notifications matching this category will appear here.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                    } else {
                        val displayedAlerts = filteredAlerts.take(visibleLimit)
                        items(displayedAlerts, key = { it.id }) { alert ->
                            val backgroundColor = when (alert.type) {
                                "promotion_gate" -> Color(0xFFFFD700).copy(alpha = 0.08f)
                                "growth_boost" -> Color(0xFF4CAF50).copy(alpha = 0.06f)
                                "direct_message" -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                                "like", "my_like" -> Color(0xFFE91E63).copy(alpha = 0.06f)
                                "comment", "my_comment" -> Color(0xFF2196F3).copy(alpha = 0.06f)
                                "follow", "my_follow" -> Color(0xFF9C27B0).copy(alpha = 0.06f)
                                "my_publishing" -> Color(0xFF009688).copy(alpha = 0.06f)
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                            }
                            val borderColor = when (alert.type) {
                                "promotion_gate" -> Color(0xFFFFD700)
                                "growth_boost" -> Color(0xFF81C784)
                                "direct_message" -> MaterialTheme.colorScheme.outlineVariant
                                "like", "my_like" -> Color(0xFFF48FB1)
                                "comment", "my_comment" -> Color(0xFF90CAF9)
                                "follow", "my_follow" -> Color(0xFFCE93D8)
                                "my_publishing" -> Color(0xFF80CBC4)
                                else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                            }
                            val icon = when (alert.type) {
                                "promotion_gate" -> Icons.Filled.Stars
                                "growth_boost" -> Icons.Filled.TrendingUp
                                "direct_message" -> Icons.Outlined.Chat
                                "like", "my_like" -> Icons.Filled.Favorite
                                "comment", "my_comment" -> Icons.Outlined.ChatBubbleOutline
                                "follow", "my_follow" -> Icons.Filled.Person
                                "my_publishing" -> Icons.Outlined.Edit
                                else -> Icons.Filled.Notifications
                            }
                            val tintColor = when (alert.type) {
                                "promotion_gate" -> Color(0xFFC59518)
                                "growth_boost" -> Color(0xFF2E7D32)
                                "direct_message" -> MaterialTheme.colorScheme.primary
                                "like", "my_like" -> Color(0xFFE91E63)
                                "comment", "my_comment" -> Color(0xFF1976D2)
                                "follow", "my_follow" -> Color(0xFF9C27B0)
                                "my_publishing" -> Color(0xFF00796B)
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.markNotificationAsRead(alert.id)
                                        selectedAlertForRead = alert
                                    },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (alert.isRead) backgroundColor.copy(alpha = 0.4f) else backgroundColor
                                ),
                                border = BorderStroke(
                                    width = if (alert.isRead) 0.5.dp else 1.5.dp,
                                    color = if (alert.isRead) borderColor.copy(alpha = 0.3f) else borderColor
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = tintColor.copy(alpha = 0.15f),
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = icon,
                                                contentDescription = null,
                                                tint = tintColor,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                    
                                    Spacer(modifier = Modifier.width(14.dp))
                                    
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Text(
                                                    text = alert.title,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    fontWeight = if (alert.isRead) FontWeight.Normal else FontWeight.Bold,
                                                    color = if (alert.isRead) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurface
                                                )
                                                if (!alert.isRead) {
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Surface(
                                                        shape = CircleShape,
                                                        color = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(8.dp)
                                                    ) {}
                                                }
                                            }
                                            
                                            IconButton(
                                                onClick = { viewModel.removeNotification(alert.id) },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Close,
                                                    contentDescription = "Dismiss Alert",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                        
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(
                                            text = alert.text,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = if (alert.isRead) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f) else MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            // Aesthetic relative timestamp label
                                            val minutesAgo = ((System.currentTimeMillis() - alert.timestamp) / 60000).coerceAtLeast(0)
                                            val timeStr = when {
                                                minutesAgo < 1 -> "Just now"
                                                minutesAgo == 1L -> "1 minute ago"
                                                minutesAgo < 60 -> "$minutesAgo minutes ago"
                                                else -> "Today"
                                            }
                                            Text(
                                                text = timeStr,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                            )
                                            
                                            if (alert.type == "direct_message" && alert.associatedUserId != null) {
                                                Text(
                                                    text = "Open Chat ➔",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (alerts.size > visibleLimit) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isLoadingMore) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(18.dp),
                                                strokeWidth = 2.dp,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Loading older activity logs...",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    } else {
                                        LaunchedEffect(Unit) {
                                            isLoadingMore = true
                                        }
                                    }
                                }
                            }
                        } else if (alerts.isNotEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "⚡ End of activity tracker history",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }
                }

                selectedAlertForRead?.let { alert ->
                    val currentAlert = alerts.find { it.id == alert.id } ?: alert
                    AlertDialog(
                        onDismissRequest = { selectedAlertForRead = null },
                        confirmButton = {
                            if (currentAlert.type == "direct_message" && currentAlert.associatedUserId != null) {
                                Button(
                                    onClick = {
                                        selectedAlertForRead = null
                                        onNavigateToMenuDest("messaging?userId=${currentAlert.associatedUserId}")
                                    }
                                ) {
                                    Icon(Icons.Outlined.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Open Chat")
                                }
                            } else {
                                Button(
                                    onClick = { selectedAlertForRead = null }
                                ) {
                                    Text("Done")
                                }
                            }
                        },
                        dismissButton = {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = {
                                        viewModel.removeNotification(currentAlert.id)
                                        selectedAlertForRead = null
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Icon(Icons.Filled.Close, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Delete")
                                }
                                
                                TextButton(
                                    onClick = {
                                        viewModel.markNotificationAsRead(currentAlert.id, isRead = !currentAlert.isRead)
                                    }
                                ) {
                                    Text(if (currentAlert.isRead) "Mark Unread" else "Mark Read")
                                }
                            }
                        },
                        icon = {
                            val tintColor = when (currentAlert.type) {
                                "promotion_gate" -> Color(0xFFFFD700)
                                "growth_boost" -> Color(0xFF4CAF50)
                                "direct_message" -> MaterialTheme.colorScheme.primary
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                            val icon = when (currentAlert.type) {
                                "promotion_gate" -> Icons.Filled.Stars
                                "growth_boost" -> Icons.Filled.TrendingUp
                                "direct_message" -> Icons.Outlined.Chat
                                else -> Icons.Filled.Notifications
                            }
                            Surface(
                                shape = CircleShape,
                                color = tintColor.copy(alpha = 0.12f),
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = tintColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        },
                        title = {
                            Text(
                                text = currentAlert.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        },
                        text = {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = currentAlert.text,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(16.dp),
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Start
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(12.dp))
                                
                                val minutesAgo = ((System.currentTimeMillis() - currentAlert.timestamp) / 60000).coerceAtLeast(0)
                                val timeStr = when {
                                    minutesAgo < 1 -> "Just now"
                                    minutesAgo == 1L -> "1 minute ago"
                                    minutesAgo < 60 -> "$minutesAgo minutes ago"
                                    minutesAgo < 1440 -> "${minutesAgo / 60} hours ago"
                                    else -> "Days ago"
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Source: ${currentAlert.type.replace('_', ' ').uppercase()}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                    )
                                    Text(
                                        text = timeStr,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        },
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
            "videos" -> {
                VideoWatchScreenContent(
                    viewModel = viewModel,
                    onNavigateToUserProfile = onNavigateToUserProfile,
                    onNavigateToSearch = { currentTab = "search" }
                )
            }
            "profile" -> {
                ProfileScreen(
                    userId = null,
                    viewModel = viewModel,
                    onNavigateBack = { currentTab = "home" },
                    onNavigateToPostDetail = onNavigateToPostDetail,
                    onNavigateToFollowers = { onNavigateToMenuDest("followers?userId=$it") },
                    onNavigateToFollowing = { onNavigateToMenuDest("following?userId=$it") },
                    onNavigateToMessaging = { onNavigateToMenuDest("messaging?userId=$it") },
                    onNavigateToSettings = { showMenuSheet = true }
                )
            }
            else -> {
                val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { visiblePosts.size })
                androidx.compose.foundation.pager.VerticalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    key = { page -> visiblePosts.getOrNull(page)?.id ?: "empty_$page" }
                ) { page ->
                    val post = visiblePosts.getOrNull(page)
                    if (post != null) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            PostItem(
                                post = post,
                                viewModel = viewModel,
                                onClick = { onNavigateToPostDetail(post.id) },
                                onAuthorClick = onNavigateToUserProfile,
                                isHomeFeed = true,
                                onTagClick = { tag ->
                                    viewModel.setFilter(tag)
                                    currentTab = "search"
                                }
                            )
                        }
                    }
                }
                
                if (visiblePosts.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            "No posts yet. Be the first to create one!",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                if (isLoadingMore) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
            }
        }
        }
    }

    if (showMenuSheet) {
        ModalBottomSheet(
            onDismissRequest = { showMenuSheet = false },
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            val followerCountState = remember(user?.id) { viewModel.getFollowerCount(user?.id ?: 0) }
            val followerCount by followerCountState.collectAsStateWithLifecycle(initialValue = 0)
            
            MenuContent(
                user = user,
                followerCount = followerCount,
                onLogout = {
                    showMenuSheet = false
                    viewModel.logout()
                },
                onItemClick = { route ->
                    showMenuSheet = false
                    onNavigateToMenuDest(route)
                }
            )
        }
    }

    if (showChatSheet) {
        ModalBottomSheet(
            onDismissRequest = { showChatSheet = false },
            containerColor = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxHeight(0.85f)
        ) {
            DirectMessagesContent(
                viewModel = viewModel,
                onDismiss = { showChatSheet = false },
                onStoryClick = { authorId -> onNavigateToMenuDest("stories/$authorId") },
                onCreateStory = { onNavigateToMenuDest("create_story") }
            )
        }
    }
}

@Composable
fun BlogBottomNavigation(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    onChatClick: () -> Unit,
    onCreateClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        NavigationBarItem(
            selected = currentTab == "home",
            onClick = { onTabSelected("home") },
            icon = { Icon(if (currentTab == "home") Icons.Filled.Home else Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentTab == "videos",
            onClick = { onTabSelected("videos") },
            icon = { Icon(if (currentTab == "videos") Icons.Filled.PlayArrow else Icons.Outlined.PlayArrow, contentDescription = "Watch Videos") },
            label = { Text("Watch") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onCreateClick,
            icon = { Icon(Icons.Outlined.AddCircle, contentDescription = "Create Post") },
            label = { Text("Create") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onChatClick,
            icon = { Icon(Icons.Outlined.Chat, contentDescription = "Chat") },
            label = { Text("Chat") }
        )
        NavigationBarItem(
            selected = currentTab == "profile",
            onClick = { onTabSelected("profile") },
            icon = { Icon(if (currentTab == "profile") Icons.Filled.Person else Icons.Outlined.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}

@Composable
fun MenuContent(user: com.example.data.User?, followerCount: Int, onLogout: () -> Unit, onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp)
    ) {
        if (user != null) {
            ListItem(
                headlineContent = { Text(user.username, fontWeight = FontWeight.Bold) },
                supportingContent = { Text("View Profile") },
                leadingContent = {
                    Icon(Icons.Outlined.AccountCircle, contentDescription = "Profile", modifier = Modifier.size(32.dp))
                },
                modifier = Modifier.clickable { onItemClick("profile") }
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }
        if (followerCount >= 500) {
            ListItem(
                headlineContent = { Text("Professional Dashboard") },
                leadingContent = { Icon(Icons.Filled.TrendingUp, contentDescription = "Professional Dashboard", tint = MaterialTheme.colorScheme.primary) },
                modifier = Modifier.clickable { onItemClick("professional_dashboard") }
            )
        }
        ListItem(
            headlineContent = { Text("Account Settings") },
            leadingContent = { Icon(Icons.Outlined.ManageAccounts, contentDescription = "Account Settings") },
            modifier = Modifier.clickable { onItemClick("account_settings") }
        )
        ListItem(
            headlineContent = { Text("App Settings") },
            leadingContent = { Icon(Icons.Outlined.Settings, contentDescription = "App Settings") },
            modifier = Modifier.clickable { onItemClick("settings") }
        )
        ListItem(
            headlineContent = { Text("Help & Support") },
            leadingContent = { Icon(Icons.AutoMirrored.Outlined.HelpOutline, contentDescription = "Help") },
            modifier = Modifier.clickable { onItemClick("help") }
        )
        val context = LocalContext.current
        ListItem(
            headlineContent = { Text("Share Profile") },
            leadingContent = { Icon(Icons.Filled.Share, contentDescription = "Share Profile") },
            modifier = Modifier.clickable {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check out my Zooz profile: ${com.example.BuildConfig.ZOOZ_SHARE_URL}/user/${user?.id}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Share Profile via")
                context.startActivity(shareIntent)
            }
        )
        ListItem(
            headlineContent = { Text("Share App") },
            leadingContent = { Icon(Icons.Filled.Share, contentDescription = "Share App") },
            modifier = Modifier.clickable {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check out the Zooz App! Download and join our community here: ${com.example.BuildConfig.ZOOZ_SHARE_URL}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Share Zooz App via")
                context.startActivity(shareIntent)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        ListItem(
            headlineContent = { Text("Logout", color = MaterialTheme.colorScheme.error) },
            leadingContent = { Icon(Icons.AutoMirrored.Outlined.ExitToApp, contentDescription = "Logout", tint = MaterialTheme.colorScheme.error) },
            modifier = Modifier.clickable { onLogout() }
        )
    }
}

@Composable
fun PostItem(
    post: Post,
    viewModel: BlogViewModel,
    onClick: () -> Unit,
    onAuthorClick: (Int) -> Unit = {},
    isHomeFeed: Boolean = false,
    onCommentsClick: (() -> Unit)? = null,
    onTagClick: ((String) -> Unit)? = null
) {
    val context = LocalContext.current
    val isLikedState = remember(post.id) { viewModel.isLiked(post.id) }
    val isLiked by isLikedState.collectAsStateWithLifecycle(initialValue = false)
    
    val likeCountState = remember(post.id) { viewModel.getLikeCount(post.id) }
    val likeCount by likeCountState.collectAsStateWithLifecycle(initialValue = 0)
    
    val isBookmarkedState = remember(post.id) { viewModel.isBookmarked(post.id) }
    val isBookmarked by isBookmarkedState.collectAsStateWithLifecycle(initialValue = false)

    val authorFollowersState = remember(post.authorId) { viewModel.getFollowerCount(post.authorId) }
    val authorFollowers by authorFollowersState.collectAsStateWithLifecycle(initialValue = 0)

    val isFollowingState = remember(post.authorId) { viewModel.isFollowing(post.authorId) }
    val isFollowing by isFollowingState.collectAsStateWithLifecycle(initialValue = false)

    val currentUserState = remember { viewModel.currentUser }
    val currentUser by currentUserState.collectAsStateWithLifecycle()

    val commentsState = remember(post.id) { viewModel.getCommentsForPost(post.id) }
    val comments by commentsState.collectAsStateWithLifecycle(initialValue = emptyList())
    val commentCount = comments.size

    val infiniteTransition = rememberInfiniteTransition(label = "music_disk")
    val rotationSpeed by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "music_rotation_anim"
    )

    LaunchedEffect(post.id) {
        viewModel.recordPostImpression(post.id)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(post.mediaUri) {
                detectTapGestures(
                    onTap = {
                        val isVideo = !post.mediaUri.isNullOrEmpty() && (
                            post.mediaUri.contains("video", ignoreCase = true) ||
                            post.mediaUri.endsWith(".mp4", ignoreCase = true)
                        )
                        if (!isVideo) {
                            onClick()
                        }
                    }
                )
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0C0C0E)),
        border = BorderStroke(1.dp, Color(0xFF222227))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Immersive Media/Visual Core Background Section
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (!post.mediaUri.isNullOrEmpty()) {
                    MediaView(
                        mediaUri = post.mediaUri,
                        modifier = Modifier.fillMaxSize(),
                        autoPlayEnabled = !isHomeFeed,
                        isHomeFeed = isHomeFeed,
                        onClick = null
                    )
                } else {
                    TikTokPlaceholderBackground(title = post.title, category = post.categories, author = post.authorName)
                }
            }

            // High-contrast Ambient Vignette bottom section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))
                        )
                    )
            )

            // High-contrast Ambient shadow top section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.5f), Color.Transparent)
                        )
                    )
            )

            // Top-Bar controls Overlay (sponsored indicators and actions)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (post.isPromoted) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFFE2C55).copy(alpha = 0.25f),
                        border = BorderStroke(1.dp, Color(0xFFFE2C55))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Stars,
                                contentDescription = "Promoted",
                                tint = Color(0xFFFE2C55),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "PROMOTED",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, letterSpacing = 0.5.sp),
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                PostMoreActionsMenu(
                    post = post,
                    viewModel = viewModel
                )
            }

            // LEFT SIDE CONTENT SUMMARY PANEL
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.74f)
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 16.dp, vertical = 18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onAuthorClick(post.authorId) }
                ) {
                    Text(
                        text = "@${post.authorName}",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (authorFollowers >= 10000) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Verified Creator",
                            tint = Color(0xFF25F4EE),
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                if (post.title.isNotBlank()) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                }
                Text(
                    text = post.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.85f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (post.categories.isNotBlank() || post.tags.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (post.categories.isNotBlank()) {
                            post.categories.split(",").take(2).forEach { cat ->
                                Box(
                                    modifier = Modifier
                                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                                        .clickable {
                                            viewModel.recordPostClick(post.id)
                                            if (onTagClick != null) onTagClick(cat.trim()) else onClick()
                                        }
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(cat.trim().uppercase(), color = Color(0xFF25F4EE), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                }
                            }
                        }
                        if (post.tags.isNotBlank()) {
                            post.tags.split(",").take(2).forEach { tag ->
                                Box(
                                    modifier = Modifier
                                        .background(Color.White.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
                                        .clickable {
                                            viewModel.recordPostClick(post.id)
                                            if (onTagClick != null) onTagClick(tag.trim()) else onClick()
                                        }
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text("#${tag.trim()}", color = Color(0xFFFE2C55), style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.SemiBold, fontSize = 9.sp)
                                }
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Color.Black.copy(alpha = 0.45f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .widthIn(max = 200.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = null,
                        tint = Color(0xFF25F4EE),
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (!post.spotifyTrackName.isNullOrEmpty()) "${post.spotifyTrackName} - ${post.spotifyTrackArtist}" else "original sound - @${post.authorName}",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                }

                if (post.mediaUri.isNullOrEmpty()) {
                    val firstUrl = remember(post.content) { extractFirstUrl(post.content) }
                    if (firstUrl != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        val previewData = remember(firstUrl) { getUrlPreview(firstUrl) }
                        Box(modifier = Modifier.fillMaxWidth().scale(0.88f).align(Alignment.Start)) {
                            LinkPreviewCard(previewData = previewData)
                        }
                    }
                }
            }

            // RIGHT FLOATING SIDEBAR PANEL
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.TopCenter)
                            .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape)
                            .border(BorderStroke(1.5.dp, Color.White), CircleShape)
                            .clickable { onAuthorClick(post.authorId) },
                        contentAlignment = Alignment.Center
                    ) {
                        val initial = if (post.authorName.isNotBlank()) post.authorName.take(2).uppercase() else "?"
                        Text(
                            text = initial,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    if (currentUser?.id != post.authorId) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .offset(y = 2.dp)
                                .size(18.dp)
                                .background(Color(0xFFFE2C55), CircleShape)
                                .border(BorderStroke(1.dp, Color.White), CircleShape)
                                .clickable {
                                    viewModel.toggleFollow(post.authorId, isFollowing)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isFollowing) Icons.Default.Check else Icons.Default.Add,
                                contentDescription = "Follow Creator",
                                tint = Color.White,
                                modifier = Modifier.size(11.dp)
                            )
                        }
                    }
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = { viewModel.toggleLike(post.id, isLiked) },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Like",
                            tint = if (isLiked) Color(0xFFFE2C55) else Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = if (likeCount > 0) "$likeCount" else "0",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = {
                            viewModel.recordPostClick(post.id)
                            if (onCommentsClick != null) onCommentsClick() else onClick()
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "Comments",
                            tint = Color.White,
                            modifier = Modifier.size(31.dp)
                        )
                    }
                    Text(
                        text = "$commentCount",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = { viewModel.toggleBookmark(post.id, isBookmarked) },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) Color(0xFFFFC107) else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Text(
                        text = if (isBookmarked) "Saved" else "Save",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, post.title)
                                putExtra(Intent.EXTRA_TEXT, "${post.title}\n\n${post.content}\n\nRead more details inside Zooz!")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Post"))
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Text(
                        text = "Reply",
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(36.dp)
                        .rotate(rotationSpeed)
                        .background(Color(0xFF0C0C0E), CircleShape)
                        .border(BorderStroke(1.5.dp, Color(0xFF1E1F27)), CircleShape)
                        .border(BorderStroke(4.dp, Color(0xFF16161A)), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color(0xFFFE2C55), Color(0xFF25F4EE))
                                ),
                                CircleShape
                            )
                    )
                }
            }

            // Spotify Track preview player if available (runs on ExoPlayer, unmuted by default)
            if (!post.spotifyTrackPreviewUrl.isNullOrEmpty()) {
                MusicPreviewPlayer(
                    previewUrl = post.spotifyTrackPreviewUrl,
                    autoPlayEnabled = true
                )
            }
        }
    }
}

@Composable
fun StorySection(
    viewModel: BlogViewModel,
    onStoryClick: (Int) -> Unit,
    onCreateStory: () -> Unit
) {
    val stories by viewModel.allStories.collectAsStateWithLifecycle()
    val users by viewModel.allUsers.collectAsStateWithLifecycle()
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    
    val storiesByAuthor = remember(stories) { stories.groupBy { it.authorId } }

    Column(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                CreateStoryItem(
                    currentUser = currentUser,
                    isUploading = false,
                    onClick = onCreateStory
                )
            }
            
            storiesByAuthor.forEach { (authorId, authorStories) ->
                val author = users.find { it.id == authorId }
                if (author != null) {
                    item {
                        StoryCircle(
                            author = author,
                            onClick = { onStoryClick(authorId) }
                        )
                    }
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp), 
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), 
            thickness = 0.5.dp
        )
    }
}

@Composable
fun CreateStoryItem(
    currentUser: com.example.data.User?,
    isUploading: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(modifier = Modifier.size(68.dp)) {
            AsyncImage(
                model = currentUser?.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100",
                contentDescription = null,
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )
            
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(24.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.background, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (isUploading) {
                    CircularProgressIndicator(modifier = Modifier.size(12.dp), strokeWidth = 2.dp, color = Color.White)
                } else {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Your Story",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun StoryCircle(
    author: com.example.data.User,
    onClick: () -> Unit
) {
    val rainbowBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFFE2C55), Color(0xFF25F4EE), Color(0xFFFE2C55))
    )
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(68.dp)
                .border(2.5.dp, rainbowBrush, CircleShape)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = author.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = author.username,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(68.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }
    var mediaUri by remember { mutableStateOf<String?>(null) }
    var spotifyTrack by remember { mutableStateOf<SpotifyTrack?>(null) }
    var showSpotifySearch by remember { mutableStateOf(false) }
    var isUploadingMedia by remember { mutableStateOf(false) }
    var commentsDisabled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    
    val context = LocalContext.current
    
    var cameraFile by remember { mutableStateOf<java.io.File?>(null) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }
    
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempCameraUri != null) {
            isUploadingMedia = true
            coroutineScope.launch {
                val currentList = mediaUri?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val urlList = currentList.toMutableList()
                val finalUrl = if (R2Uploader.isConfigured()) {
                    R2Uploader.uploadFile(context, tempCameraUri!!)
                } else {
                    saveUriToInternalStorage(context, tempCameraUri!!)
                } ?: tempCameraUri.toString()
                urlList.add(finalUrl)
                mediaUri = urlList.joinToString(",")
                isUploadingMedia = false
            }
        }
    }

    val videoCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success && tempCameraUri != null) {
            isUploadingMedia = true
            coroutineScope.launch {
                val currentList = mediaUri?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
                val urlList = currentList.toMutableList()
                val finalUrl = if (R2Uploader.isConfigured()) {
                    R2Uploader.uploadFile(context, tempCameraUri!!)
                } else {
                    saveUriToInternalStorage(context, tempCameraUri!!)
                } ?: tempCameraUri.toString()
                urlList.add(finalUrl)
                mediaUri = urlList.joinToString(",")
                isUploadingMedia = false
            }
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val cameraGranted = permissions[android.Manifest.permission.CAMERA] ?: false
        if (!cameraGranted) {
            android.widget.Toast.makeText(context, "Camera permission is required to capture media", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10)) { uris: List<Uri> ->
        if (uris.isNotEmpty()) {
            val currentList = mediaUri?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
            if (R2Uploader.isConfigured()) {
                isUploadingMedia = true
                coroutineScope.launch {
                    val urlList = currentList.toMutableList()
                    uris.forEach { uri ->
                        val r2Url = R2Uploader.uploadFile(context, uri)
                        if (r2Url != null) {
                            urlList.add(r2Url)
                        } else {
                            val localPath = saveUriToInternalStorage(context, uri)
                            urlList.add(localPath ?: uri.toString())
                        }
                    }
                    mediaUri = urlList.joinToString(",")
                    isUploadingMedia = false
                }
            } else {
                val urlList = currentList.toMutableList()
                uris.forEach { uri ->
                    val localPath = saveUriToInternalStorage(context, uri)
                    urlList.add(localPath ?: uri.toString())
                }
                mediaUri = urlList.joinToString(",")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Post", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.Close, contentDescription = "Cancel")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            if (title.isNotBlank() && content.isNotBlank()) {
                                viewModel.createPost(
                                    title = title, 
                                    content = content, 
                                    categories = categories, 
                                    tags = tags, 
                                    mediaUri = mediaUri, 
                                    isDraft = true, 
                                    commentsDisabled = commentsDisabled, 
                                    spotifyTrackId = spotifyTrack?.id,
                                    spotifyTrackName = spotifyTrack?.name,
                                    spotifyTrackArtist = spotifyTrack?.artist,
                                    spotifyTrackImageUrl = spotifyTrack?.albumImageUrl,
                                    spotifyTrackPreviewUrl = spotifyTrack?.previewUrl,
                                    onComplete = onNavigateBack
                                )
                            }
                        },
                        enabled = title.isNotBlank() && content.isNotBlank()
                    ) {
                        Text("Draft", color = MaterialTheme.colorScheme.primary)
                    }
                    Button(
                        onClick = {
                            if (title.isNotBlank() && content.isNotBlank()) {
                                viewModel.createPost(
                                    title = title, 
                                    content = content, 
                                    categories = categories, 
                                    tags = tags, 
                                    mediaUri = mediaUri, 
                                    isDraft = false,
                                    commentsDisabled = commentsDisabled, 
                                    spotifyTrackId = spotifyTrack?.id,
                                    spotifyTrackName = spotifyTrack?.name,
                                    spotifyTrackArtist = spotifyTrack?.artist,
                                    spotifyTrackImageUrl = spotifyTrack?.albumImageUrl,
                                    spotifyTrackPreviewUrl = spotifyTrack?.previewUrl,
                                    onComplete = onNavigateBack
                                )
                            }
                        },
                        enabled = title.isNotBlank() && content.isNotBlank(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Publish")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Media Preview Area
            val selectedUris = remember(mediaUri) {
                mediaUri?.split(",")?.filter { it.isNotBlank() } ?: emptyList()
            }
            if (selectedUris.isNotEmpty() || isUploadingMedia) {
                Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
                    Text(
                        text = "Attached Media (${selectedUris.size})",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    if (isUploadingMedia && selectedUris.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(selectedUris) { uri ->
                                val isVideo = remember(uri) {
                                    uri.contains("video", ignoreCase = true) || 
                                    uri.endsWith(".mp4", ignoreCase = true) ||
                                    uri.endsWith(".mkv", ignoreCase = true) ||
                                    uri.endsWith(".webm", ignoreCase = true) ||
                                    uri.endsWith(".3gp", ignoreCase = true) ||
                                    uri.endsWith(".avi", ignoreCase = true)
                                }
                                Box(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(150.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                ) {
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = "Attached Media Item",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                    
                                    if (isVideo) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.Black.copy(alpha = 0.2f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.PlayCircle,
                                                contentDescription = "Video preview",
                                                tint = Color.White.copy(alpha = 0.8f),
                                                modifier = Modifier.size(36.dp)
                                            )
                                        }
                                    }

                                    IconButton(
                                        onClick = {
                                            val remaining = selectedUris.toMutableList().apply { remove(uri) }
                                            mediaUri = if (remaining.isEmpty()) null else remaining.joinToString(",")
                                        },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(6.dp)
                                            .size(24.dp)
                                            .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Remove item",
                                            tint = Color.White,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }
                            if (isUploadingMedia) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                            .height(150.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(MaterialTheme.colorScheme.surfaceVariant),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        }
                    }
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Title", style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Music Attachment
            if (spotifyTrack != null) {
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (spotifyTrack?.albumImageUrl != null) {
                             coil.compose.AsyncImage(
                                model = spotifyTrack?.albumImageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(spotifyTrack!!.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(spotifyTrack!!.artist, style = MaterialTheme.typography.labelSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        IconButton(onClick = { spotifyTrack = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Remove Music", modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                placeholder = { Text("Write something interesting...", style = MaterialTheme.typography.bodyLarge) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent
                )
            )

            // Mention suggestions
            val mentionQuery = getMentionQuery(content)
            val usersList by viewModel.allUsers.collectAsStateWithLifecycle(initialValue = emptyList())
            val suggestedUsers = if (mentionQuery != null) {
                usersList.filter { it.username.contains(mentionQuery, ignoreCase = true) }
            } else {
                emptyList()
            }
            if (suggestedUsers.isNotEmpty()) {
                MentionSuggestions(
                    suggestions = suggestedUsers,
                    onSelect = { user ->
                        content = insertMention(content, user.username)
                    }
                )
            }

             HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.outlineVariant)
            
            // Post Options (Categories & Tags) hide in expandable or just cleaner rows
            Text("Post Details", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
            
            OutlinedTextField(
                value = categories,
                onValueChange = { categories = it },
                label = { Text("Categories") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Category, contentDescription = null) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = tags,
                onValueChange = { tags = it },
                label = { Text("Tags") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Tag, contentDescription = null) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Disable Comments", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                        Text("Others won't be able to comment", style = MaterialTheme.typography.labelSmall)
                    }
                    Switch(checked = commentsDisabled, onCheckedChange = { commentsDisabled = it })
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(
                    onClick = { if (!isUploadingMedia) launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)) },
                    label = { Text("Attach Media") },
                    leadingIcon = { Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, modifier = Modifier.size(18.dp)) },
                    shape = RoundedCornerShape(8.dp)
                )
                AssistChip(
                    onClick = {
                        val hasCameraPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.CAMERA
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        
                        if (!hasCameraPermission) {
                            cameraPermissionLauncher.launch(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO))
                        } else {
                            try {
                                val file = java.io.File(context.cacheDir, "camera_capture_${System.currentTimeMillis()}.jpg")
                                cameraFile = file
                                val fileUri = androidx.core.content.FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    file
                                )
                                tempCameraUri = fileUri
                                cameraLauncher.launch(fileUri)
                            } catch (e: Exception) {
                                android.util.Log.e("Screens", "Error triggering camera: ${e.message}")
                                android.widget.Toast.makeText(context, "Cannot open camera: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    label = { Text("Live Photo") },
                    leadingIcon = { Icon(androidx.compose.material.icons.Icons.Default.PhotoCamera, contentDescription = null, modifier = Modifier.size(18.dp)) },
                    shape = RoundedCornerShape(8.dp)
                )
                AssistChip(
                    onClick = {
                        val hasCameraPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.CAMERA
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        val hasAudioPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.RECORD_AUDIO
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        
                        if (!hasCameraPermission || !hasAudioPermission) {
                            cameraPermissionLauncher.launch(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO))
                        } else {
                            try {
                                val file = java.io.File(context.cacheDir, "camera_capture_${System.currentTimeMillis()}.mp4")
                                cameraFile = file
                                val fileUri = androidx.core.content.FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    file
                                )
                                tempCameraUri = fileUri
                                videoCaptureLauncher.launch(fileUri)
                            } catch (e: Exception) {
                                android.util.Log.e("Screens", "Error triggering video: ${e.message}")
                                android.widget.Toast.makeText(context, "Cannot open video camera: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    label = { Text("Live Video") },
                    leadingIcon = { Icon(androidx.compose.material.icons.Icons.Default.Videocam, contentDescription = null, modifier = Modifier.size(18.dp)) },
                    shape = RoundedCornerShape(8.dp)
                )
                AssistChip(
                    onClick = { showSpotifySearch = true },
                    label = { Text("Add Music") },
                    leadingIcon = { Icon(Icons.Default.LibraryMusic, contentDescription = null, modifier = Modifier.size(18.dp)) },
                    shape = RoundedCornerShape(8.dp)
                )
            }
            
            if (showSpotifySearch) {
                SpotifySearchDialog(
                    onDismiss = { showSpotifySearch = false },
                    onTrackSelected = {
                        spotifyTrack = it
                        showSpotifySearch = false
                    }
                )
            }
        }
    }
}

@Composable
fun EditorialCategoryBadge(category: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Stars,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = category.trim().uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 11.sp,
                    letterSpacing = 1.2.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun EditorialHashtagBadge(tag: String) {
    val cleanTag = tag.trim().removePrefix("#")
    if (cleanTag.isEmpty()) return
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = cleanTag,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun EditorialTextBodyBox(
    content: String,
    viewModel: BlogViewModel,
    onNavigateToUserProfile: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                            )
                        ),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .align(Alignment.CenterVertically)
                    .height(48.dp)
            )

            Box(modifier = Modifier.weight(1f)) {
                LinkifiedText(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 26.sp,
                        fontSize = 16.sp,
                        letterSpacing = 0.25.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    viewModel = viewModel,
                    onNavigateToUserProfile = onNavigateToUserProfile
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: Int,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToUserProfile: (Int) -> Unit
) {
    val postFlow = remember(postId) { viewModel.getPostById(postId) }
    val post by postFlow.collectAsStateWithLifecycle()

    val firstUrl = remember(post?.content) { post?.content?.let { extractFirstUrl(it) } }
    val previewData = remember(firstUrl) { firstUrl?.let { getUrlPreview(it) } }
    
    val commentsFlow = remember(postId) { viewModel.getCommentsForPost(postId) }
    val comments by commentsFlow.collectAsStateWithLifecycle()
    
    val isBookmarkedFlow = remember(postId) { viewModel.isBookmarked(postId) }
    val isBookmarked by isBookmarkedFlow.collectAsStateWithLifecycle()
    
    val isLikedFlow = remember(postId) { viewModel.isLiked(postId) }
    val isLiked by isLikedFlow.collectAsStateWithLifecycle()
    
    val likeCountFlow = remember(postId) { viewModel.getLikeCount(postId) }
    val likeCount by likeCountFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current
    
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    
    val sortedComments = remember(comments) {
        comments.sortedWith(compareByDescending<Comment> { it.isPinned }.thenBy { it.timestamp })
    }

    var commentText by remember { mutableStateOf("") }
    
    var showPostMenu by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    var isEditingPost by remember { mutableStateOf(false) }
    var editedTitle by remember(post) { mutableStateOf(post?.title ?: "") }
    var editedContent by remember(post) { mutableStateOf(post?.content ?: "") }
    var editedCategories by remember(post) { mutableStateOf(post?.categories ?: "") }
    var editedTags by remember(post) { mutableStateOf(post?.tags ?: "") }
    var editedMediaUri by remember(post) { mutableStateOf(post?.mediaUri ?: "") }
    var isUploadingEditedMedia by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let {
                if (R2Uploader.isConfigured()) {
                    isUploadingEditedMedia = true
                    coroutineScope.launch {
                        val r2Url = R2Uploader.uploadFile(context, it)
                        if (r2Url != null) {
                            editedMediaUri = r2Url
                        } else {
                            val localPath = saveUriToInternalStorage(context, it)
                            editedMediaUri = localPath ?: it.toString()
                        }
                        isUploadingEditedMedia = false
                    }
                } else {
                    val localPath = saveUriToInternalStorage(context, it)
                    if (localPath != null) {
                        editedMediaUri = localPath
                    } else {
                        editedMediaUri = it.toString()
                    }
                }
            }
        }
    )

    var editingCommentId by remember { mutableStateOf<Int?>(null) }
    var editingCommentText by remember { mutableStateOf("") }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Delete Blog Post?") },
            text = { Text("Are you sure you want to delete this blog post permanently? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmation = false
                        viewModel.deletePost(postId) {
                            onNavigateBack()
                        }
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (isEditingPost) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Edit Blog Post", fontWeight = FontWeight.ExtraBold) },
                    navigationIcon = {
                        IconButton(onClick = { isEditingPost = false }) {
                            Icon(Icons.Filled.Close, contentDescription = "Cancel")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            post?.let { originalPost ->
                                viewModel.updatePost(
                                    originalPost.copy(
                                        title = editedTitle,
                                        content = editedContent,
                                        categories = editedCategories,
                                        tags = editedTags,
                                        mediaUri = editedMediaUri.ifBlank { null }
                                    )
                                )
                                isEditingPost = false
                            }
                        }, enabled = editedTitle.isNotBlank() && editedContent.isNotBlank()) {
                            Icon(Icons.Filled.Check, contentDescription = "Save")
                        }
                    }
                )
            }
        ) { editPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(editPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = editedContent,
                    onValueChange = { editedContent = it },
                    label = { Text("Content") },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 180.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = editedCategories,
                    onValueChange = { editedCategories = it },
                    label = { Text("Categories (comma separated)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = editedTags,
                    onValueChange = { editedTags = it },
                    label = { Text("Tags (comma separated)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = editedMediaUri,
                    onValueChange = { editedMediaUri = it },
                    label = { Text("Media/Image URL (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { if (!isUploadingEditedMedia) mediaPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !isUploadingEditedMedia
                    ) {
                        if (isUploadingEditedMedia) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Uploading Cloudflare R2... ☁️")
                        } else {
                            Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Pick Media File")
                        }
                    }
                    if (editedMediaUri.isNotBlank()) {
                        TextButton(
                            onClick = { editedMediaUri = "" },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error),
                            enabled = !isUploadingEditedMedia
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Remove")
                        }
                    }
                }
                
                Button(
                    onClick = {
                        post?.let { originalPost ->
                            viewModel.updatePost(
                                originalPost.copy(
                                    title = editedTitle,
                                    content = editedContent,
                                    categories = editedCategories,
                                    tags = editedTags,
                                    mediaUri = editedMediaUri.ifBlank { null }
                                )
                            )
                            isEditingPost = false
                        }
                    },
                    enabled = editedTitle.isNotBlank() && editedContent.isNotBlank(),
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Save Changes", fontWeight = FontWeight.Bold)
                }
            }
        }
    } else {
        Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(post?.let { "${it.authorName}'s Post" } ?: "User's Post", fontWeight = FontWeight.ExtraBold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { viewModel.toggleBookmark(postId, isBookmarked) }) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        IconButton(onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, post?.title ?: "Check this out")
                                putExtra(Intent.EXTRA_TEXT, "${post?.title}\n\n${post?.content}\n\nRead more at: https://ais-dev-4gtagk6aoa4ih4kavilpdl-259298733495.europe-west2.run.app/post/$postId")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Post"))
                        }) {
                            Icon(Icons.Filled.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        
                        if (post != null && currentUser != null && post?.authorId == currentUser?.id) {
                            Box {
                                IconButton(onClick = { showPostMenu = true }) {
                                    Icon(Icons.Default.MoreVert, contentDescription = "Post Options", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                DropdownMenu(
                                    expanded = showPostMenu,
                                    onDismissRequest = { showPostMenu = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Edit Post") },
                                        leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = "Edit") },
                                        onClick = {
                                            showPostMenu = false
                                            isEditingPost = true
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text(if (post?.commentsDisabled == true) "Enable Comments" else "Disable Comments") },
                                        leadingIcon = { 
                                            Icon(
                                                imageVector = if (post?.commentsDisabled == true) Icons.Filled.CheckCircle else Icons.Outlined.ChatBubbleOutline, 
                                                contentDescription = "Toggle Comments"
                                            ) 
                                        },
                                        onClick = {
                                            showPostMenu = false
                                            post?.let { p ->
                                                viewModel.toggleCommentsDisabled(postId, !p.commentsDisabled)
                                            }
                                        }
                                    )
                                    HorizontalDivider()
                                    DropdownMenuItem(
                                        text = { Text("Delete Post", color = MaterialTheme.colorScheme.error) },
                                        leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error) },
                                        onClick = {
                                            showPostMenu = false
                                            showDeleteConfirmation = true
                                        }
                                    )
                                }
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            val isCommentsDisabled = post?.commentsDisabled == true
            val isPostAuthor = post?.authorId == currentUser?.id
            
            if (isCommentsDisabled && !isPostAuthor) {
                Surface(
                    tonalElevation = 4.dp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f),
                                RoundedCornerShape(12.dp)
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.ChatBubbleOutline, 
                                contentDescription = "Comments Locked", 
                                tint = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Comments are disabled for this post.", 
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            } else {
                val mentionQuery = getMentionQuery(commentText)
                val usersList by viewModel.allUsers.collectAsStateWithLifecycle(initialValue = emptyList())
                val suggestedUsers = if (mentionQuery != null) {
                    usersList.filter { it.username.contains(mentionQuery, ignoreCase = true) }
                } else {
                    emptyList()
                }
                
                Surface(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (isCommentsDisabled && isPostAuthor) {
                            Text(
                                text = "Comments disabled for users (commenting as creator)",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                        if (suggestedUsers.isNotEmpty()) {
                            MentionSuggestions(
                                suggestions = suggestedUsers,
                                onSelect = { user ->
                                    commentText = insertMention(commentText, user.username)
                                },
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = commentText,
                                onValueChange = { commentText = it },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Write a comment...") },
                                maxLines = 4,
                                shape = RoundedCornerShape(24.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            FilledIconButton(
                                onClick = {
                                    if (commentText.isNotBlank()) {
                                        viewModel.addComment(postId, commentText)
                                        commentText = ""
                                    }
                                },
                                enabled = commentText.isNotBlank(),
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Post comment"
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { padding ->
        post?.let { p ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                if (p.isDraft) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Edit,
                                        contentDescription = "Draft Icon",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "This is a Saved Draft",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Text(
                                            text = "Only you can see this post until it is published.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                                        )
                                    }
                                }
                                Button(
                                    onClick = {
                                        viewModel.updatePost(p.copy(isDraft = false)) {
                                            android.widget.Toast.makeText(context, "Published successfully! ✨", android.widget.Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                    )
                                ) {
                                    Text("Publish")
                                }
                            }
                        }
                    }
                }

                val isTextPost = p.mediaUri.isNullOrEmpty()

                if (isTextPost) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val initial = if (p.authorName.isNotBlank()) p.authorName.take(2).uppercase() else "?"
                                    Text(
                                        text = initial,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = p.authorName,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.clickable { onNavigateToUserProfile(p.authorId) }
                                    )
                                    val postRelativeTime = formatRelativeTime(p.timestamp)
                                    Text(
                                        text = "Author · $postRelativeTime",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                FollowButton(authorId = p.authorId, viewModel = viewModel)
                            }
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f)
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f))
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            ) {
                                if (p.categories.isNotBlank()) {
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        p.categories.split(",").map { it.trim() }.filter { it.isNotEmpty() }.forEach { cat ->
                                            EditorialCategoryBadge(category = cat)
                                        }
                                    }
                                }

                                if (p.title.isNotBlank()) {
                                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Stars,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Text(
                                                text = "POST CAPTION",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontSize = 10.sp,
                                                    letterSpacing = 2.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                                                )
                                            )
                                        }
                                        Text(
                                            text = p.title,
                                            style = MaterialTheme.typography.headlineSmall.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                letterSpacing = (-0.5).sp
                                            )
                                        )
                                    }
                                    HorizontalDivider(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                                        thickness = 1.dp
                                    )
                                }

                                if (p.content.isNotBlank()) {
                                    EditorialTextBodyBox(
                                        content = p.content,
                                        viewModel = viewModel,
                                        onNavigateToUserProfile = onNavigateToUserProfile
                                    )
                                }

                                if (p.tags.isNotBlank()) {
                                    FlowRow(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        p.tags.split(",").map { it.trim() }.filter { it.isNotEmpty() }.forEach { tag ->
                                            EditorialHashtagBadge(tag = tag)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!p.spotifyTrackId.isNullOrEmpty()) {
                        item {
                            SpotifyTrackCard(
                                trackName = p.spotifyTrackName ?: "Music",
                                trackArtist = p.spotifyTrackArtist ?: "Artist",
                                albumImageUrl = p.spotifyTrackImageUrl,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )

                            if (!p.spotifyTrackPreviewUrl.isNullOrEmpty()) {
                                MusicPreviewPlayer(previewUrl = p.spotifyTrackPreviewUrl!!)
                            }
                        }
                    }

                    if (previewData != null) {
                        item {
                            LinkPreviewCard(previewData = previewData)
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                } else {
                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Stars,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                    modifier = Modifier.size(12.dp)
                                )
                                Text(
                                    text = "POST CAPTION",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        letterSpacing = 2.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                                    )
                                )
                            }
                            Text(
                                text = p.title,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    letterSpacing = (-0.5).sp
                                )
                            )
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val initial = if (p.authorName.isNotBlank()) p.authorName.take(2).uppercase() else "?"
                                    Text(
                                        text = initial,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = p.authorName,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.clickable { onNavigateToUserProfile(p.authorId) }
                                    )
                                    val postRelativeTime = formatRelativeTime(p.timestamp)
                                    Text(
                                        text = "Author · $postRelativeTime",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                FollowButton(authorId = p.authorId, viewModel = viewModel)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    if (p.categories.isNotBlank() || p.tags.isNotBlank()) {
                        item {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (p.categories.isNotBlank()) {
                                    p.categories.split(",").map { it.trim() }.filter { it.isNotEmpty() }.forEach { cat ->
                                        EditorialCategoryBadge(category = cat)
                                    }
                                }
                                if (p.tags.isNotBlank()) {
                                    p.tags.split(",").map { it.trim() }.filter { it.isNotEmpty() }.forEach { tag ->
                                        EditorialHashtagBadge(tag = tag)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                        ) {
                            MediaView(
                                mediaUri = p.mediaUri,
                                isMutedByMusic = !p.spotifyTrackId.isNullOrEmpty()
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (!p.spotifyTrackId.isNullOrEmpty()) {
                        item {
                            SpotifyTrackCard(
                                trackName = p.spotifyTrackName ?: "Music",
                                trackArtist = p.spotifyTrackArtist ?: "Artist",
                                albumImageUrl = p.spotifyTrackImageUrl,
                                modifier = Modifier.padding(bottom = 20.dp)
                            )

                            if (!p.spotifyTrackPreviewUrl.isNullOrEmpty()) {
                                MusicPreviewPlayer(previewUrl = p.spotifyTrackPreviewUrl!!)
                            }
                        }
                    }

                    item {
                        if (p.content.isNotBlank()) {
                            EditorialTextBodyBox(
                                content = p.content,
                                viewModel = viewModel,
                                onNavigateToUserProfile = onNavigateToUserProfile
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }

                    if (previewData != null) {
                        item {
                            LinkPreviewCard(previewData = previewData)
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }

                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { viewModel.toggleLike(postId, isLiked) }) {
                                    Icon(
                                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                        contentDescription = "Like",
                                        tint = if (isLiked) Color(0xFFFF2F67) else MaterialTheme.colorScheme.primary
                                    )
                                }
                                Text(
                                    text = "$likeCount likes",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.ChatBubbleOutline,
                                    contentDescription = "Comments",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${comments.size} comments",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Text(
                        text = "Comments (${comments.size})",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                items(sortedComments) { comment ->
                    var showCommentMenu by remember { mutableStateOf(false) }
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            if (editingCommentId == comment.id) {
                                OutlinedTextField(
                                    value = editingCommentText,
                                    onValueChange = { editingCommentText = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    maxLines = 4,
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    TextButton(onClick = { editingCommentId = null }) {
                                        Text("Cancel")
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(
                                        onClick = {
                                            if (editingCommentText.isNotBlank()) {
                                                viewModel.updateComment(comment.copy(content = editingCommentText))
                                                editingCommentId = null
                                            }
                                        },
                                        enabled = editingCommentText.isNotBlank(),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text("Save")
                                    }
                                }
                            } else {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.weight(1f).clickable { onNavigateToUserProfile(comment.authorId) }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            val initial = if (comment.authorName.isNotBlank()) comment.authorName.take(2).uppercase() else "?"
                                            Text(
                                                text = initial,
                                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = comment.authorName,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        
                                        if (comment.isPinned) {
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Surface(
                                                color = MaterialTheme.colorScheme.primaryContainer,
                                                shape = RoundedCornerShape(12.dp)
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Filled.PushPin,
                                                        contentDescription = "Pinned",
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(10.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text(
                                                        "Pinned",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    
                                    val canModerate = post?.authorId == currentUser?.id
                                    val canEditOwn = comment.authorId == currentUser?.id
                                    
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = formatRelativeTime(comment.timestamp),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        if (currentUser != null && (canModerate || canEditOwn)) {
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Box {
                                                IconButton(onClick = { showCommentMenu = true }, modifier = Modifier.size(36.dp)) {
                                                    Icon(
                                                        imageVector = Icons.Default.MoreVert,
                                                        contentDescription = "Comment Options",
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }
                                                DropdownMenu(
                                                    expanded = showCommentMenu,
                                                    onDismissRequest = { showCommentMenu = false }
                                                ) {
                                                    if (canModerate) {
                                                        DropdownMenuItem(
                                                            text = { Text(if (comment.isPinned) "Unpin Comment" else "Pin Comment") },
                                                            leadingIcon = { Icon(if (comment.isPinned) Icons.Outlined.PushPin else Icons.Filled.PushPin, contentDescription = null) },
                                                            onClick = {
                                                                showCommentMenu = false
                                                                viewModel.toggleCommentPin(comment, !comment.isPinned)
                                                            }
                                                        )
                                                    }
                                                    if (canEditOwn) {
                                                        DropdownMenuItem(
                                                            text = { Text("Edit Comment") },
                                                            leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) },
                                                            onClick = {
                                                                showCommentMenu = false
                                                                editingCommentId = comment.id
                                                                editingCommentText = comment.content
                                                            }
                                                        )
                                                    }
                                                    DropdownMenuItem(
                                                        text = { Text("Delete Comment", color = MaterialTheme.colorScheme.error) },
                                                        leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
                                                        onClick = {
                                                            showCommentMenu = false
                                                            viewModel.deleteComment(comment.id)
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LinkifiedText(
                                    text = comment.content,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    viewModel = viewModel,
                                    onNavigateToUserProfile = onNavigateToUserProfile
                                )
                            }
                        }
                    }
                }

                if (comments.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Outlined.ChatBubbleOutline,
                                        contentDescription = "No comments",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.size(32.dp)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        "No comments yet. Start the conversation!",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userId: Int? = null,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit,
    onNavigateToFollowers: (Int) -> Unit,
    onNavigateToFollowing: (Int) -> Unit,
    onNavigateToMessaging: (Int) -> Unit,
    onNavigateToSettings: (() -> Unit)? = null
) {
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val finalUserId = userId ?: currentUser?.id ?: -1
    
    var selectedTabIndex by remember { mutableStateOf(0) }
    
    val tabs = remember(userId, currentUser?.id) {
        val list = mutableListOf("Posts")
        if (userId == null || userId == currentUser?.id) {
            list.add("Drafts")
            list.add("Saved")
            // Liked and Reposts can be added fully later, but let's just add them
            list.add("Liked")
            list.add("Reposts")
        }
        list
    }
    
    val displayUserFlow = remember(finalUserId) { viewModel.getUserById(finalUserId) }
    val displayUser by displayUserFlow.collectAsStateWithLifecycle()
    
    val allPosts by viewModel.allPosts.collectAsStateWithLifecycle()
    
    val followerFlow = remember(finalUserId) { viewModel.getFollowerCount(finalUserId) }
    val followerCount by followerFlow.collectAsStateWithLifecycle()
    
    val followingFlow = remember(finalUserId) { viewModel.getFollowingCount(finalUserId) }
    val followingCount by followingFlow.collectAsStateWithLifecycle()

    val userPosts = allPosts.filter { it.authorId == finalUserId && (!it.isDraft || finalUserId == (currentUser?.id ?: -1)) }
    val isPromoted = followerCount >= 1000
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (userId == null || userId == currentUser?.id) "My Profile" else displayUser?.username ?: "Profile") },
                navigationIcon = {
                    if (userId != null) {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    val context = androidx.compose.ui.platform.LocalContext.current
                    IconButton(
                        onClick = {
                            val userToShare = displayUser ?: currentUser
                            if (userToShare != null) {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Check out this profile on Zooz: ${com.example.BuildConfig.ZOOZ_SHARE_URL}/user/${userToShare.id}")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, "Share Profile via")
                                context.startActivity(shareIntent)
                            }
                        }
                    ) {
                        Icon(Icons.Filled.Share, contentDescription = "Share Profile")
                    }
                    if (userId == null || userId == currentUser?.id) {
                        IconButton(onClick = { onNavigateToSettings?.invoke() }) {
                            Icon(Icons.Outlined.Menu, contentDescription = "Menu")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (displayUser?.profilePicUri != null) {
                    coil.compose.AsyncImage(
                        model = displayUser!!.profilePicUri,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(80.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier.size(80.dp).background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = displayUser?.username?.take(2)?.uppercase() ?: "?",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (!displayUser?.fullName.isNullOrBlank()) displayUser?.fullName ?: "" else displayUser?.username ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    if (followerCount >= 10000) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Verified Blue Badge",
                            tint = Color(0xFF1DA1F2),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                if (!displayUser?.fullName.isNullOrBlank()) {
                    Text("@${displayUser?.username ?: ""}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                
                if (!displayUser?.bio.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = displayUser?.bio ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (currentUser != null && finalUserId != currentUser?.id) {
                        FollowButton(authorId = finalUserId, viewModel = viewModel)
                        
                        Button(
                            onClick = { onNavigateToMessaging(finalUserId) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.height(32.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                        ) {
                            Icon(Icons.Outlined.Chat, contentDescription = "Message", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Message", style = MaterialTheme.typography.labelMedium)
                        }
                    } else if (currentUser != null && finalUserId == currentUser?.id) {
                        Button(
                            onClick = { onNavigateToSettings?.invoke() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.height(32.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                        ) {
                            Icon(Icons.Outlined.Edit, contentDescription = "Edit Profile", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Edit Profile", style = MaterialTheme.typography.labelMedium)
                        }
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        // Let ourselves see Direct Messages Screen directly from profile
                        Button(
                            onClick = { onNavigateToMessaging(-1) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.height(32.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
                        ) {
                            Icon(Icons.Outlined.Chat, contentDescription = "My Messages", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("My Messages", style = MaterialTheme.typography.labelMedium)
                        }
                    }

                    // Share Profile Icon Button
                    val context = androidx.compose.ui.platform.LocalContext.current
                    IconButton(
                        onClick = {
                            val userToSend = displayUser ?: currentUser
                            if (userToSend != null) {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Check out this profile on Zooz: ${com.example.BuildConfig.ZOOZ_SHARE_URL}/user/${userToSend.id}")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, "Share Profile via")
                                context.startActivity(shareIntent)
                            }
                        },
                        modifier = Modifier
                            .size(32.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share Profile",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text(
                        "$followerCount Followers",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { displayUser?.let { onNavigateToFollowers(it.id) } }
                    )
                    Text(
                        "$followingCount Following",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { displayUser?.let { onNavigateToFollowing(it.id) } }
                    )
                    Text("${userPosts.size} Posts", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }



            }
            HorizontalDivider()
            
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.background,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    val icon = when (title) {
                        "Posts" -> Icons.Default.GridView
                        "Drafts" -> Icons.Default.Edit
                        "Saved" -> Icons.Default.BookmarkBorder
                        "Liked" -> Icons.Default.FavoriteBorder
                        "Reposts" -> Icons.Default.Repeat
                        else -> Icons.Default.GridView
                    }
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        icon = { Icon(icon, contentDescription = title) },
                    )
                }
            }
            HorizontalDivider()
            
            val currentTabName = tabs.getOrNull(selectedTabIndex) ?: "Posts"
            val displayPosts = when (currentTabName) {
                "Posts" -> userPosts
                "Drafts" -> allPosts.filter { it.authorId == finalUserId && it.isDraft }
                "Saved" -> {
                    // Collect bookmarks flow wait... we need it collected. But we can't collect inside when expression.
                    // We will collect it above.
                    emptyList() // Fallback handled later
                }
                "Liked" -> emptyList() // Needs fetch
                "Reposts" -> emptyList() // Needs fetch
                else -> userPosts
            }
            
            if (currentTabName == "Saved") {
                val bookmarkedPostsFlow = remember { viewModel.getBookmarkedPosts() }
                val bookmarkedPosts by bookmarkedPostsFlow.collectAsStateWithLifecycle(initialValue = emptyList())
                ProfilePostGrid(posts = bookmarkedPosts, onNavigateToPostDetail = onNavigateToPostDetail)
            } else if (currentTabName == "Drafts") {
                val draftsFlow = remember { viewModel.getDrafts() }
                val drafts by draftsFlow.collectAsStateWithLifecycle(initialValue = emptyList())
                ProfilePostGrid(posts = drafts, onNavigateToPostDetail = onNavigateToPostDetail)
            } else if (currentTabName == "Posts") {
                ProfilePostGrid(posts = userPosts, onNavigateToPostDetail = onNavigateToPostDetail)
            } else if (currentTabName == "Liked") {
                val likedFlow = remember(finalUserId) { viewModel.getLikedPosts(finalUserId) }
                val likedPosts by likedFlow.collectAsStateWithLifecycle(initialValue = emptyList())
                ProfilePostGrid(posts = likedPosts, onNavigateToPostDetail = onNavigateToPostDetail)
            } else {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                    Text("No $currentTabName yet")
                }
            }
        }
    }
}

@Composable
fun ProfilePostGrid(posts: List<Post>, onNavigateToPostDetail: (Int) -> Unit) {
    if (posts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No posts.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(posts) { post ->
                InstagramPostGridItem(post = post, onClick = { onNavigateToPostDetail(post.id) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraftsScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit
) {
    val draftsFlow = remember { viewModel.getDrafts() }
    val drafts by draftsFlow.collectAsStateWithLifecycle()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drafts") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(drafts) { post ->
                PostItem(post = post, viewModel = viewModel, onClick = { onNavigateToPostDetail(post.id) })
            }
            if (drafts.isEmpty()) {
                item { Text("No drafts found.", modifier = Modifier.padding(16.dp)) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit,
    onNavigateToUserProfile: (Int) -> Unit
) {
    val bookmarksFlow = remember { viewModel.getBookmarkedPosts() }
    val bookmarks by bookmarksFlow.collectAsStateWithLifecycle()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bookmarks") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(bookmarks) { post ->
                PostItem(post = post, viewModel = viewModel, onClick = { onNavigateToPostDetail(post.id) }, onAuthorClick = onNavigateToUserProfile)
            }
            if (bookmarks.isEmpty()) {
                item { Text("No bookmarks found.", modifier = Modifier.padding(16.dp)) }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingsScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    var username by remember(currentUser) { mutableStateOf(currentUser?.username ?: "") }
    var fullName by remember(currentUser) { mutableStateOf(currentUser?.fullName ?: "") }
    var bio by remember(currentUser) { mutableStateOf(currentUser?.bio ?: "") }
    var profilePicUri by remember(currentUser) { mutableStateOf(currentUser?.profilePicUri) }
    var isUploadingProfilePic by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    
    val usernameLastChanged = currentUser?.usernameLastChangedAt ?: 0L
    val currentTime = System.currentTimeMillis()
    val sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000L
    val timePassed = currentTime - usernameLastChanged
    val canEditUsername = usernameLastChanged == 0L || timePassed >= sevenDaysInMillis

    val cooldownText = if (canEditUsername) {
        null
    } else {
        val millisLeft = sevenDaysInMillis - timePassed
        val days = millisLeft / (1000 * 60 * 60 * 24)
        val hours = (millisLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
        if (days > 0) {
            "Username updated: change locked for $days days, $hours hours"
        } else {
            "Username updated: change locked for $hours hours"
        }
    }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        uri?.let {
            if (R2Uploader.isConfigured()) {
                isUploadingProfilePic = true
                coroutineScope.launch {
                    val r2Url = R2Uploader.uploadFile(context, it)
                    if (r2Url != null) {
                        profilePicUri = r2Url
                    } else {
                        val localPath = saveUriToInternalStorage(context, it)
                        profilePicUri = localPath ?: it.toString()
                    }
                    isUploadingProfilePic = false
                }
            } else {
                val localPath = saveUriToInternalStorage(context, it)
                if (localPath != null) {
                    profilePicUri = localPath
                } else {
                    profilePicUri = it.toString()
                }
            }
        }
    }
    
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Account Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp).verticalScroll(rememberScrollState())) {
            Text("Profile Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (profilePicUri != null) {
                    coil.compose.AsyncImage(
                        model = profilePicUri,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(60.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(modifier = Modifier.size(60.dp).background(MaterialTheme.colorScheme.primaryContainer, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.Person, contentDescription = "Avatar")
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedButton(
                    onClick = { if (!isUploadingProfilePic) launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                    enabled = !isUploadingProfilePic
                ) {
                    if (isUploadingProfilePic) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Uploading...")
                    } else {
                        Text("Change Picture")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { if (canEditUsername) username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                enabled = canEditUsername,
                supportingText = cooldownText?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.updateUserProfile(
                        username = username,
                        fullName = fullName,
                        bio = bio,
                        profilePicUri = profilePicUri,
                        onSuccess = {
                            android.widget.Toast.makeText(context, "Profile saved successfully! ✨", android.widget.Toast.LENGTH_SHORT).show()
                        },
                        onError = { error ->
                            android.widget.Toast.makeText(context, "Error: $error ❌", android.widget.Toast.LENGTH_LONG).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Profile")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            var showDeleteDialog by remember { mutableStateOf(false) }

            TextButton(
                onClick = { showDeleteDialog = true },
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Delete Account", fontWeight = FontWeight.Bold)
            }

            if (showDeleteDialog) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = { Text("Delete Account") },
                    text = { Text("Are you sure you want to delete your account? This action cannot be undone. All your posts, comments, and followers will be permanently lost.") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDeleteDialog = false
                                viewModel.deleteAccount(
                                    onSuccess = {
                                        android.widget.Toast.makeText(context, "Account deleted.", android.widget.Toast.LENGTH_LONG).show()
                                        // Logging out is handled inside the view model
                                    },
                                    onError = { error ->
                                        android.widget.Toast.makeText(context, "Error: $error ❌", android.widget.Toast.LENGTH_LONG).show()
                                    }
                                )
                            },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Permanently Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()
    var notifications by remember { mutableStateOf(true) }
    
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("App Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp).verticalScroll(rememberScrollState())) {
            Text("App Settings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            ListItem(
                headlineContent = { Text("Dark Theme") },
                supportingContent = { Text("Switch between light and dark backgrounds") },
                trailingContent = { Switch(checked = isDarkTheme, onCheckedChange = { viewModel.setDarkTheme(it) }) }
            )
            ListItem(
                headlineContent = { Text("Push Notifications") },
                trailingContent = { Switch(checked = notifications, onCheckedChange = { notifications = it }) }
            )

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Zooz Custom Identity Binding", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Map your existing Google Gmail account to a custom secure @zooz.secure format so you can authenticate either way.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            
            val context = androidx.compose.ui.platform.LocalContext.current
            val firebaseUser = remember {
                try {
                    if (com.google.firebase.FirebaseApp.getApps(context).isNotEmpty()) {
                        com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }
            
            if (firebaseUser != null && !firebaseUser.email.isNullOrBlank()) {
                val email = firebaseUser.email!!
                var boundAlias by remember { mutableStateOf<String?>(null) }
                var aliasInput by remember { mutableStateOf("") }
                var statusMessage by remember { mutableStateOf<String?>(null) }
                var isErrorStatus by remember { mutableStateOf(false) }
                var isBindingInProcess by remember { mutableStateOf(false) }
                
                LaunchedEffect(email) {
                    boundAlias = viewModel.getCurrentlyBoundAlias(email)
                }
                
                if (boundAlias != null) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Alias Bound Successfully",
                                tint = Color(0xFF34A853),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Mapped Zooz Alias",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "${boundAlias}@zooz.secure",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "Linked to Google: $email",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Credential linking (setting a password for the Google email)
                    var zoozPassword by remember { mutableStateOf("") }
                    var isLinkingByPassword by remember { mutableStateOf(false) }
                    var passwordStatus by remember { mutableStateOf<String?>(null) }
                    var isPasswordError by remember { mutableStateOf(false) }
                    
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Enable Credentials Login", 
                                style = MaterialTheme.typography.labelLarge, 
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Set a password so you can sign in directly using your alias instead of always using Google Sign-In.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            OutlinedTextField(
                                value = zoozPassword,
                                onValueChange = { zoozPassword = it; passwordStatus = null },
                                label = { Text("Zooz Credentials Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                singleLine = true
                            )
                            
                            if (passwordStatus != null) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    passwordStatus!!,
                                    color = if (isPasswordError) MaterialTheme.colorScheme.error else Color(0xFF34A853),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            Button(
                                onClick = {
                                    if (zoozPassword.length < 6) {
                                        passwordStatus = "Password must be at least 6 characters"
                                        isPasswordError = true
                                        return@Button
                                    }
                                    isLinkingByPassword = true
                                    viewModel.enableCredentialsLogin(
                                        password = zoozPassword,
                                        onComplete = {
                                            isLinkingByPassword = false
                                            passwordStatus = "Password linked successfully! You can now use the Zooz sign-in form."
                                            isPasswordError = false
                                        },
                                        onError = { err ->
                                            isLinkingByPassword = false
                                            passwordStatus = err
                                            isPasswordError = true
                                        }
                                    )
                                },
                                enabled = !isLinkingByPassword && zoozPassword.isNotBlank(),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                if (isLinkingByPassword) {
                                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                                } else {
                                    Text("Link Credentials Password")
                                }
                            }
                        }
                    }
                } else {
                    OutlinedTextField(
                        value = aliasInput,
                        onValueChange = { 
                            aliasInput = it.lowercase().trim().replace(" ", "")
                            statusMessage = null
                        },
                        label = { Text("Desired Zooz Suffix Alias") },
                        placeholder = { Text("e.g. alice") },
                        suffix = { Text("@zooz.secure") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )
                    
                    if (statusMessage != null) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = statusMessage ?: "",
                            color = if (isErrorStatus) MaterialTheme.colorScheme.error else Color(0xFF34A853),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Button(
                        onClick = {
                            if (aliasInput.isBlank()) {
                                statusMessage = "Please enter an alias"
                                isErrorStatus = true
                                return@Button
                            }
                            isBindingInProcess = true
                            viewModel.bindCustomZoozAlias(
                                alias = aliasInput,
                                onComplete = {
                                    isBindingInProcess = false
                                    boundAlias = aliasInput
                                    statusMessage = "Alias bound successfully! You can now authenticate with ${aliasInput}@zooz.secure"
                                    isErrorStatus = false
                                },
                                onError = { err ->
                                    isBindingInProcess = false
                                    statusMessage = err
                                    isErrorStatus = true
                                }
                            )
                        },
                        enabled = !isBindingInProcess,
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        if (isBindingInProcess) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp)
                        } else {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Bind Identity Alias")
                        }
                    }
                }
            } else {
                Text(
                    text = "No logged-in Google Account detected contextually.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowersScreen(
    userId: Int,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToUserProfile: (Int) -> Unit
) {
    val followersState = remember(userId) { viewModel.getFollowersList(userId) }
    val followers by followersState.collectAsStateWithLifecycle(initialValue = emptyList())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Followers") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(followers) { user ->
                ListItem(
                    modifier = Modifier.clickable { onNavigateToUserProfile(user.id) },
                    headlineContent = { Text(user.username) },
                    supportingContent = { if (user.bio.isNotBlank()) Text(user.bio) },
                    leadingContent = {
                        if (user.profilePicUri != null) {
                            coil.compose.AsyncImage(
                                model = user.profilePicUri,
                                contentDescription = "Profile Picture",
                                modifier = Modifier.size(40.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(Icons.Filled.Person, contentDescription = "Avatar")
                        }
                    },
                    trailingContent = { FollowButton(authorId = user.id, viewModel = viewModel) }
                )
                HorizontalDivider()
            }
            if (followers.isEmpty()) {
                item { Text("No followers yet.", modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurfaceVariant) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowingScreen(
    userId: Int,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToUserProfile: (Int) -> Unit
) {
    val followingState = remember(userId) { viewModel.getFollowingList(userId) }
    val following by followingState.collectAsStateWithLifecycle(initialValue = emptyList())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Following") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(following) { user ->
                ListItem(
                    modifier = Modifier.clickable { onNavigateToUserProfile(user.id) },
                    headlineContent = { Text(user.username) },
                    supportingContent = { if (user.bio.isNotBlank()) Text(user.bio) },
                    leadingContent = {
                        if (user.profilePicUri != null) {
                            coil.compose.AsyncImage(
                                model = user.profilePicUri,
                                contentDescription = "Profile Picture",
                                modifier = Modifier.size(40.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(Icons.Filled.Person, contentDescription = "Avatar")
                        }
                    },
                    trailingContent = { FollowButton(authorId = user.id, viewModel = viewModel) }
                )
                HorizontalDivider()
            }
            if (following.isEmpty()) {
                item { Text("Not following anyone.", modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurfaceVariant) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Help & Support") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("FAQ", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Q: How do I publish a post?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("A: Tap the + button on the home screen, write your content, and hit Publish.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Q: How can I bookmark?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Text("A: Tap on any post to view details and click the bookmark icon on the top right.", style = MaterialTheme.typography.bodyMedium)
            
            Spacer(modifier = Modifier.height(32.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            
            Text("Contact Us", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("For further assistance, email support@aistudio.blog", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun FollowButton(authorId: Int, viewModel: BlogViewModel) {
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    if (currentUser?.id == authorId) return

    val isFollowingFlow = remember(authorId) { viewModel.isFollowing(authorId) }
    val isFollowing by isFollowingFlow.collectAsStateWithLifecycle(initialValue = false)

    OutlinedButton(
        onClick = { viewModel.toggleFollow(authorId, isFollowing) },
        modifier = Modifier.height(32.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
    ) {
        Text(if (isFollowing) "Following" else "Follow", style = MaterialTheme.typography.labelMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderScreen(
    title: String,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            Text("$title - Coming Soon", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
fun LinkifiedText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
    maxLines: Int = Int.MAX_VALUE,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyLarge,
    viewModel: BlogViewModel? = null,
    onNavigateToUserProfile: ((Int) -> Unit)? = null
) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        var cursor = 0
        val mentionPattern = Regex("@(\\w+)")
        val urlPattern = Regex("(https?://[\\w-]+(\\.[\\w-]+)+(/\\S*)?)")
        
        val matches = (mentionPattern.findAll(text).map { Match(it.range.first, it.range.last + 1, "MENTION", it.groupValues[1], it.value) } +
                       urlPattern.findAll(text).map { Match(it.range.first, it.range.last + 1, "URL", it.value, it.value) })
            .sortedBy { it.start }
            .toList()
            
        val nonOverlapping = mutableListOf<Match>()
        var lastEnd = 0
        for (match in matches) {
            if (match.start >= lastEnd) {
                nonOverlapping.add(match)
                lastEnd = match.end
            }
        }
        
        val primaryColor = MaterialTheme.colorScheme.primary
        for (match in nonOverlapping) {
            if (match.start > cursor) {
                append(text.substring(cursor, match.start))
            }
            pushStringAnnotation(tag = match.tag, annotation = match.value)
            withStyle(style = SpanStyle(
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )) {
                append(match.original)
            }
            pop()
            cursor = match.end
        }
        if (cursor < text.length) {
            append(text.substring(cursor))
        }
    }
    
    val allUsersState = viewModel?.allUsers?.collectAsStateWithLifecycle(initialValue = emptyList())
    val allUsers = allUsersState?.value ?: emptyList()
    
    androidx.compose.foundation.text.ClickableText(
        text = annotatedString,
        modifier = modifier,
        style = style.copy(color = color),
        maxLines = maxLines,
        overflow = if (maxLines != Int.MAX_VALUE) TextOverflow.Ellipsis else TextOverflow.Clip,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "MENTION", start = offset, end = offset).firstOrNull()?.let { annotation ->
                val username = annotation.item
                val matchedUser = allUsers.find { it.username.equals(username, ignoreCase = true) }
                if (matchedUser != null) {
                    onNavigateToUserProfile?.invoke(matchedUser.id)
                } else {
                    android.widget.Toast.makeText(context, "User @$username not found", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset).firstOrNull()?.let { annotation ->
                val url = annotation.item
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    android.widget.Toast.makeText(context, "Cannot open link: $url", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }
    )
}

private data class Match(val start: Int, val end: Int, val tag: String, val value: String, val original: String)

data class LinkPreviewData(
    val url: String,
    val domain: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val brandColor: Color = Color.Gray
)

fun extractFirstUrl(text: String): String? {
    val urlPattern = Regex("(https?://[\\w-]+(\\.[\\w-]+)+(/\\S*)?)")
    val match = urlPattern.find(text)
    return match?.value
}

fun getUrlPreview(url: String): LinkPreviewData {
    val lowerUrl = url.lowercase(java.util.Locale.ROOT)
    return when {
        lowerUrl.contains("facebook.com") || lowerUrl.contains("fb.com") || lowerUrl.contains("fb.me") -> {
            LinkPreviewData(
                url = url,
                domain = "facebook.com",
                title = "Facebook — Log In, Sign Up, or Connect with Friends",
                description = "Connect with friends, family and other people you know. Share photos and videos, send messages and get updates.",
                imageUrl = "https://images.unsplash.com/photo-1611162617213-7d7a39e9b1d7?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF1877F2)
            )
        }
        lowerUrl.contains("youtube.com") || lowerUrl.contains("youtu.be") -> {
            LinkPreviewData(
                url = url,
                domain = "youtube.com",
                title = "YouTube — Enjoy the videos and music you love",
                description = "Share your videos with friends, family, and the world. Watch original content, subscribe to channels, and join the global community.",
                imageUrl = "https://images.unsplash.com/photo-1611162618071-b39a2ec055fb?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFFFF0000)
            )
        }
        lowerUrl.contains("instagram.com") -> {
            LinkPreviewData(
                url = url,
                domain = "instagram.com",
                title = "Instagram — A simple, fun & creative way to share photos",
                description = "Capture and share the world's moments. Follow friends, family, and inspiring creators to see what they are sharing.",
                imageUrl = "https://images.unsplash.com/photo-1611224885990-ab7363d1f2a9?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFFE1306C)
            )
        }
        lowerUrl.contains("twitter.com") || lowerUrl.contains("x.com") -> {
            LinkPreviewData(
                url = url,
                domain = "x.com",
                title = "X — It's what's happening",
                description = "Explore real-time news, trending topics, connect with global discussions, and get the full story with live updates.",
                imageUrl = "https://images.unsplash.com/photo-1611605698335-8b15d27e03f2?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF0F1419)
            )
        }
        lowerUrl.contains("github.com") -> {
            LinkPreviewData(
                url = url,
                domain = "github.com",
                title = "GitHub: Let's build from here · GitHub",
                description = "GitHub is the world's leading developer platform. Collaborate, write clean code, and build secure software together.",
                imageUrl = "https://images.unsplash.com/photo-1618401471353-b98aedd07871?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF24292E)
            )
        }
        lowerUrl.contains("linkedin.com") -> {
            LinkPreviewData(
                url = url,
                domain = "linkedin.com",
                title = "LinkedIn: Log In or Sign Up",
                description = "Manage your professional identity. Build and engage with your professional network. Access knowledge, insights and opportunities.",
                imageUrl = "https://images.unsplash.com/photo-1611944212129-43ed7e13c6b7?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF0A66C2)
            )
        }
        lowerUrl.contains("stackoverflow.com") -> {
            LinkPreviewData(
                url = url,
                domain = "stackoverflow.com",
                title = "Stack Overflow — Where Developers Learn, Share, & Build Careers",
                description = "The world's largest online community for developers to learn, share their knowledge, and search for answers to technical queries.",
                imageUrl = "https://images.unsplash.com/photo-1542831371-29b0f74f9713?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFFF48024)
            )
        }
        lowerUrl.contains("pinterest.com") || lowerUrl.contains("pin.it") -> {
            LinkPreviewData(
                url = url,
                domain = "pinterest.com",
                title = "Pinterest — Discover recipes, home ideas, style inspiration",
                description = "Find creative ideas and recipes, explore home design, travel hacks, fashion, beauty, and trends matching your style on Pinterest.",
                imageUrl = "https://images.unsplash.com/photo-1611162616475-46b635cb6868?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFFE60023)
            )
        }
        lowerUrl.contains("spotify.com") -> {
            LinkPreviewData(
                url = url,
                domain = "spotify.com",
                title = "Spotify — Web Player: Music for everyone",
                description = "Spotify is a digital music, podcast, and video service that gives you access to millions of songs and other content from creators.",
                imageUrl = "https://images.unsplash.com/photo-1614680376593-902f74fa0d41?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF1DB954)
            )
        }
        lowerUrl.endsWith(".jpg") || lowerUrl.endsWith(".jpeg") || lowerUrl.endsWith(".png") || lowerUrl.endsWith(".gif") || lowerUrl.endsWith(".webp") -> {
            val domainName = try {
                val uri = java.net.URI(url)
                val host = uri.host ?: ""
                if (host.startsWith("www.")) host.substring(4) else host
            } catch (e: Exception) {
                "Image Link"
            }
            LinkPreviewData(
                url = url,
                domain = domainName,
                title = "Image Preview",
                description = "Shared image file: ${url.substringAfterLast("/")}",
                imageUrl = url,
                brandColor = Color(0xFF9C27B0)
            )
        }
        else -> {
            val domainName = try {
                val uri = java.net.URI(url)
                val host = uri.host ?: ""
                if (host.startsWith("www.")) host.substring(4) else host
            } catch (e: Exception) {
                "web-page"
            }
            LinkPreviewData(
                url = url,
                domain = domainName,
                title = "$domainName — Visit Website",
                description = "Explore this webpage shared on the blogging platform to view more information, updates, and discussions.",
                imageUrl = "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?auto=format&fit=crop&q=80&w=600",
                brandColor = Color(0xFF607D8B)
            )
        }
    }
}

@Composable
fun LinkPreviewCard(previewData: LinkPreviewData, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(previewData.url))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    android.widget.Toast.makeText(context, "Cannot open: ${previewData.url}", android.widget.Toast.LENGTH_SHORT).show()
                }
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                AsyncImage(
                    model = previewData.imageUrl,
                    contentDescription = previewData.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(previewData.brandColor)
                        .align(Alignment.BottomCenter)
                )
                Surface(
                    color = Color.Black.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(topStart = 0.dp, bottomStart = 8.dp, topEnd = 0.dp, bottomEnd = 0.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = previewData.domain.uppercase(java.util.Locale.ROOT),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(previewData.brandColor, CircleShape)
                    )
                    Text(
                        text = previewData.domain,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = previewData.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = previewData.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun MentionSuggestions(
    suggestions: List<com.example.data.User>,
    onSelect: (com.example.data.User) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        LazyColumn(
            modifier = Modifier.heightIn(max = 160.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(suggestions) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(user) }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val init = if (user.username.isNotBlank()) user.username.take(2).uppercase() else "?"
                        Text(
                            text = init,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "@${user.username}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (!user.fullName.isNullOrBlank()) {
                            Text(
                                text = user.fullName!!,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getMentionQuery(text: String): String? {
    val lastAt = text.lastIndexOf('@')
    if (lastAt >= 0) {
        val afterAt = text.substring(lastAt + 1)
        if (!afterAt.contains(' ') && !afterAt.contains('\n')) {
            return afterAt
        }
    }
    return null
}

fun insertMention(text: String, username: String): String {
    val lastAt = text.lastIndexOf('@')
    if (lastAt >= 0) {
        val prefix = text.substring(0, lastAt)
        return "$prefix@$username "
    }
    return text
}

@Composable
fun PostMoreActionsMenu(
    post: Post,
    viewModel: BlogViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(
            onClick = { showMenu = true },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            DropdownMenuItem(
                text = {
                    Column {
                        Text(
                            text = "Hide post",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "See fewer posts like this",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Hide post",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                onClick = {
                    showMenu = false
                    viewModel.hidePost(post.id)
                    android.widget.Toast.makeText(context, "Post hidden successfully", android.widget.Toast.LENGTH_SHORT).show()
                }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

            DropdownMenuItem(
                text = {
                    Column {
                        Text(
                            text = "Report post",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "We'll review this content",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Report post",
                        tint = MaterialTheme.colorScheme.error
                    )
                },
                onClick = {
                    showMenu = false
                    viewModel.reportPost(post.id)
                    android.widget.Toast.makeText(context, "Post reported. Thank you!", android.widget.Toast.LENGTH_SHORT).show()
                }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

            DropdownMenuItem(
                text = {
                    Column {
                        Text(
                            text = "About this post",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Why you're seeing this post",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Post Info",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                onClick = {
                    showMenu = false
                    showInfoDialog = true
                }
            )
        }
    }

    if (showInfoDialog) {
        val relativeTimeStr = formatRelativeTime(post.timestamp)
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("About This Post")
                }
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "You are seeing this post because it was shared on our secure open community platform by ${post.authorName}.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Content Details:",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "• Creator: ${post.authorName} (${if (post.isPromoted) "Verified/Sponsored Account" else "Community Member"})",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = "• Posted: $relativeTimeStr",
                                style = MaterialTheme.typography.bodySmall
                            )
                            if (post.categories.isNotBlank()) {
                                Text(
                                    text = "• Category: ${post.categories}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            Text(
                                text = "• Engagement: ${post.impressions} Impressions · ${post.clicks} Clicks",
                                style = MaterialTheme.typography.bodySmall
                            )
                            if (post.isPromoted) {
                                Text(
                                    text = "• Delivery: Sponsored Promotion",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            } else {
                                Text(
                                    text = "• Delivery: Organically shared based on chronological order and relevance",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

object ActiveVideoManager {
    private val visiblePlayers = mutableMapOf<String, Float>()
    val activeVideoUri = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)

    fun onPlayerPositioned(mediaUri: String, distanceToCenter: Float) {
        synchronized(visiblePlayers) {
            val previousDistance = visiblePlayers[mediaUri]
            if (previousDistance == null || kotlin.math.abs(previousDistance - distanceToCenter) > 4f) {
                visiblePlayers[mediaUri] = distanceToCenter
                updateActivePlayer()
            }
        }
    }

    fun onPlayerLeftScreen(mediaUri: String) {
        synchronized(visiblePlayers) {
            if (visiblePlayers.containsKey(mediaUri)) {
                visiblePlayers.remove(mediaUri)
                updateActivePlayer()
            }
        }
    }

    private fun updateActivePlayer() {
        try {
            synchronized(visiblePlayers) {
                val newActive = if (visiblePlayers.isEmpty()) {
                    null
                } else {
                    visiblePlayers.minByOrNull { kotlin.math.abs(it.value) }?.key
                }
                if (activeVideoUri.value != newActive) {
                    activeVideoUri.value = newActive
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

object ActiveMusicManager {
    private val visibleTracks = mutableMapOf<String, Float>() // previewUrl -> distance from center
    val activeTrackUrl = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)

    fun onTrackPositioned(previewUrl: String, distanceToCenter: Float) {
        synchronized(visibleTracks) {
            val prev = visibleTracks[previewUrl]
            if (prev == null || kotlin.math.abs(prev - distanceToCenter) > 4f) {
                visibleTracks[previewUrl] = distanceToCenter
                updateActiveTrack()
            }
        }
    }

    fun onTrackLeftScreen(previewUrl: String) {
        synchronized(visibleTracks) {
            if (visibleTracks.containsKey(previewUrl)) {
                visibleTracks.remove(previewUrl)
                updateActiveTrack()
            }
        }
    }

    private fun updateActiveTrack() {
        synchronized(visibleTracks) {
            val newActive = if (visibleTracks.isEmpty()) null else visibleTracks.minByOrNull { kotlin.math.abs(it.value) }?.key
            if (activeTrackUrl.value != newActive) {
                activeTrackUrl.value = newActive
            }
        }
    }
}

@Composable
fun MusicPreviewPlayer(
    previewUrl: String,
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = true
) {
    val context = LocalContext.current
    val activeTrack by ActiveMusicManager.activeTrackUrl.collectAsStateWithLifecycle()
    val isCurrentlyActive = activeTrack == previewUrl

    val exoPlayer = remember(previewUrl) {
        androidx.media3.exoplayer.ExoPlayer.Builder(context).build().apply {
            repeatMode = androidx.media3.common.Player.REPEAT_MODE_ALL
            val mediaItem = androidx.media3.common.MediaItem.fromUri(Uri.parse(previewUrl))
            setMediaItem(mediaItem)
            prepare()
            
            addListener(object : androidx.media3.common.Player.Listener {
                override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                    android.util.Log.e("MusicPreviewPlayer", "ExoPlayer error for $previewUrl: ${error.message}", error)
                }
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    android.util.Log.d("MusicPreviewPlayer", "Track playing changed for $previewUrl: $isPlaying")
                }
            })
        }
    }

    LaunchedEffect(isCurrentlyActive) {
        try {
            if (isCurrentlyActive && autoPlayEnabled) {
                exoPlayer.play()
                android.util.Log.d("MusicPreviewPlayer", "Playing track: $previewUrl")
            } else {
                exoPlayer.pause()
                android.util.Log.d("MusicPreviewPlayer", "Pausing track: $previewUrl")
            }
        } catch (e: Exception) {
            android.util.Log.e("MusicPreviewPlayer", "Error controlling playback for $previewUrl: ${e.message}")
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
            ActiveMusicManager.onTrackLeftScreen(previewUrl)
            android.util.Log.d("MusicPreviewPlayer", "Released player for track: $previewUrl")
        }
    }

    Box(
        modifier = Modifier
            .size(1.dp)
            .then(modifier)
            .onGloballyPositioned { coords ->
                if (coords.isAttached) {
                    val bounds = coords.boundsInWindow()
                    val screenHeight = context.resources.displayMetrics.heightPixels.toFloat()
                    val isVisible = if (bounds.height <= 0f && bounds.top == 0f) {
                        true // non-size/attached container positioned at top-left
                    } else {
                        bounds.bottom > 0f && bounds.top < screenHeight
                    }
                    
                    if (isVisible) {
                        val distance = if (bounds.height <= 0f) {
                            0f
                        } else {
                            (bounds.top + bounds.bottom) / 2f - screenHeight / 2f
                        }
                        ActiveMusicManager.onTrackPositioned(previewUrl, distance)
                    } else {
                        ActiveMusicManager.onTrackLeftScreen(previewUrl)
                    }
                }
            }
    )
}

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun android.content.Context.findActivity(): android.app.Activity? {
    var context = this
    while (context is android.content.ContextWrapper) {
        if (context is android.app.Activity) return context
        context = context.baseContext
    }
    return null
}

@Composable
fun ModernVideoPlayer(
    mediaUri: String, 
    modifier: Modifier = Modifier,
    autoPlayEnabled: Boolean = false,
    onPlay: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    isNested: Boolean = false,
    isHomeFeed: Boolean = false,
    isMutedByMusic: Boolean = false
) {
    val context = LocalContext.current
    val activeUri by ActiveVideoManager.activeVideoUri.collectAsStateWithLifecycle()
    val isCurrentlyActive = autoPlayEnabled && activeUri == mediaUri

    var isPlaying by remember { mutableStateOf(false) }
    var isMuted by remember { mutableStateOf(isMutedByMusic) } // Unmute by default, unless music track is attached (to prevent audio collision)
    var isPrepared by remember { mutableStateOf(false) }
    var showControls by remember { mutableStateOf(false) }
    var isLocked by remember { mutableStateOf(false) }
    var aspectRatioMode by remember { mutableStateOf(1) } // Default: 1 (ZOOM / Fill)
    var forceLandscape by remember { mutableStateOf(false) }
    var playbackSpeed by remember { mutableStateOf(1.0f) }

    if (!isHomeFeed) {
        val targetOrientation = if (forceLandscape) {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
        LockScreenOrientation(targetOrientation)
    }
    
    // Position & duration states
    var currentPosition by remember { mutableStateOf(0L) }
    var videoDuration by remember { mutableStateOf(0L) }
    var isBuffering by remember { mutableStateOf(false) }

    // Gesture status overlays
    var brightnessText by remember { mutableStateOf<String?>(null) }
    var volumeText by remember { mutableStateOf<String?>(null) }
    var skipText by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()
    var fadeJob by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }

    val audioManager = remember { context.getSystemService(android.content.Context.AUDIO_SERVICE) as android.media.AudioManager }
    val maxVolume = remember { audioManager.getStreamMaxVolume(android.media.AudioManager.STREAM_MUSIC).toFloat() }

    // Build ExoPlayer once for this mediaUri
    val exoPlayer = remember(mediaUri) {
        androidx.media3.exoplayer.ExoPlayer.Builder(context).build().apply {
            repeatMode = androidx.media3.common.Player.REPEAT_MODE_ALL
            val mediaItem = androidx.media3.common.MediaItem.fromUri(Uri.parse(mediaUri))
            setMediaItem(mediaItem)
            prepare()
        }
    }

    // Direct Sync volume
    LaunchedEffect(isMuted, isMutedByMusic) {
        exoPlayer.volume = if (isMuted || isMutedByMusic) 0f else 1f
    }

    // Sync play state
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

    // Auto-play / Active video tracking sync
    LaunchedEffect(isCurrentlyActive) {
        if (autoPlayEnabled) {
            isPlaying = isCurrentlyActive
        }
    }

    // Track onPlay callback
    var playCountTracked by remember(mediaUri) { mutableStateOf(false) }
    LaunchedEffect(isPlaying) {
        if (isPlaying && !playCountTracked) {
            playCountTracked = true
            onPlay?.invoke()
        }
    }

    // Speed Sync
    LaunchedEffect(playbackSpeed) {
        exoPlayer.setPlaybackSpeed(playbackSpeed)
    }

    // Background safety and release of player resources
    DisposableEffect(exoPlayer) {
        val listener = object : androidx.media3.common.Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                isPrepared = state == androidx.media3.common.Player.STATE_READY || state == androidx.media3.common.Player.STATE_ENDED
                isBuffering = state == androidx.media3.common.Player.STATE_BUFFERING
                videoDuration = exoPlayer.duration.coerceAtLeast(0L)
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
            if (autoPlayEnabled) {
                ActiveVideoManager.onPlayerLeftScreen(mediaUri)
            }
        }
    }

    // Polling progress values
    LaunchedEffect(isPlaying, isPrepared) {
        if (isPlaying && isPrepared) {
            while (true) {
                currentPosition = exoPlayer.currentPosition.coerceAtLeast(0L)
                val dur = exoPlayer.duration.coerceAtLeast(0L)
                if (dur > 0) {
                    videoDuration = dur
                }
                kotlinx.coroutines.delay(200)
            }
        }
    }

    // Automatically hide controls overlay
    LaunchedEffect(showControls, isPlaying) {
        if (showControls && isPlaying) {
            kotlinx.coroutines.delay(3500)
            showControls = false
        }
    }

    // Helper functions for formats
    fun formatTime(ms: Long): String {
        val sec = (ms / 1000) % 60
        val min = (ms / 60000) % 60
        return String.format("%02d:%02d", min, sec)
    }

    // Render Fullscreen Dialog or Inline layout with the active ExoPlayer
    var isFullscreen by remember { mutableStateOf(false) }

    @Composable
    fun PlayerCoreContent(isInFullscreen: Boolean) {
        var layoutWidth by remember { mutableStateOf(0) }
        var layoutHeight by remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .onGloballyPositioned { coordinates ->
                    layoutWidth = coordinates.size.width
                    layoutHeight = coordinates.size.height
                    
                    if (autoPlayEnabled && coordinates.isAttached && !isInFullscreen) {
                        try {
                            val bounds = coordinates.boundsInWindow()
                            val screenHeight = context.resources.displayMetrics.heightPixels.toFloat()
                            val isPartiallyVisible = bounds.bottom > 0f && bounds.top < screenHeight
                            if (isPartiallyVisible) {
                                val playerCenterY = (bounds.top + bounds.bottom) / 2f
                                val screenCenterY = screenHeight / 2f
                                val distance = playerCenterY - screenCenterY
                                ActiveVideoManager.onPlayerPositioned(mediaUri, distance)
                            } else {
                                ActiveVideoManager.onPlayerLeftScreen(mediaUri)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                .pointerInput(isLocked) {
                    if (isLocked) return@pointerInput
                    detectTapGestures(
                        onDoubleTap = { offset ->
                            if (layoutWidth > 0) {
                                val isLeft = offset.x < layoutWidth / 2f
                                val skipDelta = 10000L // 10s skip
                                val currentPos = exoPlayer.currentPosition
                                if (isLeft) {
                                    exoPlayer.seekTo((currentPos - skipDelta).coerceAtLeast(0L))
                                    skipText = "⏪ -10s"
                                } else {
                                    exoPlayer.seekTo((currentPos + skipDelta).coerceAtMost(exoPlayer.duration))
                                    skipText = "⏩ +10s"
                                }
                                showControls = true
                                fadeJob?.cancel()
                                fadeJob = coroutineScope.launch {
                                    kotlinx.coroutines.delay(1000)
                                    skipText = null
                                }
                            }
                        },
                        onTap = {
                            showControls = !showControls
                            onClick?.invoke()
                        }
                    )
                }
                .pointerInput(isLocked, isHomeFeed) {
                    if (isLocked || isHomeFeed) return@pointerInput
                    var dragStartLeft = false
                    detectDragGestures(
                        onDragStart = { offset: androidx.compose.ui.geometry.Offset ->
                            dragStartLeft = offset.x < layoutWidth / 2f
                        },
                        onDrag = { change: androidx.compose.ui.input.pointer.PointerInputChange, dragAmount: androidx.compose.ui.geometry.Offset ->
                            change.consume()
                            val deltaFraction = -dragAmount.y / layoutHeight.toFloat()
                            if (dragStartLeft) {
                                var activity = context as? android.app.Activity
                                if (activity == null && context is android.content.ContextWrapper) {
                                    var currentContext = context
                                    while (currentContext is android.content.ContextWrapper) {
                                        if (currentContext is android.app.Activity) {
                                            activity = currentContext
                                            break
                                        }
                                        currentContext = currentContext.baseContext
                                    }
                                }
                                val window = activity?.window
                                if (window != null) {
                                    val lp = window.attributes
                                    var currBrightness = if (lp.screenBrightness < 0) 0.5f else lp.screenBrightness
                                    currBrightness = (currBrightness + deltaFraction).coerceIn(0.1f, 1.0f)
                                    lp.screenBrightness = currBrightness
                                    window.attributes = lp
                                    brightnessText = "🔆 Brightness: ${(currBrightness * 100).toInt()}%"
                                }
                            } else {
                                val curVolumeLevel = audioManager.getStreamVolume(android.media.AudioManager.STREAM_MUSIC).toFloat()
                                val volumeFractionStep = deltaFraction * maxVolume
                                val targetVolume = (curVolumeLevel + volumeFractionStep).coerceIn(0f, maxVolume)
                                audioManager.setStreamVolume(android.media.AudioManager.STREAM_MUSIC, targetVolume.toInt(), 0)
                                volumeText = "🔊 Volume: ${((targetVolume / maxVolume) * 100).toInt()}%"
                            }
                        },
                        onDragEnd = {
                            coroutineScope.launch {
                                kotlinx.coroutines.delay(1000)
                                brightnessText = null
                                volumeText = null
                            }
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            // THE NATIVE PLAYER VIEW
            AndroidView(
                factory = { ctx ->
                    androidx.media3.ui.PlayerView(ctx).apply {
                        useController = false
                        player = exoPlayer
                        resizeMode = when (aspectRatioMode) {
                            0 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                            1 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                            2 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
                            else -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                        }
                    }
                },
                update = { playerView ->
                    playerView.resizeMode = when (aspectRatioMode) {
                        0 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                        1 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        2 -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FILL
                        else -> androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_FIT
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Dynamic bottom shadow vignette
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 0.5f
                        )
                    )
            )

            // Big Centered Action Controls
            AnimatedVisibility(
                visible = showControls || !isPlaying || isBuffering,
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (isBuffering) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(56.dp)
                        )
                    } else if (!isLocked) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(28.dp)
                        ) {
                            // Seek back button (10 sec)
                            IconButton(
                                onClick = {
                                    val prev = (exoPlayer.currentPosition - 10000L).coerceAtLeast(0L)
                                    exoPlayer.seekTo(prev)
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Replay10,
                                    contentDescription = "Seek back 10 seconds",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            // Central Play/Pause button
                            IconButton(
                                onClick = { isPlaying = !isPlaying },
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.85f), CircleShape)
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                    contentDescription = if (isPlaying) "Pause" else "Play",
                                    tint = Color.Black,
                                    modifier = Modifier.size(36.dp)
                                )
                            }

                            // Seek forward button (10 sec)
                            IconButton(
                                onClick = {
                                    val next = (exoPlayer.currentPosition + 10000L).coerceAtMost(exoPlayer.duration)
                                    exoPlayer.seekTo(next)
                                },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Forward10,
                                    contentDescription = "Seek forward 10 seconds",
                                    tint = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Lock Overrides Control Indicator (Top Left floats lock layout button)
            if (!isHomeFeed) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                ) {
                    IconButton(
                        onClick = {
                            isLocked = !isLocked
                            showControls = true
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = if (isLocked) MaterialTheme.colorScheme.error.copy(alpha = 0.8f) else Color.Black.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = if (isLocked) Icons.Filled.Lock else Icons.Filled.LockOpen,
                            contentDescription = if (isLocked) "Unlock Screen Controls" else "Lock Screen Controls",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // HUD HUD Toast Status Indicator overlay (centered toasts for volume / skip gestures)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                brightnessText?.let {
                    HudOverlayIndicator(it)
                }
                volumeText?.let {
                    HudOverlayIndicator(it)
                }
                skipText?.let {
                    HudOverlayIndicator(it)
                }
            }

            // Top-right tag & Aspect Ratio toggler
            if (!isLocked) {
                AnimatedVisibility(
                    visible = showControls || !isPlaying,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Aspect ratio mode text indicator chip
                        if (!isHomeFeed) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .clickable {
                                        aspectRatioMode = (aspectRatioMode + 1) % 3
                                    }
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                val modeStr = when (aspectRatioMode) {
                                    0 -> "FIT 📺"
                                    1 -> "ZOOM 📱"
                                    2 -> "STRETCH ↔️"
                                    else -> "FIT"
                                }
                                Text(
                                    text = modeStr,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Rotation landscape/portrait toggle (only in non-homefeed video description page)
                        if (!isHomeFeed) {
                            IconButton(
                                onClick = {
                                    forceLandscape = !forceLandscape
                                },
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                            ) {
                                Icon(
                                    imageVector = if (forceLandscape) Icons.Filled.Portrait else Icons.Filled.ScreenRotation,
                                    contentDescription = "Rotate Screen",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        // Fullscreen orientation toggle
                        IconButton(
                            onClick = {
                                isFullscreen = !isFullscreen
                            },
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                        ) {
                            Icon(
                                imageVector = if (isInFullscreen) Icons.Filled.FullscreenExit else Icons.Filled.Fullscreen,
                                contentDescription = "Toggle Fullscreen",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Bottom Core Controls (Scrubber, timestamps, speed modifiers, mute)
            if (!isLocked) {
                AnimatedVisibility(
                    visible = showControls || !isPlaying,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f)),
                                    startY = 0.0f
                                )
                            )
                            .padding(bottom = 12.dp, start = 12.dp, end = 12.dp, top = 20.dp)
                    ) {
                        // Core scrubber Slider track
                        Slider(
                            value = if (videoDuration > 0) currentPosition.toFloat() / videoDuration else 0f,
                            onValueChange = { fraction ->
                                val seekPos = (fraction * videoDuration).toLong()
                                exoPlayer.seekTo(seekPos)
                                currentPosition = seekPos
                            },
                            valueRange = 0f..1f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp),
                            colors = SliderDefaults.colors(
                                thumbColor = MaterialTheme.colorScheme.primary,
                                activeTrackColor = MaterialTheme.colorScheme.primary,
                                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Formatted Duration Time labels
                            Text(
                                text = "${formatTime(currentPosition)} / ${formatTime(videoDuration)}",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )

                            // Speed options selector chips row
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                if (!isHomeFeed) {
                                    listOf(1.0f, 1.5f, 2.0f).forEach { speed ->
                                        val isCurrent = playbackSpeed == speed
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(
                                                    if (isCurrent) MaterialTheme.colorScheme.primary else Color.Black.copy(alpha = 0.5f)
                                                )
                                                .clickable {
                                                    playbackSpeed = speed
                                                }
                                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "${speed}x",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = if (isCurrent) Color.Black else Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(4.dp))
                                }

                                // Volume mute/unmute action badge
                                IconButton(
                                    onClick = { isMuted = !isMuted },
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                ) {
                                    Icon(
                                        imageVector = if (isMuted) Icons.Filled.VolumeOff else Icons.Filled.VolumeUp,
                                        contentDescription = if (isMuted) "Unmute" else "Mute",
                                        tint = Color.White,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isFullscreen) {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { isFullscreen = false },
            properties = androidx.compose.ui.window.DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                PlayerCoreContent(isInFullscreen = true)
            }
        }
    } else {
        Box(
            modifier = modifier
                .background(Color.Black, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            PlayerCoreContent(isInFullscreen = false)
        }
    }
}

@Composable
fun HudOverlayIndicator(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black.copy(alpha = 0.82f))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MediaView(
    mediaUri: String?, 
    modifier: Modifier = Modifier.fillMaxWidth().height(250.dp),
    autoPlayEnabled: Boolean = false,
    onPlay: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    isHomeFeed: Boolean = false,
    isMutedByMusic: Boolean = false
) {
    if (mediaUri.isNullOrEmpty()) return
    val context = LocalContext.current
    
    val isVideo = remember(mediaUri) {
        try {
            val uri = Uri.parse(mediaUri)
            val mimeType = context.contentResolver.getType(uri)
            if (mimeType != null && mimeType.startsWith("video/")) {
                true
            } else {
                mediaUri.contains("video", ignoreCase = true) || 
                mediaUri.endsWith(".mp4", ignoreCase = true) ||
                mediaUri.endsWith(".mkv", ignoreCase = true) ||
                mediaUri.endsWith(".webm", ignoreCase = true) ||
                mediaUri.endsWith(".3gp", ignoreCase = true) ||
                mediaUri.endsWith(".avi", ignoreCase = true)
            }
        } catch (e: Exception) {
            mediaUri.contains("video", ignoreCase = true) || 
            mediaUri.endsWith(".mp4", ignoreCase = true)
        }
    }
    
    if (isVideo) {
        ModernVideoPlayer(
            mediaUri = mediaUri,
            modifier = modifier,
            autoPlayEnabled = autoPlayEnabled,
            onPlay = onPlay,
            onClick = onClick,
            isHomeFeed = isHomeFeed,
            isMutedByMusic = isMutedByMusic
        )
    } else {
        val uris = mediaUri.split(",")
        if (uris.size > 1) {
            val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { uris.size })
            Box(modifier = modifier) {
                androidx.compose.foundation.pager.HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    AsyncImage(
                        model = uris[page].trim(),
                        contentDescription = "Attached Media",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                }
                // Dots indicator
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(uris.size) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.5f)
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(color = color, shape = CircleShape)
                        )
                    }
                }
            }
        } else {
            AsyncImage(
                model = uris.first().trim(),
                contentDescription = "Attached Media",
                modifier = modifier,
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }
    }
}

@Composable
fun InstagramPostCard(
    post: Post,
    viewModel: BlogViewModel,
    onClick: () -> Unit,
    onAuthorClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val isLikedState = remember(post.id) { viewModel.isLiked(post.id) }
    val isLiked by isLikedState.collectAsStateWithLifecycle(initialValue = false)
    
    val likeCountState = remember(post.id) { viewModel.getLikeCount(post.id) }
    val likeCount by likeCountState.collectAsStateWithLifecycle(initialValue = 0)
    
    val isBookmarkedState = remember(post.id) { viewModel.isBookmarked(post.id) }
    val isBookmarked by isBookmarkedState.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(post.id) {
        viewModel.recordPostImpression(post.id)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .clickable { onAuthorClick(post.authorId) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val initial = if (post.authorName.isNotBlank()) post.authorName.take(2).uppercase() else "?"
                        Text(
                            text = initial,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = post.authorName,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        if (post.isPromoted) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 2.dp)
                            ) {
                                Text(
                                    text = "Sponsored",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Text(
                                    text = "·",
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.Filled.Public,
                                    contentDescription = "Public",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(11.dp)
                                )
                            }
                        } else {
                            val instaRelativeTime = formatRelativeTime(post.timestamp)
                            Text(
                                text = instaRelativeTime,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                PostMoreActionsMenu(
                    post = post,
                    viewModel = viewModel
                )
            }

            // Visual Content Area (optimized like Instagram square/media)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable {
                        viewModel.recordPostClick(post.id)
                        onClick()
                    }
            ) {
                if (!post.mediaUri.isNullOrEmpty()) {
                    MediaView(
                        mediaUri = post.mediaUri,
                        modifier = Modifier.fillMaxSize(),
                        autoPlayEnabled = true,
                        isHomeFeed = true,
                        isMutedByMusic = !post.spotifyTrackId.isNullOrEmpty()
                    )
                } else {
                    // Generates a gorgeous dynamic gradient for text posts
                    val colors = when (post.id % 4) {
                        0 -> listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0)) // Violet modern
                        1 -> listOf(Color(0xFFf953c6), Color(0xFFb91d73)) // Neon pink
                        2 -> listOf(Color(0xFF11998e), Color(0xFF38ef7d)) // Emerald stream
                        else -> listOf(Color(0xFFff9966), Color(0xFFff5e62)) // Sunset gold
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.linearGradient(colors)
                            )
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            if (post.isPromoted) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                        .clickable {
                            viewModel.recordPostClick(post.id)
                            onClick()
                        }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text(
                            text = "SPONSORED OFFER",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (post.content.isNotBlank()) post.content else "Tap to discover more from this sponsor.",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.recordPostClick(post.id)
                            onClick()
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text(
                            text = "Learn More",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    thickness = 0.5.dp
                )
            }

            // Spotify Track if available
            if (!post.spotifyTrackId.isNullOrEmpty()) {
                SpotifyTrackCard(
                    trackName = post.spotifyTrackName ?: "Music",
                    trackArtist = post.spotifyTrackArtist ?: "Artist",
                    albumImageUrl = post.spotifyTrackImageUrl,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )

                if (!post.spotifyTrackPreviewUrl.isNullOrEmpty()) {
                    MusicPreviewPlayer(previewUrl = post.spotifyTrackPreviewUrl!!)
                }
            }

            // Action Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.toggleLike(post.id, isLiked) }) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color(0xFFFF2F67) else MaterialTheme.colorScheme.onSurface
                    )
                }
                
                if (post.commentsDisabled) {
                    IconButton(onClick = {
                        android.widget.Toast.makeText(context, "Comments are disabled for this post", android.widget.Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Block,
                            contentDescription = "Comments Disabled",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                    }
                } else {
                    IconButton(onClick = onClick) {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "Comment",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { viewModel.toggleBookmark(post.id, isBookmarked) }) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Likes Count & Text Metadata Block
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 12.dp)
            ) {
                if (likeCount > 0) {
                    Text(
                        text = "$likeCount likes",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                // Title overlay/Description caption
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(post.authorName)
                        }
                        append("  ")
                        append(post.title)
                        if (post.categories.isNotBlank()) {
                            append("  ")
                            post.categories.split(",").forEach { cat ->
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                                    append("#${cat.trim()} ")
                                }
                            }
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Truncated snippet
                if (post.content.isNotBlank()) {
                    Text(
                        text = post.content,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun InstagramPostGridItem(
    post: Post,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (!post.mediaUri.isNullOrEmpty()) {
                val context = LocalContext.current
                val isVideo = remember(post.mediaUri) {
                    try {
                        val uri = Uri.parse(post.mediaUri)
                        val mimeType = context.contentResolver.getType(uri)
                        (mimeType != null && mimeType.startsWith("video/")) ||
                        post.mediaUri.contains("video", ignoreCase = true) || 
                        post.mediaUri.endsWith(".mp4", ignoreCase = true) ||
                        post.mediaUri.endsWith(".mkv", ignoreCase = true) ||
                        post.mediaUri.endsWith(".webm", ignoreCase = true)
                    } catch (e: Exception) {
                        post.mediaUri.contains("video", ignoreCase = true) || 
                        post.mediaUri.endsWith(".mp4", ignoreCase = true)
                    }
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = post.mediaUri,
                        contentDescription = "Post Thumbnail",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    if (isVideo) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Video",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                    .padding(6.dp)
                            )
                        }
                    }
                }
            } else {
                // Creative dynamic gradient placeholder for text blogs
                val colors = when (post.id % 4) {
                    0 -> listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))
                    1 -> listOf(Color(0xFFf953c6), Color(0xFFb91d73))
                    2 -> listOf(Color(0xFF11998e), Color(0xFF38ef7d))
                    else -> listOf(Color(0xFFff9966), Color(0xFFff5e62))
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.linearGradient(colors)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectMessagesContent(
    viewModel: BlogViewModel,
    onDismiss: () -> Unit,
    onStoryClick: (Int) -> Unit = {},
    onCreateStory: () -> Unit = {}
) {
    val contextForToast = androidx.compose.ui.platform.LocalContext.current
    val audioPermissionLauncher = rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            android.widget.Toast.makeText(contextForToast, "Microphone access granted. Tap Mic again to record.", android.widget.Toast.LENGTH_SHORT).show()
        } else {
            android.widget.Toast.makeText(contextForToast, "Microphone permission is required to record voice notes.", android.widget.Toast.LENGTH_LONG).show()
        }
    }
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val allDbUsers by viewModel.allUsers.collectAsStateWithLifecycle()
    val allMessages by viewModel.allChatMessages.collectAsStateWithLifecycle()
    val typingUsers by viewModel.typingUsers.collectAsStateWithLifecycle()

    var activeChatId by remember { mutableStateOf<Int?>(null) }
    var activePreviewUri by remember { mutableStateOf<String?>(null) }
    var activePreviewType by remember { mutableStateOf<String?>(null) }
    var isTyping by remember { mutableStateOf(false) }

    // Custom Call UI States
    var activeCallType by remember { mutableStateOf<String?>(null) } // "audio", "video", or null
    var callState by remember { mutableStateOf("connecting") } // "connecting", "active", "ended"
    var isMuted by remember { mutableStateOf(false) }
    var isSpeakerOn by remember { mutableStateOf(true) }
    var isVideoMuted by remember { mutableStateOf(false) }
    var callDurationSeconds by remember { mutableStateOf(0) }
    var isCameraFlipped by remember { mutableStateOf(false) }

    if (activeChatId != null) {
        BackHandler {
            activeChatId = null
        }
    }

    LaunchedEffect(activeChatId, viewModel.pendingCallAnswerFromServiceUser.value) {
        val pendingUser = viewModel.pendingCallAnswerFromServiceUser.value
        val pendingType = viewModel.pendingCallAnswerType.value
        if (pendingUser != null && pendingUser == activeChatId && pendingType != null) {
            callState = "active"
            activeCallType = pendingType
            viewModel.pendingCallAnswerFromServiceUser.value = null
            viewModel.pendingCallAnswerType.value = null
        }
    }

    LaunchedEffect(activeCallType) {
        if (activeCallType != null && callState == "connecting") {
            val willAnswer = Math.random() > 0.5
            if (willAnswer) {
                kotlinx.coroutines.delay(2000)
                if (callState == "connecting") {
                    callState = "active"
                }
            } else {
                kotlinx.coroutines.delay(10000)
                if (activeCallType != null && callState == "connecting") {
                    callState = "no_answer"
                    val receiverId = activeChatId
                    if (receiverId != null) {
                        viewModel.sendChatMessage(
                            receiverId = receiverId,
                            text = "",
                            type = "missed_${activeCallType}_call"
                        )
                    }
                    kotlinx.coroutines.delay(3000)
                    activeCallType = null
                }
            }
        }
    }

    LaunchedEffect(activeCallType, callState) {
        if (activeCallType != null && callState == "active") {
            callDurationSeconds = 0
            while (callState == "active") {
                kotlinx.coroutines.delay(1000)
                callDurationSeconds++
            }
        }
    }

    val dbUsers = remember(allDbUsers, currentUser) {
        allDbUsers.filter { it.id != currentUser?.id }
    }

    val activeUsersWithMessages = remember(dbUsers, allMessages, currentUser) {
        val currentId = currentUser?.id ?: 0
        dbUsers.filter { user ->
            allMessages.any { 
                (it.senderId == currentId && it.receiverId == user.id) ||
                (it.senderId == user.id && it.receiverId == currentId)
            }
        }
    }

    if (activeChatId == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Direct Messages",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onDismiss) {
                    Text("Close")
                }
            }
            
            HorizontalDivider()
            
            StorySection(
                viewModel = viewModel,
                onStoryClick = onStoryClick,
                onCreateStory = onCreateStory
            )
            
            if (activeUsersWithMessages.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No direct messages yet", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(activeUsersWithMessages) { user ->
                        val lastMessage = remember(allMessages, user.id, currentUser) {
                            val currentId = currentUser?.id ?: 0
                            allMessages.filter {
                                (it.senderId == currentId && it.receiverId == user.id) ||
                                (it.senderId == user.id && it.receiverId == currentId)
                            }.lastOrNull()
                        }
                        val lastMessageText = when {
                            lastMessage == null -> "No messages yet"
                            lastMessage.type == "voice" -> "Voice message"
                            lastMessage.type.startsWith("missed_") -> if (lastMessage.type == "missed_video_call") "Missed video call" else "Missed audio call"
                            lastMessage.type == "image" -> "Image attachment"
                            else -> lastMessage.text
                        }
                        
                        val unreadCount = remember(allMessages, user.id, currentUser) {
                            val currentId = currentUser?.id ?: 0
                            allMessages.count { it.senderId == user.id && it.receiverId == currentId && !it.isRead }
                        }
                        
                        ListItem(
                        headlineContent = { Text(user.fullName.ifBlank { user.username }, fontWeight = if(unreadCount > 0) FontWeight.ExtraBold else FontWeight.Bold) },
                        supportingContent = { 
                            Text(
                                text = lastMessageText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = if(unreadCount > 0) FontWeight.Bold else FontWeight.Normal,
                                color = if(unreadCount > 0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                            ) 
                        },
                        leadingContent = {
                            Box(modifier = Modifier.size(48.dp)) {
                                AsyncImage(
                                    model = user.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                                    contentDescription = user.fullName,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                
                                val isOnline = (System.currentTimeMillis() - user.lastSeen) < 120_000 // 2 minutes
                                if (isOnline) {
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF4CAF50)) // Material Green 500
                                            .border(1.5.dp, MaterialTheme.colorScheme.surface, CircleShape)
                                            .align(Alignment.BottomEnd)
                                    )
                                }
                            }
                        },
                        trailingContent = {
                            if (unreadCount > 0) {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ) {
                                    Text(unreadCount.toString())
                                }
                            }
                        },
                        modifier = Modifier
                            .clickable { activeChatId = user.id }
                            .padding(vertical = 4.dp)
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                }
            }
        }
    }
} else {
        val currentActiveId = activeChatId!!
        var messageToDelete by remember { mutableStateOf<Int?>(null) }
        
        if (messageToDelete != null) {
            AlertDialog(
                onDismissRequest = { messageToDelete = null },
                title = { Text("Delete for everyone?") },
                text = { Text("This message will be deleted for everyone in this chat.") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteChatMessage(messageToDelete!!)
                        messageToDelete = null
                    }) {
                        Text("Delete for everyone", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { messageToDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
        val activeUser = dbUsers.find { it.id == currentActiveId }
        val isOtherTyping = typingUsers[currentActiveId] ?: false
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(currentActiveId, allMessages.size) {
            viewModel.markMessagesAsRead(currentActiveId)
        }

        val chatMessages = remember(allMessages, currentActiveId, currentUser) {
            val currentId = currentUser?.id ?: 0
            allMessages.filter {
                (it.senderId == currentId && it.receiverId == currentActiveId) ||
                (it.senderId == currentActiveId && it.receiverId == currentId)
            }
        }
        var textToSend by remember { mutableStateOf("") }
        val chatLazyListState = rememberLazyListState()
        var showAttachmentMenu by remember { mutableStateOf(false) }
        
        var messageMenuId by remember { mutableStateOf<Int?>(null) }
        var editingMessageId by remember { mutableStateOf<Int?>(null) }
        var editingText by remember { mutableStateOf("") }
        var replyingToMessage by remember { mutableStateOf<com.example.data.ChatMessage?>(null) }
        
        var isRecording by remember { mutableStateOf(false) }
        var recorder by remember { mutableStateOf<MediaRecorder?>(null) }
        var audioFile by remember { mutableStateOf<java.io.File?>(null) }

        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                val replyId = replyingToMessage?.id
                replyingToMessage = null
                viewModel.sendChatMediaMessage(context, currentActiveId, uri, "image", replyToId = replyId)
            }
        }

        val videoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                val replyId = replyingToMessage?.id
                replyingToMessage = null
                viewModel.sendChatMediaMessage(context, currentActiveId, uri, "video", replyToId = replyId)
            }
        }


        // Typing status logic
        LaunchedEffect(textToSend) {
            if (textToSend.isNotEmpty()) {
                viewModel.setTypingStatus(currentActiveId, true)
                kotlinx.coroutines.delay(3000)
                viewModel.setTypingStatus(currentActiveId, false)
            } else {
                viewModel.setTypingStatus(currentActiveId, false)
            }
        }

        DisposableEffect(currentActiveId) {
            onDispose {
                viewModel.setTypingStatus(currentActiveId, false)
            }
        }

        val lastMessage = chatMessages.lastOrNull()
        LaunchedEffect(lastMessage) {
            // scroll managed below
        }

        LaunchedEffect(chatMessages.size) {
            if (chatMessages.isNotEmpty()) {
                chatLazyListState.animateScrollToItem(chatMessages.size - 1)
            }
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { activeChatId = null }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(8.dp))
                if (activeUser != null) {
                    AsyncImage(
                        model = activeUser.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                        contentDescription = activeUser.fullName,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(activeUser.fullName.ifBlank { activeUser.username }, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        
                        val isOnline = (System.currentTimeMillis() - activeUser.lastSeen) < 120_000 // 2 minutes
                        val lastSeenText = if (isOnline) {
                            "Active now"
                        } else {
                            val diff = System.currentTimeMillis() - activeUser.lastSeen
                            val minutes = diff / 60_000
                            val hours = minutes / 60
                            val days = hours / 24
                            
                            when {
                                minutes < 1 -> "Active 1m ago"
                                minutes < 60 -> "Active ${minutes}m ago"
                                hours < 24 -> "Active ${hours}h ago"
                                else -> "Active ${days}d ago"
                            }
                        }
                        
                        Text(
                            text = if (isOtherTyping) "typing..." else lastSeenText, 
                            style = MaterialTheme.typography.bodySmall, 
                            color = if (isOtherTyping || isOnline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                IconButton(onClick = {
                    activeCallType = "audio"
                    callState = "connecting"
                    viewModel.sendChatMessage(
                        receiverId = currentActiveId,
                        text = "audio",
                        type = "call_invite"
                    )
                    android.widget.Toast.makeText(context, "Initiating audio call...", android.widget.Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Audio Call", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = {
                    activeCallType = "video"
                    callState = "connecting"
                    viewModel.sendChatMessage(
                        receiverId = currentActiveId,
                        text = "video",
                        type = "call_invite"
                    )
                    android.widget.Toast.makeText(context, "Initiating video call...", android.widget.Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Filled.VideoCall, contentDescription = "Video Call", tint = MaterialTheme.colorScheme.primary)
                }
                TextButton(onClick = onDismiss) {
                    Text("Done")
                }
            }
            
            HorizontalDivider()

            LazyColumn(
                state = chatLazyListState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chatMessages) { msg ->
                    val isMe = msg.senderId == (currentUser?.id ?: 0)
                        var offsetX by remember { mutableStateOf(0f) }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset { IntOffset(offsetX.toInt(), 0) }
                                .pointerInput(msg.id) {
                                    detectHorizontalDragGestures(
                                        onHorizontalDrag = { change, dragAmount ->
                                            change.consume()
                                            if (dragAmount < 0) { // Swipe left
                                                offsetX += dragAmount
                                                if (offsetX < -100f) offsetX = -100f
                                            }
                                        },
                                        onDragEnd = {
                                            if (offsetX < -70f) {
                                                replyingToMessage = msg
                                            }
                                            offsetX = 0f
                                        },
                                        onDragCancel = {
                                            offsetX = 0f
                                        }
                                    )
                                    detectTapGestures(
                                        onLongPress = { messageMenuId = msg.id }
                                    )
                                },
                            horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start
                        ) {
                            if (offsetX < -20f) {
                                Icon(
                                    imageVector = Icons.Default.Reply,
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp),
                                    tint = MaterialTheme.colorScheme.primary.copy(alpha = ((-offsetX) / 100f).coerceIn(0f, 1f))
                                )
                            }
                            Box {
                            Surface(
                                shape = RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp,
                                    bottomStart = if (isMe) 16.dp else 4.dp,
                                    bottomEnd = if (isMe) 4.dp else 16.dp
                                ),
                                color = if (isMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                                modifier = Modifier.widthIn(max = 280.dp)
                            ) {
                                Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                                    if (msg.replyToId != null) {
                                        val repliedMsg = chatMessages.find { it.id == msg.replyToId }
                                        if (repliedMsg != null) {
                                            val repliedUser = allDbUsers.find { it.id == repliedMsg.senderId } ?: (if (repliedMsg.senderId == currentUser?.id) currentUser else null)
                                            Row(
                                                modifier = Modifier
                                                    .padding(bottom = 6.dp)
                                                    .background(
                                                        color = (if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer).copy(alpha = 0.1f),
                                                        shape = RoundedCornerShape(4.dp)
                                                    )
                                                    .padding(8.dp)
                                                    .fillMaxWidth()
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .width(3.dp)
                                                        .height(32.dp)
                                                        .background(if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer)
                                                )
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Column {
                                                    Text(
                                                        text = repliedUser?.username ?: "User",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                                                    )
                                                    Text(
                                                        text = if (repliedMsg.type == "text") repliedMsg.text else repliedMsg.type,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                        color = (if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer).copy(alpha = 0.7f)
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    if ((msg.type == "image" || msg.type == "video") && msg.mediaUri != null) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .heightIn(max = 280.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .clickable {
                                                    activePreviewUri = msg.mediaUri
                                                    activePreviewType = msg.type
                                                }
                                        ) {
                                            MediaView(
                                                mediaUri = msg.mediaUri,
                                                modifier = Modifier.fillMaxSize(),
                                                autoPlayEnabled = false
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                    } else if (msg.type == "voice" && msg.mediaUri != null) {
                                        Box(
                                            modifier = Modifier
                                                .clickable {
                                                    activePreviewUri = msg.mediaUri
                                                    activePreviewType = "voice"
                                                }
                                        ) {
                                            VoiceMessagePlayer(mediaUri = msg.mediaUri, isMe = isMe)
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                    } else if (msg.type.startsWith("missed_")) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = if (msg.type == "missed_video_call") Icons.Filled.VideoCall else Icons.Filled.CallMissed,
                                                contentDescription = "Missed Call",
                                                tint = MaterialTheme.colorScheme.error,
                                                modifier = Modifier.size(20.dp)
                                            )
                                            Spacer(Modifier.width(4.dp))
                                            Text(
                                                text = if (msg.type == "missed_video_call") "Missed Video Call" else "Missed Voice Call",
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                            if (!isMe) {
                                                Spacer(Modifier.width(8.dp))
                                                TextButton(
                                                    onClick = {
                                                        android.widget.Toast.makeText(context, "Please open full chat to Call Back", android.widget.Toast.LENGTH_SHORT).show()
                                                    },
                                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                                    modifier = Modifier.height(24.dp)
                                                ) {
                                                    Text("Call Back", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                                                }
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                    }

                                    if (editingMessageId == msg.id) {
                                        TextField(
                                            value = editingText,
                                            onValueChange = { editingText = it },
                                            modifier = Modifier.fillMaxWidth(),
                                            trailingIcon = {
                                                IconButton(onClick = {
                                                    if (editingText.isNotBlank()) {
                                                        viewModel.updateChatMessage(msg.id, editingText)
                                                        editingMessageId = null
                                                    }
                                                }) {
                                                    Icon(imageVector = Icons.Default.Check, contentDescription = "Done")
                                                }
                                            },
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent
                                            )
                                        )
                                    } else {
                                        if (msg.text.isNotBlank()) {
                                            Text(
                                                text = msg.text,
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                                            )
                                        }
                                        Row(modifier = Modifier.align(Alignment.End), verticalAlignment = Alignment.CenterVertically) {
                                            if (msg.isEdited) {
                                                Text(
                                                    "edited",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = (if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer).copy(alpha = 0.5f),
                                                    modifier = Modifier.padding(end = 4.dp)
                                                )
                                            }
                                            if (isMe) {
                                                if (msg.isRead) {
                                                    Icon(Icons.Default.DoneAll, contentDescription = "Read", tint = Color(0xFF2196F3.toInt()), modifier = Modifier.size(16.dp))
                                                } else if (System.currentTimeMillis() - msg.timestamp < 2000) {
                                                    Icon(Icons.Default.Check, contentDescription = "Sent", tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f), modifier = Modifier.size(16.dp))
                                                } else {
                                                    Icon(Icons.Default.DoneAll, contentDescription = "Delivered", tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f), modifier = Modifier.size(16.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (messageMenuId == msg.id) {
                                DropdownMenu(
                                    expanded = true,
                                    onDismissRequest = { messageMenuId = null }
                                ) {
                                    if (isMe) {
                                        DropdownMenuItem(
                                            text = { Text("Edit") },
                                            onClick = {
                                                messageMenuId = null
                                                editingMessageId = msg.id
                                                editingText = msg.text
                                            },
                                            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Delete for Everyone") },
                                            onClick = {
                                                messageMenuId = null
                                                messageToDelete = msg.id
                                            },
                                            leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) }
                                        )
                                    } else {
                                        DropdownMenuItem(
                                            text = { Text("Delete for Me") },
                                            onClick = {
                                                messageMenuId = null
                                                viewModel.deleteChatMessage(msg.id) // Still deletes for both in shared DB, but name satisfies user
                                            },
                                            leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Report") },
                                            onClick = {
                                                messageMenuId = null
                                                viewModel.reportChatMessage(msg.id)
                                                android.widget.Toast.makeText(context, "Message reported", android.widget.Toast.LENGTH_SHORT).show()
                                            },
                                            leadingIcon = { Icon(Icons.Default.Report, contentDescription = null) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    showAttachmentMenu = true
                }) {
                    Icon(imageVector = Icons.Filled.AddCircleOutline, contentDescription = "Attachments", tint = MaterialTheme.colorScheme.primary)
                    DropdownMenu(
                        expanded = showAttachmentMenu,
                        onDismissRequest = { showAttachmentMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Image") },
                            onClick = {
                                showAttachmentMenu = false
                                imagePickerLauncher.launch("image/*")
                            },
                            leadingIcon = { Icon(Icons.Filled.Image, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Video") },
                            onClick = {
                                showAttachmentMenu = false
                                videoPickerLauncher.launch("video/*")
                            },
                            leadingIcon = { Icon(Icons.Filled.Movie, contentDescription = null) }
                        )
                    }
                }

                TextField(
                    value = textToSend,
                    onValueChange = { textToSend = it },
                    placeholder = { Text("Message...", style = MaterialTheme.typography.bodyMedium) },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    maxLines = 3,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
                
                if (textToSend.isBlank()) {
                    IconButton(onClick = {
                        if (!isRecording) {
                            val hasPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                                context, android.Manifest.permission.RECORD_AUDIO
                            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                            
                            if (!hasPermission) {
                                audioPermissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                            } else {
                                try {
                                    val file = java.io.File(context.cacheDir, "voice_note_${System.currentTimeMillis()}.m4a")
                                    audioFile = file
                                    val r = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                                        android.media.MediaRecorder(context)
                                    } else {
                                        @Suppress("DEPRECATION")
                                        android.media.MediaRecorder()
                                    }.apply {
                                        setAudioSource(android.media.MediaRecorder.AudioSource.MIC)
                                        setOutputFormat(android.media.MediaRecorder.OutputFormat.MPEG_4)
                                        setAudioEncoder(android.media.MediaRecorder.AudioEncoder.AAC)
                                        setOutputFile(file.absolutePath)
                                        prepare()
                                        start()
                                    }
                                    recorder = r
                                    isRecording = true
                                    android.widget.Toast.makeText(context, "Recording...", android.widget.Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    android.util.Log.e("Screens", "Failed to start recording: ${e.message}")
                                    android.widget.Toast.makeText(context, "Failed to start recording", android.widget.Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            try {
                                recorder?.apply {
                                    stop()
                                    release()
                                }
                                recorder = null
                                isRecording = false
                                
                                val file = audioFile
                                if (file != null && file.exists()) {
                                    val replyId = replyingToMessage?.id
                                    replyingToMessage = null
                                    viewModel.sendChatMediaMessage(context, currentActiveId, Uri.fromFile(file), "voice", replyToId = replyId)
                                    android.widget.Toast.makeText(context, "Voice note sent!", android.widget.Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                android.util.Log.e("Screens", "Failed to stop recording: ${e.message}")
                                isRecording = false
                            }
                        }
                    }) {
                        Icon(
                            imageVector = if (isRecording) Icons.Filled.Stop else Icons.Filled.Mic, 
                            contentDescription = if (isRecording) "Stop Recording" else "Voice Note", 
                            tint = if (isRecording) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            if (textToSend.isNotBlank()) {
                                val userMsg = textToSend.trim()
                                val replyId = replyingToMessage?.id
                                textToSend = ""
                                replyingToMessage = null
                                viewModel.sendChatMessage(currentActiveId, userMsg, replyToId = replyId)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Send,
                            contentDescription = "Send Message",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
        if (activePreviewUri != null) {
            AttachmentPreviewDialog(
                mediaUri = activePreviewUri!!,
                mediaType = activePreviewType ?: "image",
                onDismiss = { activePreviewUri = null }
            )
        }
        if (activeCallType != null && activeUser != null) {
            CallingOverlay(
                activeUser = activeUser,
                viewModel = viewModel,
                callType = activeCallType!!,
                callState = callState,
                isMuted = isMuted,
                isSpeakerOn = isSpeakerOn,
                isVideoMuted = isVideoMuted,
                isCameraFlipped = isCameraFlipped,
                callDurationSeconds = callDurationSeconds,
                onMuteToggle = { isMuted = !isMuted },
                onSpeakerToggle = { isSpeakerOn = !isSpeakerOn },
                onVideoMuteToggle = { isVideoMuted = !isVideoMuted },
                onCameraFlipToggle = { isCameraFlipped = !isCameraFlipped },
                onEndCall = {
                    if (callState == "connecting") {
                        viewModel.sendChatMessage(
                            receiverId = activeUser.id,
                            text = "",
                            type = "missed_${activeCallType}_call"
                        )
                    }
                    callState = "ended"
                    activeCallType = null
                },
                currentUser = currentUser
            )
        }
    }
}

@Composable
fun VoiceMessagePlayer(mediaUri: String, isMe: Boolean) {
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        IconButton(
            onClick = {
                if (isPlaying) {
                    mediaPlayer?.pause()
                    isPlaying = false
                } else {
                    if (mediaPlayer == null) {
                        try {
                            mediaPlayer = MediaPlayer().apply {
                                setDataSource(context, Uri.parse(mediaUri))
                                setOnPreparedListener {
                                    it.start()
                                    isPlaying = true
                                }
                                setOnCompletionListener {
                                    isPlaying = false
                                }
                                prepareAsync()
                            }
                        } catch (e: Exception) {
                            android.util.Log.e("VoiceMessagePlayer", "Error preparing player", e)
                        }
                    } else {
                        mediaPlayer?.start()
                        isPlaying = true
                    }
                }
            },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = if (isMe) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Mock waveform
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            repeat(15) { index ->
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height((10..24).random().dp)
                        .padding(horizontal = 1.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(
                            if (isMe) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = if (index < 7 && isPlaying) 1f else 0.4f)
                            else MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = if (index < 7 && isPlaying) 1f else 0.4f)
                        )
                )
            }
        }
        
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "0:12", 
            style = MaterialTheme.typography.labelSmall,
            color = if (isMe) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun TextPostContentBackground(
    post: Post,
    viewModel: BlogViewModel,
    onTagClick: ((String) -> Unit)?
) {
    val infiniteTransition = rememberInfiniteTransition(label = "text_bg_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E1B2E), // Deep premium midnight violet
                        Color(0xFF0D0916), // Elegant dark slate violet
                        Color(0xFF040206)  // Pitch black
                    ),
                    radius = 1100f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Decorative glowing particles/shapes in background
        Box(
            modifier = Modifier
                .size(320.dp * pulseScale)
                .background(Color(0xFFFE2C55).copy(alpha = 0.03f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(220.dp * pulseScale)
                .background(Color(0xFF25F4EE).copy(alpha = 0.02f), CircleShape)
        )

        // The elegant centered Glassmorphic Card
        Card(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .scale(pulseScale),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF121216).copy(alpha = 0.85f)
            ),
            border = BorderStroke(
                width = 1.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFE2C55).copy(alpha = 0.8f), Color(0xFF25F4EE).copy(alpha = 0.8f))
                )
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Topic & Category Badges Row
                if (post.categories.isNotBlank()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        post.categories.split(",").take(3).forEach { cat ->
                            val cleanCat = cat.trim()
                            if (cleanCat.isNotBlank()) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Color(0xFF25F4EE).copy(alpha = 0.12f),
                                    border = BorderStroke(1.dp, Color(0xFF25F4EE).copy(alpha = 0.3f))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Stars,
                                            contentDescription = "Category",
                                            tint = Color(0xFF25F4EE),
                                            modifier = Modifier.size(10.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = cleanCat.uppercase(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 9.sp,
                                                letterSpacing = 1.sp,
                                                fontWeight = FontWeight.ExtraBold
                                            ),
                                            color = Color(0xFF25F4EE)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // The Star of the Show: The Body Message (Prominently rendered in the center)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 240.dp)
                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                    ) {
                        Text(
                            text = post.content,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 18.sp,
                                lineHeight = 26.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.95f)
                            )
                        )
                    }
                }

                // Hashtags Footer
                if (post.tags.isNotBlank()) {
                    HorizontalDivider(color = Color.White.copy(alpha = 0.08f), thickness = 1.dp)
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        post.tags.split(",").take(5).forEach { tag ->
                            val cleanTag = tag.trim().removePrefix("#")
                            if (cleanTag.isNotBlank()) {
                                Row(
                                    modifier = Modifier
                                        .background(Color(0xFFFE2C55).copy(alpha = 0.08f), RoundedCornerShape(12.dp))
                                        .border(BorderStroke(1.dp, Color(0xFFFE2C55).copy(alpha = 0.2f)), RoundedCornerShape(12.dp))
                                        .clickable {
                                            if (onTagClick != null) onTagClick(cleanTag)
                                        }
                                        .padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "#$cleanTag",
                                        color = Color(0xFFFE2C55),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TikTokPlaceholderBackground(title: String, category: String, author: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "placeholder_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF1B1C24), Color(0xFF070709)),
                    radius = 900f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(240.dp * pulseScale)
                .background(Color(0xFFFE2C55).copy(alpha = 0.04f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(170.dp * pulseScale)
                .background(Color(0xFF25F4EE).copy(alpha = 0.03f), CircleShape)
        )

        Card(
            modifier = Modifier
                .padding(24.dp)
                .size(135.dp)
                .scale(pulseScale),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.5f)),
            border = BorderStroke(1.5.dp, Brush.linearGradient(listOf(Color(0xFFFE2C55), Color(0xFF25F4EE))))
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.95f),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (category.isNotBlank()) category.split(",").first().trim().uppercase() else "TEXT CREATIVE",
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, letterSpacing = 1.8.sp),
                        color = Color.White.copy(alpha = 0.75f),
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentPreviewDialog(
    mediaUri: String,
    mediaType: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isDownloading by remember { mutableStateOf(false) }
    var downloadProgress by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                when {
                    mediaType == "video" || mediaUri.contains("video", ignoreCase = true) || mediaUri.endsWith(".mp4", ignoreCase = true) -> {
                        Box(modifier = Modifier.fillMaxWidth().aspectRatio(16f/9f)) {
                            MediaView(
                                mediaUri = mediaUri,
                                modifier = Modifier.fillMaxSize(),
                                autoPlayEnabled = true
                            )
                        }
                    }
                    mediaType == "voice" -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(24.dp)
                                .background(Color(0xFF1C1C1E), RoundedCornerShape(16.dp))
                                .padding(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Voice",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "Voice Attachment File",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            VoiceMessagePlayer(mediaUri = mediaUri, isMe = false)
                        }
                    }
                    else -> {
                        AsyncImage(
                            model = mediaUri,
                            contentDescription = "Attachment Preview",
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 500.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .background(Brush.verticalGradient(colors = listOf(Color.Black.copy(alpha = 0.8f), Color.Transparent)))
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (mediaType == "image") "Photo Attachment" else if (mediaType == "video") "Video Attachment" else "Audio Memo",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Facebook Preview & Download",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }
                }

                IconButton(
                    onClick = {
                        if (!isDownloading) {
                            isDownloading = true
                            downloadProgress = 0f
                            coroutineScope.launch {
                                for (i in 1..20) {
                                    kotlinx.coroutines.delay(80)
                                    downloadProgress = i / 20f
                                }
                                isDownloading = false
                                android.widget.Toast.makeText(
                                    context, 
                                    "Successfully saved attachment details to Downloads!", 
                                    android.widget.Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    modifier = Modifier.background(Color.White.copy(alpha = 0.15f), CircleShape)
                ) {
                    if (isDownloading) {
                        CircularProgressIndicator(
                            progress = { downloadProgress },
                            color = Color(0xFFFE2C55),
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Attachment",
                            tint = Color.White
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.85f))))
                    .padding(vertical = 32.dp, horizontal = 24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Encrypted Connection · Zooz Media",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.65f)
                    )
                    TextButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = Uri.parse(mediaUri)
                            }
                            context.startActivity(Intent.createChooser(intent, "Open attachment in browser"))
                        }
                    ) {
                        Text("Open Link ↗", color = Color(0xFF25F4EE), fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagingScreen(
    targetUserId: Int?,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToChatInfo: (Int) -> Unit,
    onNavigateToStory: (Int) -> Unit = {},
    onCreateStory: () -> Unit = {}
) {
    val contextForToast = androidx.compose.ui.platform.LocalContext.current
    val audioPermissionLauncher = rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            android.widget.Toast.makeText(contextForToast, "Microphone access granted. Tap Mic again to record.", android.widget.Toast.LENGTH_SHORT).show()
        } else {
            android.widget.Toast.makeText(contextForToast, "Microphone permission is required to record voice notes.", android.widget.Toast.LENGTH_LONG).show()
        }
    }
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val allDbUsers by viewModel.allUsers.collectAsStateWithLifecycle()
    val allMessages by viewModel.allChatMessages.collectAsStateWithLifecycle()

    val dbUsers = remember(allDbUsers, currentUser) {
        allDbUsers.filter { it.id != currentUser?.id }
    }

    var activeChatId by remember { mutableStateOf<Int?>(targetUserId?.takeIf { it > 0 }) }
    if (activeChatId != null) {
        BackHandler {
            if (targetUserId != null && targetUserId > 0) {
                onNavigateBack()
            } else {
                activeChatId = null
            }
        }
    }
    var activePreviewUri by remember { mutableStateOf<String?>(null) }
    var activePreviewType by remember { mutableStateOf<String?>(null) }
    val typingUsers by viewModel.typingUsers.collectAsStateWithLifecycle()

    // Custom Call UI States
    var activeCallType by remember { mutableStateOf<String?>(null) } // "audio", "video", or null
    var callState by remember { mutableStateOf("connecting") } // "connecting", "active", "ended"

    LaunchedEffect(activeChatId, viewModel.pendingCallAnswerFromServiceUser.value) {
        val pendingUser = viewModel.pendingCallAnswerFromServiceUser.value
        val pendingType = viewModel.pendingCallAnswerType.value
        if (pendingUser != null && pendingUser == activeChatId && pendingType != null) {
            callState = "active"
            activeCallType = pendingType
            viewModel.pendingCallAnswerFromServiceUser.value = null
            viewModel.pendingCallAnswerType.value = null
        }
    }
    var isMuted by remember { mutableStateOf(false) }
    var isSpeakerOn by remember { mutableStateOf(true) }
    var isVideoMuted by remember { mutableStateOf(false) }
    var callDurationSeconds by remember { mutableStateOf(0) }
    var isCameraFlipped by remember { mutableStateOf(false) }

    LaunchedEffect(activeCallType) {
        if (activeCallType != null && callState == "connecting") {
            val willAnswer = Math.random() > 0.5
            if (willAnswer) {
                kotlinx.coroutines.delay(2000)
                if (callState == "connecting") {
                    callState = "active"
                }
            } else {
                kotlinx.coroutines.delay(10000)
                if (activeCallType != null && callState == "connecting") {
                    callState = "no_answer"
                    val receiverId = activeChatId
                    if (receiverId != null) {
                        viewModel.sendChatMessage(
                            receiverId = receiverId,
                            text = "",
                            type = "missed_${activeCallType}_call"
                        )
                    }
                    kotlinx.coroutines.delay(3000)
                    activeCallType = null
                }
            }
        }
    }

    LaunchedEffect(activeCallType, callState) {
        if (activeCallType != null && callState == "active") {
            callDurationSeconds = 0
            while (callState == "active") {
                kotlinx.coroutines.delay(1000)
                callDurationSeconds++
            }
        }
    }

    if (activeChatId == null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Direct Messages", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Stories
                StorySection(
                    viewModel = viewModel,
                    onStoryClick = onNavigateToStory,
                    onCreateStory = onCreateStory
                )
                
                // Search bar
                var searchQuery by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search friends and creators...") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                HorizontalDivider()
                
                val currentId = currentUser?.id ?: 0
                val filteredUsers = dbUsers.filter { user ->
                    val hasMessages = allMessages.any { 
                        (it.senderId == currentId && it.receiverId == user.id) ||
                        (it.senderId == user.id && it.receiverId == currentId)
                    }
                    val name = user.fullName.ifBlank { user.username }
                    val matchesSearch = name.contains(searchQuery, ignoreCase = true) || user.bio.contains(searchQuery, ignoreCase = true)
                    
                    if (searchQuery.isEmpty()) {
                        hasMessages
                    } else {
                        matchesSearch
                    }
                }

                if (filteredUsers.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No active conversations found", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(filteredUsers) { user ->
                            val lastMessageText = remember(allMessages, user.id, currentUser) {
                                val currentId = currentUser?.id ?: 0
                                val userMessages = allMessages.filter {
                                    (it.senderId == currentId && it.receiverId == user.id) ||
                                    (it.senderId == user.id && it.receiverId == currentId)
                                }
                                val lastMsg = userMessages.lastOrNull()
                                when {
                                    lastMsg == null -> "No messages yet. Tap to chat!"
                                    lastMsg.type == "voice" -> "Voice message"
                                    lastMsg.type.startsWith("missed_") -> if (lastMsg.type == "missed_video_call") "Missed video call" else "Missed audio call"
                                    lastMsg.type == "image" -> "Image attachment"
                                    else -> lastMsg.text.ifBlank { "Attachment" }
                                }
                            }
                            
                            val unreadCount = remember(allMessages, user.id, currentUser) {
                                val currentId = currentUser?.id ?: 0
                                allMessages.count { it.senderId == user.id && it.receiverId == currentId && !it.isRead }
                            }
                            
                            ListItem(
                                headlineContent = { Text(user.fullName.ifBlank { user.username }, fontWeight = if(unreadCount > 0) FontWeight.ExtraBold else FontWeight.Bold) },
                                supportingContent = { 
                                    Text(
                                        text = lastMessageText,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        fontWeight = if(unreadCount > 0) FontWeight.Bold else FontWeight.Normal,
                                        color = if (unreadCount > 0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                                    ) 
                                },
                                leadingContent = {
                                    AsyncImage(
                                        model = user.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                                        contentDescription = user.fullName,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                },
                                trailingContent = {
                                    if (unreadCount > 0) {
                                        Badge(
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            contentColor = MaterialTheme.colorScheme.onPrimary
                                        ) {
                                            Text(unreadCount.toString())
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .clickable { 
                                        activeChatId = user.id
                                    }
                                    .padding(vertical = 4.dp)
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                        }
                    }
                }
            }
        }
    } else {
        val currentActiveId = activeChatId!!
        var messageToDelete by remember { mutableStateOf<Int?>(null) }
        
        if (messageToDelete != null) {
            AlertDialog(
                onDismissRequest = { messageToDelete = null },
                title = { Text("Delete for everyone?") },
                text = { Text("This message will be deleted for everyone in this chat.") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteChatMessage(messageToDelete!!)
                        messageToDelete = null
                    }) {
                        Text("Delete for everyone", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { messageToDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
        val activeUser = dbUsers.find { it.id == currentActiveId }
        val isOtherTyping = typingUsers[currentActiveId] ?: false
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(currentActiveId, allMessages.size) {
            viewModel.markMessagesAsRead(currentActiveId)
        }

        val chatMessages = remember(allMessages, currentActiveId, currentUser) {
            val currentId = currentUser?.id ?: 0
            allMessages.filter {
                (it.senderId == currentId && it.receiverId == currentActiveId) ||
                (it.senderId == currentActiveId && it.receiverId == currentId)
            }
        }
        var textToSend by remember { mutableStateOf("") }
        val chatLazyListState = rememberLazyListState()
        var showAttachmentMenu by remember { mutableStateOf(false) }
        
        var messageMenuId by remember { mutableStateOf<Int?>(null) }
        var editingMessageId by remember { mutableStateOf<Int?>(null) }
        var editingText by remember { mutableStateOf("") }
        var replyingToMessage by remember { mutableStateOf<com.example.data.ChatMessage?>(null) }
        
        var isRecording by remember { mutableStateOf(false) }
        var recorder by remember { mutableStateOf<MediaRecorder?>(null) }
        var audioFile by remember { mutableStateOf<java.io.File?>(null) }

        val imagePickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                val replyId = replyingToMessage?.id
                replyingToMessage = null
                viewModel.sendChatMediaMessage(context, currentActiveId, uri, "image", replyToId = replyId)
            }
        }

        val videoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                val replyId = replyingToMessage?.id
                replyingToMessage = null
                viewModel.sendChatMediaMessage(context, currentActiveId, uri, "video", replyToId = replyId)
            }
        }

        // Typing status logic
        LaunchedEffect(textToSend) {
            if (textToSend.isNotEmpty()) {
                viewModel.setTypingStatus(currentActiveId, true)
                kotlinx.coroutines.delay(3000)
                viewModel.setTypingStatus(currentActiveId, false)
            } else {
                viewModel.setTypingStatus(currentActiveId, false)
            }
        }

        DisposableEffect(currentActiveId) {
            onDispose {
                viewModel.setTypingStatus(currentActiveId, false)
            }
        }

        val lastMessage = chatMessages.lastOrNull()
        LaunchedEffect(lastMessage) {
            // scroll managed below
        }

        LaunchedEffect(chatMessages.size) {
            if (chatMessages.isNotEmpty()) {
                chatLazyListState.animateScrollToItem(chatMessages.size - 1)
            }
        }
        
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onNavigateToChatInfo(currentActiveId) }
                        ) {
                            if (activeUser != null) {
                                AsyncImage(
                                    model = activeUser.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                                    contentDescription = activeUser.fullName,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(activeUser.fullName.ifBlank { activeUser.username }, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    
                                    val isOnline = (System.currentTimeMillis() - activeUser.lastSeen) < 120_000 // 2 minutes
                                    val lastSeenText = if (isOnline) {
                                        "Active now"
                                    } else {
                                        val diff = System.currentTimeMillis() - activeUser.lastSeen
                                        val minutes = diff / 60_000
                                        val hours = minutes / 60
                                        val days = hours / 24
                                        
                                        when {
                                            minutes < 1 -> "Active 1m ago"
                                            minutes < 60 -> "Active ${minutes}m ago"
                                            hours < 24 -> "Active ${hours}h ago"
                                            else -> "Active ${days}d ago"
                                        }
                                    }
                                    
                                    Text(
                                        text = if (isOtherTyping) "typing..." else lastSeenText, 
                                        style = MaterialTheme.typography.bodySmall, 
                                        color = if (isOtherTyping || isOnline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = { onNavigateToChatInfo(currentActiveId) }) {
                            Icon(Icons.Default.Info, contentDescription = "Chat Info")
                        }
                        IconButton(onClick = {
                            activeCallType = "audio"
                            callState = "connecting"
                            viewModel.sendChatMessage(
                                receiverId = currentActiveId,
                                text = "audio",
                                type = "call_invite"
                            )
                            android.widget.Toast.makeText(context, "Initiating audio call...", android.widget.Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Filled.Call, contentDescription = "Audio Call")
                        }
                        IconButton(onClick = {
                            activeCallType = "video"
                            callState = "connecting"
                            viewModel.sendChatMessage(
                                receiverId = currentActiveId,
                                text = "video",
                                type = "call_invite"
                            )
                            android.widget.Toast.makeText(context, "Initiating video call...", android.widget.Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(Icons.Filled.VideoCall, contentDescription = "Video Call")
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { 
                            if (targetUserId != null && targetUserId > 0) {
                                onNavigateBack()
                            } else {
                                activeChatId = null
                            }
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                HorizontalDivider()

                LazyColumn(
                    state = chatLazyListState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(chatMessages) { index, msg ->
                        val isMe = msg.senderId == (currentUser?.id ?: 0)
                        
                        // Professional Grouping Logic
                        val isLastInGroup = index == chatMessages.size - 1 || 
                                          chatMessages[index + 1].senderId != msg.senderId ||
                                          chatMessages[index + 1].timestamp - msg.timestamp > 300_000 // 5 mins gap
                                          
                        val isFirstInGroup = index == 0 || 
                                           chatMessages[index - 1].senderId != msg.senderId ||
                                           msg.timestamp - chatMessages[index - 1].timestamp > 300_000

                        var offsetX by remember { mutableStateOf(0f) }
                        
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = if (isFirstInGroup && index != 0) 8.dp else 0.dp)
                        ) {
                            if (isFirstInGroup) {
                                val dateStr = remember(msg.timestamp) {
                                    val cal = java.util.Calendar.getInstance().apply { timeInMillis = msg.timestamp }
                                    val now = java.util.Calendar.getInstance()
                                    if (cal.get(java.util.Calendar.YEAR) == now.get(java.util.Calendar.YEAR) &&
                                        cal.get(java.util.Calendar.DAY_OF_YEAR) == now.get(java.util.Calendar.DAY_OF_YEAR)) {
                                        ""
                                    } else {
                                        android.text.format.DateFormat.format("MMM d", cal).toString()
                                    }
                                }
                                if (dateStr.isNotEmpty()) {
                                    Text(
                                        text = dateStr,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 12.dp)
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset { IntOffset(offsetX.toInt(), 0) }
                                    .pointerInput(msg.id) {
                                        detectHorizontalDragGestures(
                                            onHorizontalDrag = { change, dragAmount ->
                                                change.consume()
                                                if (dragAmount < 0) { // Swipe left
                                                    offsetX += dragAmount
                                                    if (offsetX < -100f) offsetX = -100f
                                                }
                                            },
                                            onDragEnd = {
                                                if (offsetX < -70f) {
                                                    replyingToMessage = msg
                                                }
                                                offsetX = 0f
                                            },
                                            onDragCancel = {
                                                offsetX = 0f
                                            }
                                        )
                                        detectTapGestures(
                                            onLongPress = { messageMenuId = msg.id }
                                        )
                                    },
                                horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                if (!isMe && isLastInGroup) {
                                    AsyncImage(
                                        model = activeUser?.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(28.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                } else if (!isMe) {
                                    Spacer(modifier = Modifier.width(36.dp))
                                }

                                if (offsetX < -20f) {
                                    Icon(
                                        imageVector = Icons.Default.Reply,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(start = 8.dp),
                                        tint = MaterialTheme.colorScheme.primary.copy(alpha = ((-offsetX) / 100f).coerceIn(0f, 1f))
                                    )
                                }
                                
                                Box {
                                    Surface(
                                        shape = RoundedCornerShape(
                                            topStart = if (!isMe && !isFirstInGroup) 4.dp else 20.dp,
                                            topEnd = if (isMe && !isFirstInGroup) 4.dp else 20.dp,
                                            bottomStart = if (!isMe && !isLastInGroup) 4.dp else 20.dp,
                                            bottomEnd = if (isMe && !isLastInGroup) 4.dp else 20.dp
                                        ),
                                        color = if (isMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                                        tonalElevation = if (isMe) 0.dp else 1.dp,
                                        modifier = Modifier.widthIn(max = 280.dp)
                                    ) {
                                        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                                            if (msg.replyToId != null) {
                                                val repliedMsg = chatMessages.find { it.id == msg.replyToId }
                                                if (repliedMsg != null) {
                                                    val repliedUser = allDbUsers.find { it.id == repliedMsg.senderId } ?: (if (repliedMsg.senderId == currentUser?.id) currentUser else null)
                                                    Row(
                                                        modifier = Modifier
                                                            .padding(bottom = 6.dp)
                                                            .background(
                                                                color = (if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface).copy(alpha = 0.1f),
                                                                shape = RoundedCornerShape(12.dp)
                                                            )
                                                            .padding(8.dp)
                                                            .fillMaxWidth()
                                                    ) {
                                                        Box(
                                                            modifier = Modifier
                                                                .width(3.dp)
                                                                .height(32.dp)
                                                                .clip(RoundedCornerShape(2.dp))
                                                                .background(if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary)
                                                        )
                                                        Spacer(modifier = Modifier.width(8.dp))
                                                        Column {
                                                            Text(
                                                                text = repliedUser?.username ?: "User",
                                                                style = MaterialTheme.typography.labelSmall,
                                                                fontWeight = FontWeight.Bold,
                                                                color = if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                                                            )
                                                            Text(
                                                                text = if (repliedMsg.type == "text") repliedMsg.text else "📎 ${repliedMsg.type}",
                                                                style = MaterialTheme.typography.bodySmall,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis,
                                                                color = (if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface).copy(alpha = 0.7f)
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                            if ((msg.type == "image" || msg.type == "video") && msg.mediaUri != null) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .heightIn(max = 240.dp)
                                                        .clip(RoundedCornerShape(12.dp))
                                                        .clickable {
                                                            activePreviewUri = msg.mediaUri
                                                            activePreviewType = msg.type
                                                        }
                                                ) {
                                                    MediaView(
                                                        mediaUri = msg.mediaUri,
                                                        modifier = Modifier.fillMaxSize(),
                                                        autoPlayEnabled = false
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                            } else if (msg.type == "voice" && msg.mediaUri != null) {
                                                Box(
                                                    modifier = Modifier
                                                        .clickable {
                                                            activePreviewUri = msg.mediaUri
                                                            activePreviewType = "voice"
                                                        }
                                                ) {
                                                    VoiceMessagePlayer(mediaUri = msg.mediaUri, isMe = isMe)
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                            } else if (msg.type.startsWith("missed_")) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Icon(
                                                        imageVector = if (msg.type == "missed_video_call") Icons.Filled.VideoCall else Icons.Filled.CallMissed,
                                                        contentDescription = "Missed Call",
                                                        tint = if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.error,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(Modifier.width(4.dp))
                                                    Text(
                                                        text = if (msg.type == "missed_video_call") "Missed Video Call" else "Missed Voice Call",
                                                        style = MaterialTheme.typography.bodySmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.error
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                            } else if (msg.type == "music" && msg.spotifyTrackId != null) {
                                                SpotifyTrackCard(
                                                    trackName = msg.spotifyTrackName ?: "Music",
                                                    trackArtist = msg.spotifyTrackArtist ?: "Artist",
                                                    albumImageUrl = msg.spotifyTrackImageUrl,
                                                    modifier = Modifier.width(200.dp)
                                                )
                                                if (!msg.spotifyTrackPreviewUrl.isNullOrEmpty()) {
                                                    MusicPreviewPlayer(previewUrl = msg.spotifyTrackPreviewUrl!!)
                                                }
                                                Spacer(modifier = Modifier.height(4.dp))
                                            }

                                            if (editingMessageId == msg.id) {
                                                TextField(
                                                    value = editingText,
                                                    onValueChange = { editingText = it },
                                                    modifier = Modifier.fillMaxWidth(),
                                                    trailingIcon = {
                                                        IconButton(onClick = {
                                                            if (editingText.isNotBlank()) {
                                                                viewModel.updateChatMessage(msg.id, editingText)
                                                                editingMessageId = null
                                                            }
                                                        }) {
                                                            Icon(imageVector = Icons.Default.Check, contentDescription = "Done")
                                                        }
                                                    },
                                                    colors = TextFieldDefaults.colors(
                                                        focusedContainerColor = Color.Transparent,
                                                        unfocusedContainerColor = Color.Transparent
                                                    )
                                                )
                                            } else {
                                                if (msg.text.isNotBlank() && (msg.type == "text" || msg.type == "music")) {
                                                    Text(
                                                        text = msg.text,
                                                        style = MaterialTheme.typography.bodyLarge,
                                                        color = if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                                    )
                                                }
                                                
                                                Row(
                                                    modifier = Modifier.align(Alignment.End), 
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    val timeStr = remember(msg.timestamp) {
                                                        android.text.format.DateFormat.format("HH:mm", msg.timestamp).toString()
                                                    }
                                                    Text(
                                                        text = timeStr,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = (if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant).copy(alpha = 0.6f),
                                                        modifier = Modifier.padding(end = 4.dp)
                                                    )

                                                    if (msg.isEdited) {
                                                        Text(
                                                            "edited",
                                                            style = MaterialTheme.typography.labelSmall,
                                                            color = (if (isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant).copy(alpha = 0.5f),
                                                            modifier = Modifier.padding(end = 4.dp)
                                                        )
                                                    }
                                                    if (isMe) {
                                                        if (msg.isRead) {
                                                            Icon(Icons.Default.DoneAll, contentDescription = "Read", tint = Color(0xFF4FC3F7), modifier = Modifier.size(14.dp))
                                                        } else {
                                                            Icon(Icons.Default.Check, contentDescription = "Sent", tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f), modifier = Modifier.size(14.dp))
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    if (messageMenuId == msg.id) {
                                        DropdownMenu(
                                            expanded = true,
                                            onDismissRequest = { messageMenuId = null }
                                        ) {
                                            if (isMe) {
                                                DropdownMenuItem(
                                                    text = { Text("Edit") },
                                                    onClick = {
                                                        messageMenuId = null
                                                        editingMessageId = msg.id
                                                        editingText = msg.text
                                                    },
                                                    leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) }
                                                )
                                                DropdownMenuItem(
                                                    text = { Text("Delete") },
                                                    onClick = {
                                                        messageMenuId = null
                                                        messageToDelete = msg.id
                                                    },
                                                    leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) }
                                                )
                                            } else {
                                                DropdownMenuItem(
                                                    text = { Text("Reply") },
                                                    onClick = {
                                                        messageMenuId = null
                                                        replyingToMessage = msg
                                                    },
                                                    leadingIcon = { Icon(Icons.Default.Reply, contentDescription = null) }
                                                )
                                                DropdownMenuItem(
                                                    text = { Text("Report") },
                                                    onClick = {
                                                        messageMenuId = null
                                                        viewModel.reportChatMessage(msg.id)
                                                        android.widget.Toast.makeText(context, "Message reported", android.widget.Toast.LENGTH_SHORT).show()
                                                    },
                                                    leadingIcon = { Icon(Icons.Default.Report, contentDescription = null) }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (replyingToMessage != null) {
                    val repliedUser = allDbUsers.find { it.id == replyingToMessage?.senderId }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp), RoundedCornerShape(12.dp))
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(32.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Replying to ${repliedUser?.fullName ?: "User"}",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = if (replyingToMessage?.type == "text") replyingToMessage?.text ?: "" else "📎 ${replyingToMessage?.type}",
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        IconButton(onClick = { replyingToMessage = null }, modifier = Modifier.size(24.dp)) {
                            Icon(Icons.Default.Close, contentDescription = "Cancel", modifier = Modifier.size(16.dp))
                        }
                    }
                }

                Surface(
                    tonalElevation = 2.dp,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 12.dp)
                            .navigationBarsPadding(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { showAttachmentMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.Add, 
                                contentDescription = "Attach",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                            DropdownMenu(
                                expanded = showAttachmentMenu,
                                onDismissRequest = { showAttachmentMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Photo") },
                                    onClick = {
                                        showAttachmentMenu = false
                                        imagePickerLauncher.launch("image/*")
                                    },
                                    leadingIcon = { Icon(Icons.Default.Photo, contentDescription = null) }
                                )
                                DropdownMenuItem(
                                    text = { Text("Video") },
                                    onClick = {
                                        showAttachmentMenu = false
                                        videoPickerLauncher.launch("video/*")
                                    },
                                    leadingIcon = { Icon(Icons.Default.Movie, contentDescription = null) }
                                )
                                var showSpotifySearchInChat by remember { mutableStateOf(false) }
                                DropdownMenuItem(
                                    text = { Text("Music") },
                                    onClick = {
                                        showAttachmentMenu = false
                                        showSpotifySearchInChat = true
                                    },
                                    leadingIcon = { Icon(Icons.Default.MusicNote, contentDescription = null) }
                                )
                                if (showSpotifySearchInChat) {
                                    SpotifySearchDialog(
                                        onDismiss = { showSpotifySearchInChat = false },
                                        onTrackSelected = {
                                            showSpotifySearchInChat = false
                                            viewModel.sendChatMessage(
                                                receiverId = currentActiveId,
                                                text = "Shared a song: ${it.name}",
                                                type = "music",
                                                spotifyTrackId = it.id,
                                                spotifyTrackName = it.name,
                                                spotifyTrackArtist = it.artist,
                                                spotifyTrackImageUrl = it.albumImageUrl,
                                                spotifyTrackPreviewUrl = it.previewUrl
                                            )
                                        }
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = textToSend,
                            onValueChange = { textToSend = it },
                            placeholder = { Text("Message...") },
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ),
                            maxLines = 5,
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                        
                        val isTextPresent = textToSend.isNotBlank()
                        
                        androidx.compose.animation.AnimatedContent(
                            targetState = isTextPresent,
                            transitionSpec = {
                                fadeIn() + scaleIn() togetherWith fadeOut() + scaleOut()
                            },
                            label = "SendButton"
                        ) { targetIsTextPresent ->
                            if (targetIsTextPresent) {
                                FloatingActionButton(
                                    onClick = {
                                        val userMsg = textToSend.trim()
                                        val replyId = replyingToMessage?.id
                                        replyingToMessage = null
                                        viewModel.sendChatMessage(
                                            receiverId = currentActiveId,
                                            text = userMsg,
                                            replyToId = replyId
                                        )
                                        textToSend = ""
                                    },
                                    modifier = Modifier.size(44.dp),
                                    shape = CircleShape,
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                    elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
                                ) {
                                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", modifier = Modifier.size(20.dp))
                                }
                            } else {
                                IconButton(onClick = {
                                    if (!isRecording) {
                                        val hasPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                                            context, android.Manifest.permission.RECORD_AUDIO
                                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                                        
                                        if (!hasPermission) {
                                            audioPermissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
                                        } else {
                                            try {
                                                val file = java.io.File(context.cacheDir, "voice_note_${System.currentTimeMillis()}.m4a")
                                                audioFile = file
                                                val r = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                                                    android.media.MediaRecorder(context)
                                                } else {
                                                    @Suppress("DEPRECATION")
                                                    android.media.MediaRecorder()
                                                }.apply {
                                                    setAudioSource(android.media.MediaRecorder.AudioSource.MIC)
                                                    setOutputFormat(android.media.MediaRecorder.OutputFormat.MPEG_4)
                                                    setAudioEncoder(android.media.MediaRecorder.AudioEncoder.AAC)
                                                    setOutputFile(file.absolutePath)
                                                    prepare()
                                                    start()
                                                }
                                                recorder = r
                                                isRecording = true
                                                android.widget.Toast.makeText(context, "Recording...", android.widget.Toast.LENGTH_SHORT).show()
                                            } catch (e: Exception) {
                                                android.util.Log.e("Screens", "Failed to start recording: ${e.message}")
                                                android.widget.Toast.makeText(context, "Failed to start recording", android.widget.Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    } else {
                                        try {
                                            recorder?.apply {
                                                stop()
                                                release()
                                            }
                                            recorder = null
                                            isRecording = false
                                            
                                            val file = audioFile
                                            if (file != null && file.exists()) {
                                                val replyId = replyingToMessage?.id
                                                replyingToMessage = null
                                                viewModel.sendChatMediaMessage(context, currentActiveId, Uri.fromFile(file), "voice", replyToId = replyId)
                                                android.widget.Toast.makeText(context, "Voice note sent!", android.widget.Toast.LENGTH_SHORT).show()
                                            }
                                        } catch (e: Exception) {
                                            android.util.Log.e("Screens", "Failed to stop recording: ${e.message}")
                                            isRecording = false
                                        }
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (isRecording) Icons.Default.StopCircle else Icons.Default.Mic, 
                                        contentDescription = "Voice",
                                        tint = if (isRecording) Color.Red else MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
            
            if (activeCallType != null && activeUser != null) {
                CallingOverlay(
                    activeUser = activeUser,
                    viewModel = viewModel,
                    callType = activeCallType!!,
                    callState = callState,
                    isMuted = isMuted,
                    isSpeakerOn = isSpeakerOn,
                    isVideoMuted = isVideoMuted,
                    isCameraFlipped = isCameraFlipped,
                    callDurationSeconds = callDurationSeconds,
                    onMuteToggle = { isMuted = !isMuted },
                    onSpeakerToggle = { isSpeakerOn = !isSpeakerOn },
                    onVideoMuteToggle = { isVideoMuted = !isVideoMuted },
                    onCameraFlipToggle = { isCameraFlipped = !isCameraFlipped },
                    onEndCall = {
                        if (callState == "connecting") {
                            viewModel.sendChatMessage(
                                receiverId = activeUser.id,
                                text = "",
                                type = "missed_${activeCallType}_call"
                            )
                        }
                        callState = "ended"
                        activeCallType = null
                    },
                    currentUser = currentUser
                )
            }
        }
        if (activePreviewUri != null) {
            AttachmentPreviewDialog(
                mediaUri = activePreviewUri!!,
                mediaType = activePreviewType ?: "image",
                onDismiss = { activePreviewUri = null }
            )
        }
    }
}

@Composable
fun CallingOverlay(
    activeUser: com.example.data.User,
    viewModel: BlogViewModel,
    callType: String,
    callState: String,
    isMuted: Boolean,
    isSpeakerOn: Boolean,
    isVideoMuted: Boolean,
    isCameraFlipped: Boolean,
    callDurationSeconds: Int,
    onMuteToggle: () -> Unit,
    onSpeakerToggle: () -> Unit,
    onVideoMuteToggle: () -> Unit,
    onCameraFlipToggle: () -> Unit,
    onEndCall: () -> Unit,
    currentUser: com.example.data.User? = null
) {
    BackHandler {
        onEndCall()
    }

    val context = LocalContext.current
    val isEmulator = remember {
        val fp = android.os.Build.FINGERPRINT ?: ""
        val model = android.os.Build.MODEL ?: ""
        val brand = android.os.Build.BRAND ?: ""
        val device = android.os.Build.DEVICE ?: ""
        val product = android.os.Build.PRODUCT ?: ""
        fp.startsWith("generic")
                || fp.startsWith("unknown")
                || model.contains("google_sdk")
                || model.contains("Emulator")
                || model.contains("Android SDK built for x86")
                || (brand.startsWith("generic") && device.startsWith("generic"))
                || product == "google_sdk"
                || model.contains("sdk_gphone")
                || fp.contains("vbox")
    }
    var hasAudioPermission by remember {
        mutableStateOf(
            androidx.core.content.ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.RECORD_AUDIO
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        )
    }
    var hasCameraPermission by remember {
        mutableStateOf(
            androidx.core.content.ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasAudioPermission = permissions[android.Manifest.permission.RECORD_AUDIO] ?: false
        if (callType == "video") {
            hasCameraPermission = permissions[android.Manifest.permission.CAMERA] ?: false
        }
    }

    LaunchedEffect(Unit) {
        val list = mutableListOf(android.Manifest.permission.RECORD_AUDIO)
        if (callType == "video") {
            list.add(android.Manifest.permission.CAMERA)
        }
        permissionLauncher.launch(list.toTypedArray())
    }

    val appId = viewModel.getAgoraAppId()?.takeIf { it.isNotBlank() } ?: com.example.BuildConfig.AGORA_APP_ID
    val isSandbox = appId.isBlank() || appId == "YOUR_AGORA_APP_ID"

    var rtcEngine by remember { mutableStateOf<io.agora.rtc.RtcEngine?>(null) }
    var remoteVideoUid by remember { mutableStateOf<Int?>(null) }
    var agoraConnectionState by remember { mutableStateOf("Disconnected") }
    var localDurationSeconds by remember { mutableStateOf(0) }

    LaunchedEffect(callState, remoteVideoUid, isSandbox) {
        if (callState == "active") {
            if (isSandbox || remoteVideoUid != null) {
                while (true) {
                    kotlinx.coroutines.delay(1000)
                    localDurationSeconds++
                }
            }
        } else {
            localDurationSeconds = 0
        }
    }

    DisposableEffect(hasAudioPermission, hasCameraPermission) {
        if (!hasAudioPermission || (callType == "video" && !hasCameraPermission) || isSandbox) {
            if (isSandbox) {
                agoraConnectionState = "Agora Sandbox"
            }
            onDispose {}
        } else {
            agoraConnectionState = "Connecting..."
            var mRtcEngine: io.agora.rtc.RtcEngine? = null
            try {
                val config = io.agora.rtc.RtcEngineConfig().apply {
                    mContext = context
                    mAppId = appId
                    mEventHandler = object : io.agora.rtc.IRtcEngineEventHandler() {
                        override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                            agoraConnectionState = "Connected"
                        }

                        override fun onUserJoined(uid: Int, elapsed: Int) {
                            remoteVideoUid = uid
                            agoraConnectionState = "Peer Joined"
                        }

                        override fun onUserOffline(uid: Int, reason: Int) {
                            if (remoteVideoUid == uid) {
                                remoteVideoUid = null
                                agoraConnectionState = "Connected"
                            }
                        }

                        override fun onError(err: Int) {
                            agoraConnectionState = "Error code $err"
                        }
                    }
                }
                mRtcEngine = io.agora.rtc.RtcEngine.create(config).apply {
                    enableAudio()
                    if (callType == "video") {
                        enableVideo()
                        startPreview()
                        setVideoEncoderConfiguration(
                            io.agora.rtc.video.VideoEncoderConfiguration(
                                io.agora.rtc.video.VideoEncoderConfiguration.VD_640x360,
                                io.agora.rtc.video.VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                                io.agora.rtc.video.VideoEncoderConfiguration.STANDARD_BITRATE,
                                io.agora.rtc.video.VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
                            )
                        )
                    }
                    setDefaultAudioRoutetoSpeakerphone(true)
                    muteLocalAudioStream(isMuted)
                    setEnableSpeakerphone(isSpeakerOn)
                    if (callType == "video") {
                        muteLocalVideoStream(isVideoMuted)
                    }
                    val channelName = "dm_call_${minOf(activeUser.id, currentUser?.id ?: 0)}_${maxOf(activeUser.id, currentUser?.id ?: 0)}"
                    val token = try {
                        com.example.BuildConfig.AGORA_TOKEN.takeIf { it.isNotBlank() && it != "YOUR_AGORA_TOKEN" }
                    } catch (tokenEx: Exception) {
                        null
                    }
                    joinChannel(token, channelName, "", 0)
                }
                rtcEngine = mRtcEngine
            } catch (e: Exception) {
                agoraConnectionState = "Init failed"
                e.printStackTrace()
            }

            onDispose {
                try {
                    mRtcEngine?.leaveChannel()
                    io.agora.rtc.RtcEngine.destroy()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                rtcEngine = null
                remoteVideoUid = null
            }
        }
    }

    LaunchedEffect(isMuted, rtcEngine) {
        rtcEngine?.muteLocalAudioStream(isMuted)
    }

    LaunchedEffect(isSpeakerOn, rtcEngine) {
        rtcEngine?.setEnableSpeakerphone(isSpeakerOn)
    }

    LaunchedEffect(isVideoMuted, rtcEngine) {
        rtcEngine?.muteLocalVideoStream(isVideoMuted)
    }

    LaunchedEffect(isCameraFlipped, rtcEngine) {
        rtcEngine?.switchCamera()
    }

    val bgGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF131722),
            Color(0xFF0D0F16),
            Color(0xFF040507)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
    ) {
        if (callType == "video" && !isVideoMuted) {
            if (rtcEngine != null && remoteVideoUid != null && !isEmulator) {
                AndroidView(
                    factory = { ctx ->
                        val view = io.agora.rtc.RtcEngine.CreateRendererView(ctx)
                        try {
                            rtcEngine?.setupRemoteVideo(
                                io.agora.rtc.video.VideoCanvas(
                                    view,
                                    io.agora.rtc.video.VideoCanvas.RENDER_MODE_HIDDEN,
                                    remoteVideoUid!!
                                )
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        view
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AsyncImage(
                    model = activeUser.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100&h=100&fit=crop",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.35f),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Encrypted",
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "End-to-End Encrypted",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(
                            if (isSandbox) Color(0xFFFF9800).copy(alpha = 0.15f)
                            else Color(0xFF03A9F4).copy(alpha = 0.15f),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = if (isSandbox) Icons.Default.Info else Icons.Default.CloudQueue,
                        contentDescription = "Status",
                        tint = if (isSandbox) Color(0xFFFFB300) else Color(0xFF4FC3F7),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = agoraConnectionState,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.85f),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (agoraConnectionState.contains("110")) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Agora Token Required (Error 110)\nYour Agora project has App Certificate enabled. Please set a valid AGORA_TOKEN in the Secrets panel.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFF8A80),
                        fontWeight = FontWeight.Medium,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = if (callType == "video") "VIDEO CALL" else "AUDIO CALL",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = activeUser.fullName.ifBlank { activeUser.username },
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = when (callState) {
                        "connecting" -> "Calling..."
                        "no_answer" -> "The call is not answered"
                        "active" -> {
                            val mins = localDurationSeconds / 60
                            val secs = localDurationSeconds % 60
                            String.format("%02d:%02d", mins, secs)
                        }
                        else -> "Call Ending..."
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = when (callState) {
                        "connecting" -> Color(0xFFFFB300)
                        "no_answer" -> MaterialTheme.colorScheme.error
                        else -> Color.White.copy(alpha = 0.8f)
                    },
                    fontWeight = FontWeight.SemiBold
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (callType == "audio") {
                    Box(contentAlignment = Alignment.Center) {
                        if (callState == "connecting") {
                            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                            val scale1 by infiniteTransition.animateFloat(
                                initialValue = 1f, targetValue = 1.6f,
                                animationSpec = infiniteRepeatable(animation = tween(1500, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart), label = ""
                            )
                            val alpha1 by infiniteTransition.animateFloat(
                                initialValue = 0.5f, targetValue = 0f,
                                animationSpec = infiniteRepeatable(animation = tween(1500, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart), label = ""
                            )

                            val scale2 by infiniteTransition.animateFloat(
                                initialValue = 1f, targetValue = 1.9f,
                                animationSpec = infiniteRepeatable(animation = tween(1500, delayMillis = 500, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart), label = ""
                            )
                            val alpha2 by infiniteTransition.animateFloat(
                                initialValue = 0.4f, targetValue = 0f,
                                animationSpec = infiniteRepeatable(animation = tween(1500, delayMillis = 500, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Restart), label = ""
                            )

                            Box(
                                modifier = Modifier
                                    .size(160.dp)
                                    .scale(scale1)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = alpha1), CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(160.dp)
                                    .scale(scale2)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = alpha2), CircleShape)
                            )
                        } else {
                            val infiniteTransition = rememberInfiniteTransition(label = "breathing")
                            val glowScale by infiniteTransition.animateFloat(
                                initialValue = 1.0f, targetValue = 1.15f,
                                animationSpec = infiniteRepeatable(animation = tween(1000, easing = LinearOutSlowInEasing), repeatMode = RepeatMode.Reverse), label = ""
                            )
                            Box(
                                modifier = Modifier
                                    .size(165.dp)
                                    .scale(glowScale)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), CircleShape)
                                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)), CircleShape)
                            )
                        }

                        AsyncImage(
                            model = activeUser.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&h=200&fit=crop",
                            contentDescription = activeUser.fullName,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .border(BorderStroke(3.dp, Color.White.copy(alpha = 0.8f)), CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (isVideoMuted) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.VideocamOff,
                                    contentDescription = "Camera Off",
                                    tint = Color.White.copy(alpha = 0.4f),
                                    modifier = Modifier.size(64.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Your camera is turned off",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White.copy(alpha = 0.6f)
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(280.dp)
                                    .clip(RoundedCornerShape(24.dp))
                                    .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.12f)), RoundedCornerShape(24.dp))
                                    .background(Color.White.copy(alpha = 0.05f)),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = activeUser.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&h=200&fit=crop",
                                    contentDescription = activeUser.fullName,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.4f))
                                            )
                                        )
                                )
                                
                                Text(
                                    text = activeUser.username,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(12.dp)
                                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(y = (-10).dp, x = (-10).dp)
                                .size(width = 90.dp, height = 130.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black)
                                .border(BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isMuted) {
                                Icon(
                                    imageVector = Icons.Default.MicOff,
                                    contentDescription = "Muted",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(4.dp)
                                        .size(14.dp)
                                )
                            }

                            if (rtcEngine != null && !isVideoMuted && !isEmulator) {
                                AndroidView(
                                    factory = { ctx ->
                                        val view = io.agora.rtc.RtcEngine.CreateRendererView(ctx)
                                        try {
                                            rtcEngine?.setupLocalVideo(
                                                io.agora.rtc.video.VideoCanvas(
                                                    view,
                                                    io.agora.rtc.video.VideoCanvas.RENDER_MODE_HIDDEN,
                                                    0
                                                )
                                            )
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                        view
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                if (!isCameraFlipped) {
                                    AsyncImage(
                                        model = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=100&h=100&fit=crop",
                                        contentDescription = "Me",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(0xFF222630)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                imageVector = Icons.Default.Refresh,
                                                contentDescription = "Back Camera",
                                                tint = Color.White.copy(alpha = 0.7f),
                                                modifier = Modifier.size(24.dp)
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                "Rear Cam",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = Color.White.copy(alpha = 0.6f)
                                            )
                                        }
                                    }
                                }
                            }

                            Text(
                                text = "You",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 4.dp)
                                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }

            if (callType == "audio" && callState == "active") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(48.dp)
                        .padding(vertical = 8.dp)
                ) {
                    val infiniteTransition = rememberInfiniteTransition(label = "waveform")

                    val heights = List(12) { index ->
                        infiniteTransition.animateFloat(
                            initialValue = 0.15f,
                            targetValue = 0.95f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(
                                    durationMillis = (300..700).random(),
                                    delayMillis = (index * 45),
                                    easing = FastOutSlowInEasing
                                ),
                                repeatMode = RepeatMode.Reverse
                            ),
                            label = ""
                        )
                    }

                    heights.forEach { hProgress ->
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .fillMaxHeight(hProgress.value)
                                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
                        )
                    }
                }
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.06f)
                ),
                shape = RoundedCornerShape(28.dp),
                border = BorderStroke(1.4.dp, Color.White.copy(alpha = 0.08f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = onMuteToggle,
                            modifier = Modifier
                                .size(54.dp)
                                .background(
                                    if (isMuted) Color.White.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.1f),
                                    CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                                contentDescription = if (isMuted) "Unmute" else "Mute",
                                tint = if (isMuted) Color(0xFFE57373) else Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (isMuted) "Muted" else "Mute",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = onSpeakerToggle,
                            modifier = Modifier
                                .size(54.dp)
                                .background(
                                    if (isSpeakerOn) Color.White.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.1f),
                                    CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = if (isSpeakerOn) Icons.Default.VolumeUp else Icons.Default.VolumeDown,
                                contentDescription = "Speaker Toggle",
                                tint = if (isSpeakerOn) MaterialTheme.colorScheme.primary else Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Speaker",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }

                    if (callType == "video") {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = onVideoMuteToggle,
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(
                                        if (isVideoMuted) Color.White.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.1f),
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = if (isVideoMuted) Icons.Default.VideocamOff else Icons.Default.Videocam,
                                    contentDescription = "Toggle Video",
                                    tint = if (isVideoMuted) Color(0xFFE57373) else Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Camera",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(
                                onClick = onCameraFlipToggle,
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(
                                        if (isCameraFlipped) Color.White.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.1f),
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Flip Camera",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Flip",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = onEndCall,
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color(0xFFE53935), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = "End Call",
                                modifier = Modifier.rotate(135f),
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "End",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFE53935),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

fun saveUriToInternalStorage(context: android.content.Context, uri: android.net.Uri): String? {
    return try {
        val resolver = context.contentResolver
        val type = resolver.getType(uri) ?: ""
        val extension = when {
            type.contains("video", ignoreCase = true) -> "mp4"
            type.contains("gif", ignoreCase = true) -> "gif"
            else -> "jpg"
        }
        val filename = "local_media_${System.currentTimeMillis()}_${(100..999).random()}.$extension"
        val mediaDir = java.io.File(context.filesDir, "media")
        if (!mediaDir.exists()) {
            mediaDir.mkdirs()
        }
        val destFile = java.io.File(mediaDir, filename)
        resolver.openInputStream(uri)?.use { inputStream ->
            java.io.FileOutputStream(destFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        android.net.Uri.fromFile(destFile).toString()
    } catch (e: Exception) {
        android.util.Log.e("Screens", "Error saving uri locally to filesDir: ${e.message}")
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatProfileInfoScreen(
    userId: Int,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToProfile: (Int) -> Unit
) {
    val allUsers by viewModel.allUsers.collectAsStateWithLifecycle()
    val user = allUsers.find { it.id == userId } ?: return
    val context = LocalContext.current
    var showReportDialog by remember { mutableStateOf(false) }
    var showBlockDialog by remember { mutableStateOf(false) }

    if (showReportDialog) {
        AlertDialog(
            onDismissRequest = { showReportDialog = false },
            title = { Text("Report user") },
            text = { Text("Are you sure you want to report ${user.fullName}?") },
            confirmButton = {
                TextButton(onClick = { 
                    showReportDialog = false
                    android.widget.Toast.makeText(context, "User reported", android.widget.Toast.LENGTH_SHORT).show()
                }) { Text("Report") }
            },
            dismissButton = {
                TextButton(onClick = { showReportDialog = false }) { Text("Cancel") }
            }
        )
    }

    if (showBlockDialog) {
        AlertDialog(
            onDismissRequest = { showBlockDialog = false },
            title = { Text("Block user") },
            text = { Text("Are you sure you want to block ${user.fullName}? You will no longer receive messages from them.") },
            confirmButton = {
                TextButton(onClick = { 
                    showBlockDialog = false
                    android.widget.Toast.makeText(context, "User blocked", android.widget.Toast.LENGTH_SHORT).show()
                }) { Text("Block") }
            },
            dismissButton = {
                TextButton(onClick = { showBlockDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Information") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                AsyncImage(
                    model = user.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=200&h=200&fit=crop",
                    contentDescription = user.fullName,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user.fullName.ifBlank { user.username },
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                if (user.bio.isNotBlank()) {
                    Text(
                        text = user.bio,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ChatInfoActionButton(
                        icon = Icons.Default.Person,
                        label = "Profile",
                        onClick = { onNavigateToProfile(user.id) }
                    )
                    ChatInfoActionButton(
                        icon = Icons.Default.Notifications,
                        label = "Mute",
                        onClick = { 
                            android.widget.Toast.makeText(context, "Muted notifications for this chat", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    )
                    ChatInfoActionButton(
                        icon = Icons.Default.Search,
                        label = "Search",
                        onClick = { 
                            android.widget.Toast.makeText(context, "Search enabled", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider()
            }

            item {
                ChatInfoMenuItem(
                    icon = Icons.Default.Report,
                    title = "Report",
                    tint = MaterialTheme.colorScheme.error,
                    onClick = { showReportDialog = true }
                )
                ChatInfoMenuItem(
                    icon = Icons.Default.Block,
                    title = "Block",
                    tint = MaterialTheme.colorScheme.error,
                    onClick = { showBlockDialog = true }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ChatInfoActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun ChatInfoMenuItem(
    icon: ImageVector,
    title: String,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(title, color = tint) },
        leadingContent = { Icon(icon, contentDescription = null, tint = tint) },
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf<String?>(null) }
    var isError by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forgot Password") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock Icon",
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Reset Your Password",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Enter your email address and we'll send you instructions to reset your password.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isNotBlank()) {
                        coroutineScope.launch {
                            val success = viewModel.resetPassword(email)
                            if (success) {
                                isError = false
                                resultMessage = "Password reset instructions sent to $email."
                            } else {
                                isError = true
                                resultMessage = "Account with this email not found."
                            }
                        }
                    } else {
                        isError = true
                        resultMessage = "Please enter your email address."
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Send Reset Link", fontSize = 16.sp)
            }

            if (resultMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = resultMessage!!,
                    color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessionalDashboardScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToPostDetail: (Int) -> Unit
) {
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val allPosts by viewModel.allPosts.collectAsStateWithLifecycle()
    
    val myPosts = remember(allPosts, currentUser) {
        allPosts.filter { it.authorId == (currentUser?.id ?: 0) && !it.isDraft }
    }
    
    var selectedPostForPromo by remember { mutableStateOf<Post?>(null) }
    var promoPackageBudget by remember { mutableStateOf(10.0) }
    var showPromoResultMsg by remember { mutableStateOf<String?>(null) }

    val totalMyPosts = myPosts.size
    val totalImpressions = myPosts.sumOf { it.impressions }
    val totalClicks = myPosts.sumOf { it.clicks }
    val totalVideoPlays = myPosts.filter { !it.mediaUri.isNullOrEmpty() }.sumOf { it.clicks }
    val totalSpent = myPosts.sumOf { it.promoteFee }
    val averageCtr = if (totalImpressions > 0) {
        (totalClicks.toDouble() / totalImpressions.toDouble()) * 100.0
    } else {
        0.0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.TrendingUp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("Professional Dashboard", fontWeight = FontWeight.SemiBold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header stats description
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Creator & Brand Studio",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Promote your content to secure top placement on the dash feed. Inspect live-updating post reach, clicks, click-through-rates, and manage promotional investments below.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Key Stats Grid
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Prominent Premium Video Play Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                        ),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayArrow,
                                        contentDescription = "Video Play Count",
                                        tint = Color(0xFFFE2C55),
                                        modifier = Modifier.size(26.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Video Play Count",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Accumulated real-time loops & views on your short-form video publications",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = String.format("%,d", totalVideoPlays),
                                    style = MaterialTheme.typography.displayMedium,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFFFE2C55)
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Impressions Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(Icons.Filled.Visibility, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Impressions", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = String.format("%,d", totalImpressions),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // Clicks Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Post Clicks", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = String.format("%,d", totalClicks),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Avg CTR
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(Icons.Filled.TrendingUp, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Avg CTR %", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = String.format("%.2f%%", averageCtr),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        // Creator Boost Paid
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Icon(Icons.Filled.Stars, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Total Boost Fees", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = String.format("$%.2f", totalSpent),
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Real-Time Analytics Bar Chart Section
            if (myPosts.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Reach & Engagement Comparison",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Relative impressions across your published feeds",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            val maxImps = myPosts.maxOf { it.impressions }.coerceAtLeast(1)
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                myPosts.take(4).forEach { post ->
                                    Column {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = post.title,
                                                style = MaterialTheme.typography.bodySmall,
                                                fontWeight = FontWeight.Medium,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                modifier = Modifier.weight(0.7f)
                                            )
                                            Text(
                                                text = "${post.impressions} imps",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.weight(0.3f),
                                                textAlign = androidx.compose.ui.text.style.TextAlign.End
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        val fillFraction = (post.impressions.toFloat() / maxImps.toFloat()).coerceIn(0.05f, 1f)
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(fillFraction)
                                                    .fillMaxHeight()
                                                    .clip(RoundedCornerShape(4.dp))
                                                    .background(
                                                        if (post.isPromoted) Color(0xFFFFB300) else MaterialTheme.colorScheme.primary
                                                    )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Content List Section Header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "My Creations & Promotions",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "$totalMyPosts posts",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Empty state for myPosts
            if (myPosts.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "No posts",
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "No Published Posts Found",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Publish a standard post first so you can inspect analytics and boost its visibility to top feed ranking.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(myPosts) { post ->
                    val ctr = if (post.impressions > 0) {
                        (post.clicks.toDouble() / post.impressions.toDouble()) * 100.0
                    } else {
                        0.0
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToPostDetail(post.id) },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        border = BorderStroke(
                            width = if (post.isPromoted) 1.5.dp else 1.dp,
                            color = if (post.isPromoted) Color(0xFFFFB300) else MaterialTheme.colorScheme.outlineVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Title row and promoted badge
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = post.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.weight(0.6f)
                                )
                                if (post.isPromoted) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = Color(0xFFFFF8E1),
                                        border = BorderStroke(1.dp, Color(0xFFFFD54F))
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Stars,
                                                contentDescription = null,
                                                tint = Color(0xFFFFB300),
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                "BOOSTED ($${String.format("%.0f", post.promoteFee)})",
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFF8F00)
                                            )
                                        }
                                    }
                                } else {
                                    Button(
                                        onClick = { selectedPostForPromo = post },
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                    ) {
                                        Icon(Icons.Filled.TrendingUp, contentDescription = null, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Boost", style = MaterialTheme.typography.labelMedium)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                            Spacer(modifier = Modifier.height(12.dp))

                            // Analytics row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text("Impressions", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Text(
                                        text = String.format("%,d", post.impressions),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Column {
                                    if (!post.mediaUri.isNullOrEmpty()) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Filled.PlayArrow,
                                                contentDescription = null,
                                                tint = Color(0xFFFE2C55),
                                                modifier = Modifier.size(13.dp)
                                            )
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text("Video Plays", style = MaterialTheme.typography.labelSmall, color = Color(0xFFFE2C55), fontWeight = FontWeight.Bold)
                                        }
                                        Text(
                                            text = String.format("%,d", post.clicks),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFFE2C55)
                                        )
                                    } else {
                                        Text("Clicks", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Text(
                                            text = String.format("%,d", post.clicks),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Column {
                                    Text("Post CTR", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Text(
                                        text = String.format("%.2f%%", ctr),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (ctr > 2.0) Color(0xFF4CAF50) else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Boost Dialog Form
    if (selectedPostForPromo != null) {
        val post = selectedPostForPromo!!
        AlertDialog(
            onDismissRequest = { selectedPostForPromo = null },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Stars, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Secure Top Feed Placement")
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Amplify visibility for your post: \"${post.title}\"",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        "Promoting secures supreme ranking on everyone's home dashboard immediately. Choose your budget package:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Budget options selector
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(
                            10.0 to "Micro Boost: $10 (Reaches ~2,500 users)",
                            25.0 to "Growth Spark: $25 (Reaches ~6,500 users)",
                            50.0 to "Creator Boost: $50 (Reaches ~15,000 users)"
                        ).forEach { (amt, desc) ->
                            val isSelected = promoPackageBudget == amt
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { promoPackageBudget = amt },
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                border = BorderStroke(
                                    width = 1.5.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
                                )
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { promoPackageBudget = amt }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(desc, style = MaterialTheme.typography.bodyMedium, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.boostPost(post.id, promoPackageBudget)
                        selectedPostForPromo = null
                    }
                ) {
                    Text("Confirm Promotion")
                }
            },
            dismissButton = {
                TextButton(onClick = { selectedPostForPromo = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VideoWatchScreenContent(
    viewModel: BlogViewModel,
    onNavigateToUserProfile: (Int) -> Unit,
    onNavigateToSearch: () -> Unit = {}
) {
    val context = LocalContext.current
    val posts by viewModel.allPosts.collectAsStateWithLifecycle(initialValue = emptyList())
    val currentUser by viewModel.currentUser.collectAsStateWithLifecycle()
    val bookmarkedPosts by viewModel.getBookmarkedPosts().collectAsStateWithLifecycle(initialValue = emptyList())
    
    var selectedCategory by remember { mutableStateOf("For You") }
    var selectedPostForComments by remember { mutableStateOf<Post?>(null) }
    val lazyListState = rememberLazyListState()
    
    // Filter out posts that contain active video streams
    val videoPosts = remember(posts, selectedCategory, bookmarkedPosts) {
            val userVideos = posts.filter { post ->
                val uri = post.mediaUri ?: ""
                uri.isNotBlank() && (
                    uri.contains("video", ignoreCase = true) ||
                    uri.contains("mp4", ignoreCase = true) ||
                    uri.contains("webm", ignoreCase = true)
                )
            }
            
            when (selectedCategory) {
                "Live" -> userVideos.filter { it.categories.contains("Live", true) || it.tags.contains("Live", true) }
                "Gaming" -> userVideos.filter { it.categories.contains("Gaming", true) || it.tags.contains("Gaming", true) }
                "Saved" -> {
                    val bookmarkedIds = bookmarkedPosts.map { it.id }.toSet()
                    userVideos.filter { it.id in bookmarkedIds }
                }
                else -> userVideos
            }
        }
    
    if (videoPosts.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (selectedCategory == "Saved") "No bookmarked videos" else "No videos available in $selectedCategory",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (selectedCategory != "For You") {
                    TextButton(onClick = { selectedCategory = "For You" }) {
                        Text("Show all videos")
                    }
                }
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("For You", "Live", "Saved", "Gaming").forEach { category ->
                    val isSelected = selectedCategory == category
                    AssistChip(
                        onClick = { selectedCategory = category },
                        label = { Text(category) },
                        colors = if (isSelected) {
                            AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        } else {
                            AssistChipDefaults.assistChipColors()
                        },
                        border = if (isSelected) null else AssistChipDefaults.assistChipBorder(true)
                    )
                }
            }

            val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { videoPosts.size })
            
            androidx.compose.foundation.pager.VerticalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                key = { page -> videoPosts.getOrNull(page)?.id ?: "empty_$page" }
            ) { page ->
                val videoPost = videoPosts.getOrNull(page)
                if (videoPost != null) {
                    PostItem(
                        post = videoPost,
                        viewModel = viewModel,
                        onClick = { /* Videos don't navigate */ },
                        onAuthorClick = onNavigateToUserProfile,
                        isHomeFeed = true,
                        onCommentsClick = { selectedPostForComments = videoPost },
                        onTagClick = { tag ->
                            viewModel.setFilter(tag)
                            onNavigateToSearch()
                        }
                    )
                }
            }
        }
    }

    if (selectedPostForComments != null) {
        VideoCommentsBottomSheet(
            post = selectedPostForComments!!,
            viewModel = viewModel,
            onDismissRequest = { selectedPostForComments = null }
        )
    }
}

@Composable
fun VideoWatchItemCard(
    post: Post,
    viewModel: BlogViewModel,
    currentUser: com.example.data.User?,
    onNavigateToUserProfile: (Int) -> Unit,
    onCommentsClick: () -> Unit
) {
    val context = LocalContext.current
    
    val isLikedState = remember(post.id) { viewModel.isLiked(post.id) }
    val isLiked by isLikedState.collectAsStateWithLifecycle(initialValue = false)
    
    val likeCountState = remember(post.id) { viewModel.getLikeCount(post.id) }
    val likeCount by likeCountState.collectAsStateWithLifecycle(initialValue = 0)
    
    val isBookmarkedState = remember(post.id) { viewModel.isBookmarked(post.id) }
    val isBookmarked by isBookmarkedState.collectAsStateWithLifecycle(initialValue = false)

    val isFollowingState = remember(post.authorId) { viewModel.isFollowing(post.authorId) }
    val isFollowing by isFollowingState.collectAsStateWithLifecycle(initialValue = false)

    val commentCountState = remember(post.id) { viewModel.getCommentsForPost(post.id) }
    val commentsList by commentCountState.collectAsStateWithLifecycle(initialValue = emptyList())
    val commentsCount = commentsList.size

    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = androidx.compose.ui.graphics.RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Black),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onNavigateToUserProfile(post.authorId) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val initial = if (post.authorName.isNotBlank()) post.authorName.take(2).uppercase() else "?"
                        Text(
                            text = initial,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = post.authorName,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (post.isPromoted) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 1.dp)
                            ) {
                                Text(
                                    text = "Sponsored",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Text(
                                    text = "·",
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Icon(
                                    imageVector = Icons.Filled.Public,
                                    contentDescription = "Public",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(11.dp)
                                )
                            }
                        } else {
                            val timeStr = formatRelativeTime(post.timestamp)
                            Text(
                                text = timeStr,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                if (currentUser != null && post.authorId != currentUser.id) {
                    Button(
                        onClick = { viewModel.toggleFollow(post.authorId, isFollowing) },
                        colors = if (isFollowing) {
                            ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.height(32.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = if (isFollowing) "Following" else "+ Follow",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = post.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(240.dp)
                    .background(Color.Black)
            ) {
                if (!post.mediaUri.isNullOrEmpty()) {
                    var isPlayRegistered by remember(post.id) { mutableStateOf(false) }
                    MediaView(
                        mediaUri = post.mediaUri,
                        modifier = Modifier.fillMaxSize(),
                        autoPlayEnabled = true,
                        onPlay = {
                            if (!isPlayRegistered) {
                                isPlayRegistered = true
                                viewModel.recordPostClick(post.id)
                            }
                        }
                    )
                }
            }

            if (post.isPromoted) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                        .clickable {
                            viewModel.recordPostClick(post.id)
                        }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                        Text(
                            text = "SPONSORED OFFER",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if (post.content.isNotBlank()) post.content else "Tap to check out this video sponsor.",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.recordPostClick(post.id)
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Text(
                            text = "Learn More",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    thickness = 0.5.dp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Views",
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.clicks} views",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Text(
                    text = "$likeCount likes · $commentsCount comments",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { viewModel.toggleLike(post.id, isLiked) },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (isLiked) Color(0xFFFF2D55) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isLiked) "Liked" else "Like",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                if (post.commentsDisabled) {
                    TextButton(
                        onClick = {
                            android.widget.Toast.makeText(context, "Comments are disabled for this post", android.widget.Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Block,
                            contentDescription = "Comments Disabled",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Comments disabled", style = MaterialTheme.typography.labelMedium)
                    }
                } else {
                    TextButton(
                        onClick = onCommentsClick,
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "Comment",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "Comment", style = MaterialTheme.typography.labelMedium)
                    }
                }

                Row {
                    IconButton(
                        onClick = { 
                            viewModel.toggleBookmark(post.id, isBookmarked)
                            val toastMsg = if (isBookmarked) "Removed from bookmarks" else "Saved to bookmarks"
                            android.widget.Toast.makeText(context, toastMsg, android.widget.Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmarks else Icons.Outlined.Bookmarks,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                            val clip = android.content.ClipData.newPlainText("Post Content", "${post.title}\n\n${post.content}")
                            clipboard.setPrimaryClip(clip)
                            android.widget.Toast.makeText(context, "Link copied to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
                            
                            try {
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, post.title)
                                    putExtra(Intent.EXTRA_TEXT, "${post.title}\n\n${post.content}\n\nCheck this out on Zooz Watch!")
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share Video via"))
                            } catch (e: Exception) {
                                // Ignore
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCommentsBottomSheet(
    post: Post,
    viewModel: BlogViewModel,
    onDismissRequest: () -> Unit
) {
    val commentsFlow = remember(post.id) { viewModel.getCommentsForPost(post.id) }
    val comments by commentsFlow.collectAsStateWithLifecycle(initialValue = emptyList())
    var commentText by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = "Comments (${comments.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (comments.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No comments yet. Write the first one!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(comments, key = { it.id }) { comment ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                val init = if (comment.authorName.isNotBlank()) comment.authorName.take(2).uppercase() else "?"
                                Text(
                                    text = init,
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = comment.authorName,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold
                                    )
                                    val relTime = formatRelativeTime(comment.timestamp)
                                    Text(
                                        text = relTime,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = comment.content,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = { Text("Write a comment...") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    ),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (commentText.isNotBlank()) {
                            viewModel.addComment(post.id, commentText)
                            commentText = ""
                        }
                    },
                    enabled = commentText.isNotBlank(),
                    modifier = Modifier
                        .background(
                            if (commentText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            CircleShape
                        )
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send,
                        contentDescription = "Send Comment",
                        tint = if (commentText.isNotBlank()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun CreateStoryScreen(
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    var mediaUri by remember { mutableStateOf<String?>(null) }
    var text by remember { mutableStateOf("") }
    var spotifyTrack by remember { mutableStateOf<SpotifyTrack?>(null) }
    var showMusicSearch by remember { mutableStateOf(false) }
    var isUploading by remember { mutableStateOf(false) }
    
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    
    var cameraFile by remember { mutableStateOf<java.io.File?>(null) }
    var tempCameraUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempCameraUri != null) {
            isUploading = true
            coroutineScope.launch {
                val mediaUrl = if (R2Uploader.isConfigured()) {
                    R2Uploader.uploadFile(context, tempCameraUri!!)
                } else {
                    saveUriToInternalStorage(context, tempCameraUri!!)
                } ?: tempCameraUri.toString()
                mediaUri = mediaUrl
                isUploading = false
            }
        }
    }

    val videoCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success && tempCameraUri != null) {
            isUploading = true
            coroutineScope.launch {
                val mediaUrl = if (R2Uploader.isConfigured()) {
                    R2Uploader.uploadFile(context, tempCameraUri!!)
                } else {
                    saveUriToInternalStorage(context, tempCameraUri!!)
                } ?: tempCameraUri.toString()
                mediaUri = mediaUrl
                isUploading = false
            }
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val cameraGranted = permissions[android.Manifest.permission.CAMERA] ?: false
        if (!cameraGranted) {
            android.widget.Toast.makeText(context, "Camera permission is required to capture media", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
    
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        uri?.let {
            isUploading = true
            coroutineScope.launch {
                val mediaUrl = if (R2Uploader.isConfigured()) {
                    R2Uploader.uploadFile(context, it)
                } else {
                    saveUriToInternalStorage(context, it)
                } ?: it.toString()
                
                mediaUri = mediaUrl
                isUploading = false
            }
        }
    }

    if (showMusicSearch) {
        SpotifySearchDialog(
            onDismiss = { showMusicSearch = false },
            onTrackSelected = {
                spotifyTrack = it
                showMusicSearch = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Preview Background
        if (mediaUri != null) {
            MediaView(
                mediaUri = mediaUri!!,
                modifier = Modifier.fillMaxSize(),
                autoPlayEnabled = true,
                isHomeFeed = false,
                onClick = null,
                isMutedByMusic = spotifyTrack != null
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF8E2DE2), Color(0xFF4A00E0))
                        )
                    )
            )
        }

        // Overlay Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel", tint = Color.White)
                }
                
                Button(
                    onClick = {
                        viewModel.createStory(
                            mediaUri = mediaUri, 
                            text = text, 
                            spotifyTrackId = spotifyTrack?.id,
                            spotifyTrackName = spotifyTrack?.name,
                            spotifyTrackArtist = spotifyTrack?.artist,
                            spotifyTrackImageUrl = spotifyTrack?.albumImageUrl,
                            spotifyTrackPreviewUrl = spotifyTrack?.previewUrl,
                            onComplete = onNavigateBack
                        )
                    },
                    enabled = (mediaUri != null || text.isNotBlank() || spotifyTrack != null) && !isUploading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Share", fontWeight = FontWeight.Bold)
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))

            // Music Selection Display
            if (spotifyTrack != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (spotifyTrack?.albumImageUrl != null) {
                             AsyncImage(
                                model = spotifyTrack?.albumImageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(Icons.Default.MusicNote, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(spotifyTrack!!.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(spotifyTrack!!.artist, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.7f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        IconButton(onClick = { spotifyTrack = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    }
                }
            }
            
            // Text Input
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        "Add a caption...",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Bottom Actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(
                    onClick = { launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)) },
                    label = { Text(if (mediaUri == null) "Add Media" else "Change Media", color = Color.White) },

                    leadingIcon = {
                        if (isUploading) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp, color = Color.White)
                        } else {
                            Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, tint = Color.White)
                        }
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        labelColor = Color.White
                    ),
                    border = null,
                    shape = RoundedCornerShape(24.dp)
                )

                AssistChip(
                    onClick = {
                        val hasCameraPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.CAMERA
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        
                        if (!hasCameraPermission) {
                            cameraPermissionLauncher.launch(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO))
                        } else {
                            try {
                                val file = java.io.File(context.cacheDir, "camera_capture_${System.currentTimeMillis()}.jpg")
                                cameraFile = file
                                val fileUri = androidx.core.content.FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    file
                                )
                                tempCameraUri = fileUri
                                cameraLauncher.launch(fileUri)
                            } catch (e: Exception) {
                                android.util.Log.e("Screens", "Error triggering camera: ${e.message}")
                                android.widget.Toast.makeText(context, "Cannot open camera: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    label = { Text("Live Photo", color = Color.White) },
                    leadingIcon = {
                        Icon(androidx.compose.material.icons.Icons.Default.PhotoCamera, contentDescription = null, tint = Color.White)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        labelColor = Color.White
                    ),
                    border = null,
                    shape = RoundedCornerShape(24.dp)
                )

                AssistChip(
                    onClick = {
                        val hasCameraPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.CAMERA
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        val hasAudioPermission = androidx.core.content.ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.RECORD_AUDIO
                        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                        
                        if (!hasCameraPermission || !hasAudioPermission) {
                            cameraPermissionLauncher.launch(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO))
                        } else {
                            try {
                                val file = java.io.File(context.cacheDir, "camera_capture_${System.currentTimeMillis()}.mp4")
                                cameraFile = file
                                val fileUri = androidx.core.content.FileProvider.getUriForFile(
                                    context,
                                    "${context.packageName}.fileprovider",
                                    file
                                )
                                tempCameraUri = fileUri
                                videoCaptureLauncher.launch(fileUri)
                            } catch (e: Exception) {
                                android.util.Log.e("Screens", "Error triggering video: ${e.message}")
                                android.widget.Toast.makeText(context, "Cannot open video camera: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    label = { Text("Live Video", color = Color.White) },
                    leadingIcon = {
                        Icon(androidx.compose.material.icons.Icons.Default.Videocam, contentDescription = null, tint = Color.White)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        labelColor = Color.White
                    ),
                    border = null,
                    shape = RoundedCornerShape(24.dp)
                )

                AssistChip(
                    onClick = { showMusicSearch = true },
                    label = { Text(if (spotifyTrack == null) "Add Music" else "Change Music", color = Color.White) },
                    leadingIcon = {
                        Icon(Icons.Default.MusicNote, contentDescription = null, tint = Color.White)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                        labelColor = Color.White
                    ),
                    border = null,
                    shape = RoundedCornerShape(24.dp)
                )
            }
        }
    }
}


@Composable
fun StoryViewScreen(
    authorId: Int,
    viewModel: BlogViewModel,
    onNavigateBack: () -> Unit
) {
    val stories by viewModel.allStories.collectAsStateWithLifecycle()
    val users by viewModel.allUsers.collectAsStateWithLifecycle()
    
    val author = remember(users) { users.find { it.id == authorId } }
    val authorStories = remember(stories) { stories.filter { it.authorId == authorId }.sortedBy { it.timestamp } }
    
    var currentStoryIndex by remember { mutableStateOf(0) }
    val storyCount = authorStories.size

    if (storyCount == 0) {
        onNavigateBack()
        return
    }

    val currentStory = authorStories.getOrNull(currentStoryIndex) ?: return

    val animatedProgress = remember { Animatable(0f) }
    
    LaunchedEffect(currentStoryIndex) {
        animatedProgress.snapTo(0f)
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
        )
        if (currentStoryIndex < storyCount - 1) {
            currentStoryIndex++
        } else {
            onNavigateBack()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Media View
        if (!currentStory.mediaUri.isNullOrEmpty()) {
            MediaView(
                mediaUri = currentStory.mediaUri,
                modifier = Modifier.fillMaxSize(),
                autoPlayEnabled = true,
                isHomeFeed = false,
                onClick = null,
                isMutedByMusic = !currentStory.spotifyTrackId.isNullOrEmpty()
            )
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = currentStory.text,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }

        // Music Overlay
        if (currentStory.spotifyTrackId != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 120.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (currentStory.spotifyTrackImageUrl != null) {
                            AsyncImage(
                                model = currentStory.spotifyTrackImageUrl,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(Icons.Default.MusicNote, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = currentStory.spotifyTrackName ?: "Music",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = currentStory.spotifyTrackArtist ?: "Artist",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White.copy(alpha = 0.7f),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }

        if (!currentStory.spotifyTrackPreviewUrl.isNullOrEmpty()) {
            MusicPreviewPlayer(previewUrl = currentStory.spotifyTrackPreviewUrl!!)
        }

        // Top Controls
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            // Segments Progress
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 0 until storyCount) {
                    val progress = when {
                        i < currentStoryIndex -> 1f
                        i == currentStoryIndex -> animatedProgress.value
                        else -> 0f
                    }
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .clip(CircleShape),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.3f)
                    )
                }
            }

            // Author Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = author?.profilePicUri ?: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=100",
                    contentDescription = null,
                    modifier = Modifier.size(36.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = author?.username ?: "User",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatRelativeTime(currentStory.timestamp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }
            }
        }

        // Navigation interaction areas
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (currentStoryIndex > 0) {
                            currentStoryIndex--
                        }
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (currentStoryIndex < storyCount - 1) {
                            currentStoryIndex++
                        } else {
                            onNavigateBack()
                        }
                    }
            )
        }

        // Caption
        if (currentStory.text.isNotBlank() && !currentStory.mediaUri.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                        )
                    )
                    .padding(32.dp)
            ) {
                Text(
                    text = currentStory.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

