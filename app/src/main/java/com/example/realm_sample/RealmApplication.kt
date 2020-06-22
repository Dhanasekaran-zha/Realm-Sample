package com.example.realm_sample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class RealmApplication : Application() {
    var realm: Realm? = null

    companion object {
        var instance: RealmApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("myDB.realm")
            .schemaVersion(1)
            .modules(MyDbModule())
            .build()
        realm = Realm.getInstance(config);
    }

    fun getRealmInstance(): Realm {
        return realm!!
    }
}