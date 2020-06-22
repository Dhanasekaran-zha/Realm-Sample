package com.example.realm_sample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Model: RealmObject() {
    var Name:String?=null
    @PrimaryKey
    var Id:Int?=null
}