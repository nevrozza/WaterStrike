package base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import base.layouts.ThreeComponentsLayout
import view.colorScheme
import view.consts.Paddings
import view.consts.Sizes
import view.typography


data object DefaultBasicTextFieldDefaults {
    val placeholderColor
        @Composable get() = colorScheme.onSurface.copy(alpha = .6f)
}

// with background
@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(Paddings.semiMedium),
    textStyle: TextStyle = typography.bodyLarge,
    placeholderText: String,
    placeholderAlignment: Alignment = Alignment.CenterStart,
    placeholderColor: Color = DefaultBasicTextFieldDefaults.placeholderColor,
    paddingBetween: Dp = Paddings.medium,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: ImageVector? = null,
    containerColor: Color = colorScheme.surfaceContainerHigh,
    maxLines: Int = Int.MAX_VALUE,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val focusRequester = remember { FocusRequester() }
    TonalCard(modifier = modifier, containerColor = containerColor) {
        Box(Modifier.matchParentSize().pointerInput(Unit) {
            detectTapGestures {
                focusRequester.requestFocus()
            }
        })
        ThreeComponentsLayout(
            modifier = Modifier.padding(contentPadding),
            paddingBetween = paddingBetween,
            leftContent = {
                leadingIcon?.let {
                    Icon(
                        leadingIcon, "textFieldLeadingIcon",
                        tint = placeholderColor
                    )
                }
            },
            rightContent = {
                trailingIcon?.let {

                    CompositionLocalProvider(
                        LocalContentColor provides placeholderColor
                    ) {
                        Box(Modifier.size(Sizes.iconSize)) {
                            trailingIcon()
                        }
                    }
                }
            },
            centerContent = {
                DefaultBasicTextField(
                    value,
                    onValueChange,
                    Modifier.focusRequester(focusRequester),
                    textStyle,
                    placeholderText,
                    placeholderAlignment,
                    placeholderColor,
                    visualTransformation,
                    keyboardActions,
                    keyboardOptions,
                    maxLines = maxLines
                )

            }
        )
    }


}