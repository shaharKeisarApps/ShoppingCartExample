@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.keisardev.supersmartexample.feature_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keisardev.supersmartexample.R
import com.keisardev.supersmartexample.feature_list.domain.entity.ListItemUIModel
import com.keisardev.supersmartexample.feature_list.presentation.components.ProductItemPreview.avocado
import com.keisardev.supersmartexample.ui.theme.Colors

@Composable
fun ProductItem(product: ListItemUIModel, onItemClicked: () -> Unit) {


    Card(onClick =  onItemClicked,
        modifier = Modifier.wrapContentSize().fillMaxWidth().padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.background(
                    color = Colors.IndexBackgroundColor,
                    shape = CircleShape
                ).size(48.dp)
            ) {
                Text(text = product.index.toString(), style = MaterialTheme.typography.titleLarge,  fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp), color = Color.White)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = product.name)
                Text(text = product.price, fontWeight = FontWeight.Bold)

            }
        }
    }
}


@Preview
@Composable
fun ProductItemPreview() {
    val uiRowItem = avocado
    ProductItem(uiRowItem,{})
}