package base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import view.colorScheme
import view.typography

@Composable
fun DefaultBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = typography.bodyLarge,
    placeholderText: String,
    placeholderAlignment: Alignment = Alignment.CenterStart,
    placeholderColor: Color = DefaultBasicTextFieldDefaults.placeholderColor,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1,
    textColor: Color = colorScheme.onSurface
) {

    val textFieldValue by remember(value) {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length)
            )
        )
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            onValueChange(newValue.text)
        },
        modifier = modifier,
        textStyle = textStyle.copy(
            color = textColor
        ),
        decorationBox = { innerTextField ->
            Box(Modifier.width(IntrinsicSize.Max)) {
                Text(
                    text = placeholderText,
                    style = textStyle,
                    color = placeholderColor.copy(alpha = if (value.isEmpty()) 1f else 0f),
                    maxLines = 1,
                    modifier = Modifier.align(placeholderAlignment)
                )

                innerTextField()
            }
        },
        cursorBrush = SolidColor(colorScheme.primary),
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions

    )
}