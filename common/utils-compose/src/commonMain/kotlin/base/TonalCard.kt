package base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import view.colorScheme
import view.shapes

@Composable
fun TonalCard(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.large,
    containerColor: Color = colorScheme.surfaceContainer,
    contentColor: Color = colorScheme.onSurface,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .then(
                if (onClick != null) Modifier.clip(shape).clickable {
                    onClick()
                }
                else Modifier
            ),
        shape = shape,
        color = containerColor,
        contentColor = contentColor
    ) {
        Box {
            content()
        }
    }
}

