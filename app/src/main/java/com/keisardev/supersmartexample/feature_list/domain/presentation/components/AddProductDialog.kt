@file:OptIn(ExperimentalMaterial3Api::class)

package com.keisardev.supersmartexample.feature_list.domain.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.keisardev.supersmartexample.R
import com.keisardev.supersmartexample.feature_add_item.ItemTypeState
import com.keisardev.supersmartexample.feature_add_item.presentation.AmountTypeSelection
import com.keisardev.supersmartexample.feature_list.domain.DialogProductModel
import com.keisardev.supersmartexample.ui.theme.Colors

@Composable
fun AddProductDialog(
    index: MutableState<Int>,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onConfirm: (item: DialogProductModel) -> Unit
) {
    val productName = remember { mutableStateOf("") }
    val amount = remember {
        mutableStateOf<String>("")
    }

    Dialog(onDismissRequest = { /*TODO*/ }) {
        ElevatedCard(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxHeight(0.85f)) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                DialogTopSection(index)
                DialogMiddleSection(productName, amount)
                DialogLowerSection(
                    onDelete = { onDelete() },
                    onConfirm = {
                        onConfirm(
                            DialogProductModel(
                                name = productName.value,
                                amount = amount.value
                            ),
                        )
                    })

            }
        }
    }
}

@Composable
fun DialogTopSection(index: MutableState<Int>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .background(Colors.ImageBackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Colors.IndexBackgroundColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(index.value.toString())

        }
        //todo: by item type
        Image(
            painter = painterResource(id = R.drawable.ic_unit_item),
            contentDescription = "dismiss_button",
            modifier = Modifier.fillMaxWidth(0.4f).padding(top = 14.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.ExitButton,
                contentColor = Color.Black
            ),
            modifier = Modifier.size(40.dp)
        ) {
            Icon(Icons.Filled.Close, contentDescription = "close",modifier = Modifier.fillMaxSize())
//            Image(
//                painter = painterResource(id = R.drawable.ic_dismiss),
//                contentDescription = "dismiss_button",
//                modifier = Modifier.size(1000.dp)
//            )
        }
    }
}

@Composable
fun DialogMiddleSection(productName: MutableState<String>, currentAmount: MutableState<String>) {
    val itemTypeState = remember { mutableStateOf<ItemTypeState>(ItemTypeState.QuantityItem) }

    val buttonModifier = Modifier
        .background(Color(184, 184, 184))
        .size(30.dp)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextField(value = productName.value, onValueChange = { productName.value = it },
            placeholder = {
                Text(text = "Please enter product name")
            })
        AmountTypeSelection() {
            itemTypeState.value = it
        }
        Spacer(modifier = Modifier.height(40.dp))
        Card() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(184, 184, 184))
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(4.dp)

            ) {
                when (itemTypeState.value) {
                    ItemTypeState.QuantityItem -> UnitItem(buttonModifier, currentAmount)

                    ItemTypeState.WeightItem -> WeightItem(buttonModifier, currentAmount)
                }

            }
        }


    }
}

@Composable
fun WeightItem(buttonModifier: Modifier, currentAmount: MutableState<String>) {
    val weight = remember {
        mutableStateOf(1.00)
    }
    Card(
        onClick = {
            if (weight.value != 0.00) {
                weight.value -= 0.5
                currentAmount.value = weight.value.toString()
            }
        },
        buttonModifier
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_minus),
            contentDescription = "minus button"
        )
    }
    Text(text = weight.value.toString())
    Card(onClick = {
        weight.value += 0.5
        currentAmount.value = weight.value.toString()

    }, buttonModifier) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "plus button"
        )
    }
}

@Composable
fun UnitItem(buttonModifier: Modifier, currentAmount: MutableState<String>) {
    val quantity = remember {
        mutableStateOf(1)
    }
    Card(
        onClick = {
            if (quantity.value != 0) {
                quantity.value -= 1
                currentAmount.value = quantity.value.toString()

            }
        },
        modifier = buttonModifier
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_minus),
            contentDescription = "minus button"
        )
    }
    Text(text = quantity.value.toString())
    Card(
        onClick = {
            quantity.value += 1
            currentAmount.value = quantity.value.toString()

        }, modifier = buttonModifier
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "plus button"
        )
    }
}


@Composable
fun DialogLowerSection(
    onDelete: () -> Unit,
    onConfirm: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { onDelete() },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.trashCanBackground)
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.ic_trash_can),
                contentDescription = "trash button"
            )

        }
        Button(
            onClick = { onConfirm() },
            colors = ButtonDefaults.buttonColors(containerColor = Colors.confirmBackground),
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Text(text = stringResource(R.string.confirm))

        }
    }
}


@Preview
@Composable
fun AddProductDialogPreview() {
    val itemIndex = remember {
        mutableStateOf(1)
    }
    AddProductDialog(itemIndex, {}, {}, {})
}
