package com.keisardev.supersmartexample.feature_add_item.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.keisardev.supersmartexample.feature_add_item.ItemTypeState


@Composable
fun AmountTypeSelection(onClick: (ItemTypeState) -> Unit) {
    val unitItem = Pair("Unit item (quantity)", ItemTypeState.QuantityItem)
    val weightItem = Pair("Weighted item", ItemTypeState.WeightItem)

    val radioOptions = listOf<Pair<String, ItemTypeState>>(unitItem, weightItem)
//    val radioOptions = listOf(unitItem.first,weightItem.first )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),

        // below line is used for
        // specifying vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for
        // specifying horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // we are displaying all our
        // radio buttons in column.
        Column {
            // below line is use to set data to
            // each radio button in columns.
            radioOptions.forEach { item ->
                Row(
                    Modifier
                        // using modifier to add max
                        // width to our radio button.
                        .fillMaxWidth()
                        .wrapContentHeight()
                        // below method is use to add
                        // selectable to our radio button.
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (item == selectedOption),
                            // below method is called on
                            // clicking of radio button.
                            onClick = { onOptionSelected(item) }
                        )
                        // below line is use to add
                        // padding to radio button.
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // below line is use to get context.
                    val context = LocalContext.current

                    // below line is use to
                    // generate radio button
                    RadioButton(
                        // inside this method we are
                        // adding selected with a option.
                        selected = (item == selectedOption),
                        modifier = Modifier.padding(all = 2.dp),
                        onClick = {
                            // inside on click method we are setting a
                            // selected option of our radio buttons.
                            onOptionSelected(item)
                            onClick(item.second)


                        }
                    )
                    // below line is use to add
                    // item to our radio buttons.
                    Text(
                        text = item.first,
                        modifier = Modifier.padding(start = 2.dp).wrapContentHeight()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AmountTypeSelectionPreview() {
    AmountTypeSelection({})
}
