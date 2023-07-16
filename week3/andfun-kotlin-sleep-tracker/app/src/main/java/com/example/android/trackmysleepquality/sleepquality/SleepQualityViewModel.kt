/*
 * Copyright 2018, The Android Open Source Project
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

package com.example.android.trackmysleepquality.sleepquality

import android.text.Editable
import android.widget.EditText
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * ViewModel for SleepQualityFragment.
 *
 * @param sleepNightKey The key of the current night we are working on.
 */
class SleepQualityViewModel(

        private val sleepNightKey: Long = 0L,

        val database: SleepDatabaseDao) : ViewModel() {


    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    // 把 sleepInfo 設定成 MutableLiveData ，直接存取 Input，因此設定為 String Type
    var sleepInfo = MutableLiveData<String>()


    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker


    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    /**
     * Sets the sleep quality and updates the database.
     *
     * Then navigates back to the SleepTrackerFragment.
     */
    fun onSetSleepQuality(quality: Int) {
        viewModelScope.launch {
                val tonight = database.get(sleepNightKey) ?: return@launch
                tonight.sleepQuality = quality
                tonight.sleepInfo = sleepInfo.value.toString() // 因為database 不允許空值，因此把String? 強制轉為 String
                database.update(tonight)

            _navigateToSleepTracker.value = true
        }
    }
}

