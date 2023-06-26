# realm-java-7810
Repro for https://github.com/realm/realm-java/issues/7810

There are 2 app modules: `kotlin1810` and `kotlin1821`

The only difference between the two is what version of Kotlin they compile with.\
To reproduce the bug, build and run `kotlin1810` to build the `Task` model using Kotlin 1.8.10 and it will create a Realm and save a single `Task` to it.\
Then build and run `kotlin1820` to build the `Task` model using Kotlin 1.8.21. It should crash with:

```FATAL EXCEPTION: main
Process: com.green.realm_7810, PID: 12102
io.realm.exceptions.RealmMigrationNeededException: Migration is required due to the following errors:
- Property 'Task.statusName' has been made required.
  at io.realm.internal.OsSharedRealm.nativeGetSharedRealm(Native Method)
  at io.realm.internal.OsSharedRealm.<init>(OsSharedRealm.java:175)
  at io.realm.internal.OsSharedRealm.getInstance(OsSharedRealm.java:260)
  at io.realm.BaseRealm.<init>(BaseRealm.java:142)
  at io.realm.BaseRealm.<init>(BaseRealm.java:109)
  at io.realm.Realm.<init>(Realm.java:161)
  at io.realm.Realm.createInstance(Realm.java:535)
  at io.realm.RealmCache.createInstance(RealmCache.java:508)
  at io.realm.RealmCache.doCreateRealmOrGetFromCache(RealmCache.java:461)
  at io.realm.RealmCache.createRealmOrGetFromCache(RealmCache.java:422)
  at io.realm.Realm.getInstance(Realm.java:464)
  at com.green.realm_7810.MainActivity.onStart(MainActivity.kt:45)
  at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1543)
  at android.app.Activity.performStart(Activity.java:8330)
  at android.app.ActivityThread.handleStartActivity(ActivityThread.java:3670)
  at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:221)
  at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:201)
  at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:173)
  at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:97)
  at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2307)
  at android.os.Handler.dispatchMessage(Handler.java:106)
  at android.os.Looper.loopOnce(Looper.java:201)
  at android.os.Looper.loop(Looper.java:288)
  at android.app.ActivityThread.main(ActivityThread.java:7872)
  at java.lang.reflect.Method.invoke(Native Method)
  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:936)
```
