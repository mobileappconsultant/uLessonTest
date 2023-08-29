package com.arkangel.ulessontechnicaltest.android.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.arkangel.ulessontechnicaltest.android.R

@Composable
fun SearchBox(
    value: String,
    placeholder: @Composable () -> Unit,
    onValueChange: (value: String) -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(24.dp)),
        value = value,
        placeholder = placeholder,
        onValueChange = onValueChange,
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search")
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}
