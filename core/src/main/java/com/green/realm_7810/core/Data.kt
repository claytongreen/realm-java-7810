package com.green.realm_7810.core

import io.realm.Realm
import io.realm.RealmConfiguration

fun openRealm(): Realm {
    val config = RealmConfiguration.Builder()
        .name("realm-7810")
        .allowQueriesOnUiThread(true)
        .allowWritesOnUiThread(true)
        .schemaVersion(1)
        .build()
    return Realm.getInstance(config)
}

//fun getTask(): Task {
//    val realm = openRealm()
//
//    var task = realm.where(Task::class.java).findFirst()
//    if (task != null) return task
//
//    realm.executeTransaction {
//        task = realm.createObject(Task::class.java)
//    }
//
//    return task
//}