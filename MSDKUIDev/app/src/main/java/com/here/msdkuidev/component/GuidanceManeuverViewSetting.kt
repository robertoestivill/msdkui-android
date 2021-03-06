/*
 * Copyright (C) 2017-2020 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.here.msdkuidev.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContextCompat
import com.here.msdkui.guidance.GuidanceManeuverData
import com.here.msdkuidev.R
import com.here.msdkuidev.Setting
import com.here.msdkuidev.SettingItem

class GuidanceManeuverViewSetting() : Setting<GuidanceManeuverView>() {

    class GuidanceManeuverViewSettingItem : SettingItem {

        // data customization
        var state: com.here.msdkui.guidance.GuidanceManeuverView.State? = null
        var defaultView: Boolean? = null
        var noDistance: Boolean? = null
        var customTheme : Int ? = null
        var direction : Int ? = 0
        var highlight : Boolean ? = null

        constructor() : super()

        constructor(parcel: Parcel) : super(parcel) {
            state = parcel.readParcelable(com.here.msdkui.guidance.GuidanceManeuverView.State::class.java.classLoader)
            defaultView = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
            noDistance = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
            customTheme = parcel.readValue(Int::class.java.classLoader) as? Int
            direction = parcel.readValue(Int::class.java.classLoader) as? Int
            highlight = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeParcelable(state, flags)
            parcel.writeValue(defaultView)
            parcel.writeValue(noDistance)
            parcel.writeValue(customTheme)
            parcel.writeValue(direction)
            parcel.writeValue(highlight)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<GuidanceManeuverViewSettingItem> {
            override fun createFromParcel(parcel: Parcel): GuidanceManeuverViewSettingItem {
                return GuidanceManeuverViewSettingItem(parcel)
            }

            override fun newArray(size: Int): Array<GuidanceManeuverViewSettingItem?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun getClassName(): Class<GuidanceManeuverView> {
        return GuidanceManeuverView::class.java
    }

    override fun getItems(context: Context): LinkedHashMap<String, SettingItem> {
        val bitmap = context.getImage(R.drawable.ic_panorama_fish_eye_black_24dp).toBitmap()
        val info1 = "Exit Highway"
        val info2 = "Lindenstraße 110"
        val iconId = R.drawable.ic_maneuver_icon_15
        val distance:Long = 2000
        return linkedMapOf(
            "Default or No data state, horizontal" to GuidanceManeuverViewSettingItem().apply {
                defaultView = true
            },

            "Set null data, horizontal" to GuidanceManeuverViewSettingItem().apply {
                defaultView = null
                state = null
            },

            "Updating, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State.UPDATING
            },

            "With all properties, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
            },

            "With long info1, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, "this is long information to test multi line. This is only for testing purpose", info2, bitmap)
                )
            },

            "With long info2, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, "this is long information to test multi line. This is only for testing purpose", bitmap)
                )
            },

            "With highlight info2, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
                highlight = true
            },

            "Maneuver icon gone, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    0, distance, info1, info2, bitmap)
                )
            },

            "Distance gone, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, null, info1, info2, bitmap)
                )
                noDistance = true
            },

            "Info1 gone, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, null, info2, bitmap)
                )
            },

            "Info2 gone, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, null, bitmap)
                )
            },

            "Without road icon, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, null)
                )
            },

            "With all red, horizontal" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
                customTheme = R.style.GuidanceManeuverViewRed
            },

            "Default or No data state, vertical" to GuidanceManeuverViewSettingItem().apply {
                defaultView = true
                direction = 1
            },

            "Set null data, vertical" to GuidanceManeuverViewSettingItem().apply {
                defaultView = null
                state = null
                direction = 1
            },

            "Updating, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State.UPDATING
                direction = 1
            },

            "With all properties, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
                direction = 1
            },

            "With highlight info2, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
                highlight = true
                direction = 1
            },

            "Maneuver icon gone, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    0, distance, info1, info2, bitmap)
                )
                direction = 1
            },

            "Distance gone, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, null, info1, info2, bitmap)
                )
                noDistance = true
                direction = 1
            },

            "Info1 gone, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, null, info2, bitmap)
                )
                direction = 1
            },

            "Info2 gone, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, null, bitmap)
                )
                direction = 1
            },

            "Without road icon, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, null)
                )
                direction = 1
            },

            "With all red, vertical" to GuidanceManeuverViewSettingItem().apply {
                state = com.here.msdkui.guidance.GuidanceManeuverView.State(GuidanceManeuverData(
                    iconId, distance, info1, info2, bitmap)
                )
                direction = 1
                customTheme = R.style.GuidanceManeuverViewRedScreen2
            }
        )
    }


    private fun Drawable.toBitmap(): Bitmap {
        if (this is BitmapDrawable) {
            return bitmap
        }
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        setBounds(0, 0, canvas.width, canvas.height)
        draw(canvas)
        return bitmap
    }

    private fun Context.getImage(id: Int): Drawable {
        return ContextCompat.getDrawable(this, id)!!
    }
}