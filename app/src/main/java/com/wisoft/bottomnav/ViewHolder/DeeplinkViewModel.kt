package com.wisoft.bottomnav.ViewHolder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//存放 Deeplink 的參數
class DeeplinkViewModel:  ViewModel() {
    private var _actionData : MutableLiveData<String> = MutableLiveData<String>()
    private var _actionView : MutableLiveData<String> = MutableLiveData<String>()
    private var _networkIsAvaiable : MutableLiveData<String> = MutableLiveData<String>()

    init {
        _actionData.value = ""
        _actionView.value = ""
        _networkIsAvaiable.value = ""
    }

    fun setActionViewValue(actionDataValue: String) {
        _actionView.value = actionDataValue
    }

    fun getActionViewValue(): MutableLiveData<String> {
        return _actionView
    }

    fun setActionDataValue(actionDataValue: String) {
        _actionData.value = actionDataValue
    }

    fun getActionDataValue(): MutableLiveData<String> {
        return _actionData
    }

    fun setNetworkIsAvailableValue(actionDataValue: String) {
        _networkIsAvaiable.value = actionDataValue
    }

    fun getNetworkIsAvailableValue(): MutableLiveData<String> {
        return _networkIsAvaiable
    }
}