package com.d121211079.maps.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Maps(
    val assetPath: String?,
    val coordinates: String?,
    val displayIcon: String?,
    val displayName: String?,
    val listViewIcon: String?,
    val mapUrl: String?,
    val narrativeDescription: String?,
    val splash: String?,
    val tacticalDescription: String?,
    val uuid: String?,
    val xMultiplier: Double?,
    val xScalarToAdd: Double?,
    val yMultiplier: Double?,
    val yScalarToAdd: Double?
): Parcelable