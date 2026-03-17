package com.kidemma.profile_menu.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kidemma.R
import com.kidemma.common.components.KidemmaOutlinedTextField
import com.kidemma.common.components.KidemmaPrimaryButton
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = viewModel()
) {
    EditProfileContent(viewModel)
}

@Composable
fun EditProfileContent(viewModel: EditProfileViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        Modifier
            .fillMaxSize()
            .background(KidemmaColors.Background)
            .padding(horizontal = KidemmaDimens.GeneralPaddingFillMaxSize)
            .padding(
                top = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(Modifier.size(160.dp), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = "image profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(KidemmaColors.Secondary)
                    .border(1.dp, color = KidemmaColors.BorderStroke, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.img_camera),
                    contentDescription = "change photo",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        KidemmaOutlinedTextField(
            label = R.string.edit_profile_email_text_field,
            value = "hola@hotmail.com",
            onValueChange = {},
            enabled = false
        )
        KidemmaOutlinedTextField(
            label = R.string.edit_profile_full_name_text_field,
            value = state.fullName,
            onValueChange = { viewModel.onIntent(EditProfileContract.Intent.OnFullNameChange(it)) }
        )
        KidemmaOutlinedTextField(
            label = R.string.edit_profile_main_phone_text_field,
            value = state.mainPhone,
            onValueChange = {
                if (it.length <= 10) {
                    viewModel.onIntent(EditProfileContract.Intent.OnMainPhoneChange(it))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        KidemmaOutlinedTextField(
            label = R.string.edit_profile_additional_phone_text_field,
            value = state.addtionalPhone,
            onValueChange = {
                if (it.length <= 9) {
                    viewModel.onIntent(
                        EditProfileContract.Intent.OnAdditionalPhoneChange(
                            it
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        KidemmaPrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            text = "Actualizar datos",

            ) { }

    }

}


@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    EditProfileScreen()

}