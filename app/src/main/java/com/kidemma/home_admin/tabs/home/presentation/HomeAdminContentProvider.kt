package com.kidemma.home_admin.tabs.home.presentation

import com.kidemma.R
import com.kidemma.common.models.HeaderElementUiModel

/*
 * File: HomeAdminContentProvider
 * Description: Clase temporal que provee de información a HomeAdminTabScreen.kt mientras se espera conexion
 * con firebase
 *
 * Created by: Javier Cuéllar
 * Created on: 26/02/26
 * Last modified: 09/03/26
 */

object HomeAdminContentProvider {

    val classStatusHeader = HeaderElementUiModel(
        title = R.string.class_status_cardview_title,
        headerIconID = R.drawable.ic_status,
        headerIconDescription = R.string.class_status_cardview_icon)

    val paymentStatusHeader = HeaderElementUiModel(
        title = R.string.payment_status_cardview_title,
        headerIconID = R.drawable.ic_payment_status,
        headerIconDescription = R.string.payment_status_cardview_icon)

    val complaintsHeader = HeaderElementUiModel(
        title = R.string.complaints_cardview_title,
        headerIconID = R.drawable.ic_complaints,
        headerIconDescription = R.string.complaints_cardview_icon)
}