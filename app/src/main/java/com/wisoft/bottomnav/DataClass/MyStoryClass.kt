package com.wisoft.bottomnav.DataClass

class MyStoryClass {
    var nIndex: Long = 0
    var IDNumber: String = ""
    var FirstName: String = ""
    var lastName: String = ""
    var Address: String = ""
    var PhoneNumber: String = ""
    var SignatureFileName: String = ""
    var isSelected: Boolean = false

    constructor(nIndex: Long, IDNumber: String, FirstName: String, lastName: String, Address:String, PhoneNumber: String, SignatureFileName: String, isChecked: Boolean)
    {
        this.nIndex= nIndex
        this.IDNumber=IDNumber
        this.FirstName = FirstName
        this.lastName = lastName
        this.Address = Address
        this.PhoneNumber = PhoneNumber
        this.SignatureFileName = SignatureFileName
    }
    fun getnIndex(): Long {
        return nIndex
    }

    fun setnIndex(nIndex: Long) {
        this.nIndex = nIndex
    }

    fun getSelecteds(): Boolean {
        return isSelected
    }

    fun setSelecteds(selected: Boolean) {
        isSelected = selected
    }
}