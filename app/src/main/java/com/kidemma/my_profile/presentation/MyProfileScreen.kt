package com.kidemma.my_profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.kidemma.R
import com.kidemma.common.components.KidemmaTopAppBarBack
import com.kidemma.common.ui.models.icons.IconUiModel
import com.kidemma.common.ui.theme.KidemmaColors
import com.kidemma.common.ui.theme.KidemmaDimens
import com.kidemma.common.ui.theme.KidemmaTheme
import com.kidemma.my_profile.data.MyProfileScreenOptionUiModel
import com.kidemma.my_profile.data.MyProfileScreenUiModel
import com.kidemma.my_profile.data.ProfileOptionAction
import org.koin.androidx.compose.koinViewModel


/**
 * MyProfileScreen composable is responsible for displaying the user's profile information.
 * @author Arturo Rivera Morales
 */
@Composable
fun MyProfileScreen(
    navController: NavHostController,
    viewModel: MyProfileViewModel = koinViewModel<MyProfileViewModelImpl>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val uiData = viewModel.uiData

    MyProfileContent(
        state = state,
        uiData = uiData,
        onBackClick = { navController.popBackStack() },
        onHandleIntent = { intent -> viewModel.processIntent(intent) }
    )
}

@Composable
fun MyProfileContent(
    state: MyProfileContract.State,
    onBackClick: () -> Unit,
    uiData: MyProfileScreenUiModel,
    onHandleIntent: (MyProfileContract.Intent) -> Unit
) {

    KidemmaTheme {
        Scaffold(
            topBar = {
                KidemmaTopAppBarBack(
                    title = stringResource(uiData.title),
                    onBackClick = onBackClick,
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            val scrollState = rememberScrollState()
            Column(modifier = Modifier.padding(padding)) {
                MyProfileHeader(
                    userName = state.name,
                    phoneNumber = state.phoneNumber,
                    profilePictureUrl = state.profilePictureUrl,
                    onHandleIntent = onHandleIntent
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(KidemmaDimens.SpacerLarge)
                        .padding(top = KidemmaDimens.SpacerMedium),
                    verticalArrangement = Arrangement.spacedBy(KidemmaDimens.SpacerMedium)
                ) {
                    MyProfileOptionsList(
                        options = uiData.options,
                        onHandleIntent = onHandleIntent
                    )
                }
            }

        }
    }

}


@Preview
@Composable
fun MyProfileScreenPreview() {
    MyProfileContent(
        state = MyProfileContract.State(
            name = "Arturo Rivera",
            phoneNumber = "+1 234 567 890",
        ),
        onBackClick = {},
        uiData = MyProfileScreenUiFactory.getMyProfileScreenData(isDarkThemeEnabled = false),
        onHandleIntent = {}
    )
}

@Composable
private fun MyProfileOptionsList(
    options: List<MyProfileScreenOptionUiModel>,
    onHandleIntent: (MyProfileContract.Intent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(KidemmaDimens.SpacerMedium)
    ) {
        for (option in options) {
            ProfileListItem(
                title = stringResource(option.title),
                vector = option.icon,
                actionButton = {
                    when (option.action) {
                        is ProfileOptionAction.Navigate -> IconButton(onClick = {
                            onHandleIntent(
                                option.intent
                            )
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = stringResource(R.string.my_profile_screen_forward_arrow_content_description),
                                tint = KidemmaColors.Icon
                            )
                        }

                        is ProfileOptionAction.Toggle -> {
                            var isChecked by remember { mutableStateOf(false) }
                            Switch(
                                checked = isChecked,
                                onCheckedChange = {
                                    isChecked = it
                                    onHandleIntent(option.intent)
                                },
                            )
                        }
                    }
                }
            )
        }
    }
}

/**
 * ProfileListItem composable represents a single item in the profile list.
 *
 * @author Arturo Rivera Morales
 * Created on: 15/05/26
 */
@Composable
private fun ProfileListItem(
    title: String,
    vector: IconUiModel,
    actionButton: @Composable () -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(KidemmaDimens.SpacerMedium)
            ) {
                Icon(
                    imageVector = vector.icon,
                    contentDescription = stringResource(vector.contentDescription),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color = KidemmaColors.Divider)
                        .border(2.dp, KidemmaColors.Icon, CircleShape)
                        .padding(8.dp),
                    tint = KidemmaColors.Icon
                )
                Text(title)
            }
            actionButton()
        }
        HorizontalDivider(modifier = Modifier.padding(top = KidemmaDimens.SpacerMedium))
    }
}


/**
 * MyProfileHeader composable displays the user's profile picture, name, and phone number.
 *
 * @author Arturo Rivera Morales
 * Created on: 15/05/26
 */
@Composable
private fun MyProfileHeader(
    userName: String,
    phoneNumber: String,
    profilePictureUrl: String?,
    onHandleIntent: (MyProfileContract.Intent) -> Unit
) {
    Surface(
        shadowElevation = KidemmaDimens.ElevationSmall,
        border = null,
        color = KidemmaColors.CardBackground
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.padding(start = KidemmaDimens.SpacerLarge),
                horizontalArrangement = Arrangement.spacedBy(KidemmaDimens.SpacerMedium)
            ) {
                AsyncImage(
                    model = profilePictureUrl,
                    contentDescription = stringResource(R.string.my_profile_screen_profile_picture_content_description),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, KidemmaColors.AvatarBorderColor, CircleShape),
                    placeholder = painterResource(id = R.drawable.img_father)
                )
                Column {
                    Text(userName, style = MaterialTheme.typography.labelLarge)
                    Text(phoneNumber, style = MaterialTheme.typography.bodyMedium)
                }
            }

            IconButton(onClick = {
                onHandleIntent(MyProfileContract.Intent.OnEditProfileClicked)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = stringResource(R.string.my_profile_screen_forward_arrow_content_description),
                    tint = KidemmaColors.Icon
                )
            }


        }
    }
}