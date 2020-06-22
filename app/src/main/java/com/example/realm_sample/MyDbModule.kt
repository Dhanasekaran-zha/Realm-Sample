package com.example.realm_sample

import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule

@RealmModule(library = true, classes = [Model::class])
class MyDbModule{
}