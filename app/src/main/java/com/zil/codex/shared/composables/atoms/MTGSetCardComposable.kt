package com.zil.codex.shared.composables.atoms

import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.zil.codex.R
import com.zil.codex.shared.data.models.MTGSet
import com.zil.codex.ui.fonts.FontManager

@Composable
fun MTGSetCardComposable(
	set: MTGSet,
	onClick: () -> Unit
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(100.dp),
		elevation = 10.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.clickable(
					interactionSource = remember { MutableInteractionSource() },
					indication = rememberRipple(bounded = true),
					onClick = { onClick.invoke() }
				)
				.background(Color.Transparent),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			AndroidView(modifier = Modifier.wrapContentSize(),
				factory = { context ->
					TextView(context).apply {
						layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
						typeface = FontManager.getTypeface(context, FontManager.FONTAWESOME)
						textSize = resources.getDimension(R.dimen.text_20sp)
						setTextColor(android.graphics.Color.BLACK)
					}
				},
				update = { view ->
					view.text = set.getKeyruneUnicode() ?: set.code
				})

			Text(
				modifier = Modifier.padding(horizontal = 8.dp),
				fontSize = 12.sp,
				text = set.name,
				textAlign = TextAlign.Center,
				maxLines = 2,
				overflow = TextOverflow.Ellipsis
			)
		}
	}
}

@Composable
@Preview
fun MTGSetCardComposablePreview() {
	MTGCardComposable(
		"",
		"https://api.scryfall.com/cards/7a5cd03c-4227-4551-aa4b-7d119f0468b5?format=image"
	)
}
