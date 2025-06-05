package base

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import view.colorScheme
import view.shapes
import view.typography

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = typography.bodyLarge,
    maxLines: Int = Int.MAX_VALUE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val colors = TextFieldDefaults.colors(
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent
    )
    val shape = shapes.large

    OutlinedTextField(
        value = value,
        onValueChange = { text ->
            onValueChange(text)
        },
        placeholder = { Text(placeholderText, style = textStyle, modifier = Modifier.alpha(.6f)) },
        shape = shape,
        colors = colors,
        modifier = modifier.clip(shape = shape).background(colorScheme.surfaceContainerHigh),
        textStyle = textStyle,
        maxLines = maxLines,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions
    )
}