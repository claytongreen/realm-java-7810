package com.green.realm_7810

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class MainActivity : AppCompatActivity() {

    private lateinit var text: TextView

    private lateinit var realm: Realm
    private var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        text = TextView(this)
        text.text = "Kotlin Compiler: ${KotlinVersion.CURRENT}"
        text.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        text.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

        val content = FrameLayout(this)
        content.addView(text)

        setContentView(content)

        Realm.init(this)
    }

    override fun onStart() {
        super.onStart()

        val config = RealmConfiguration.Builder()
            .name("realm-7810")
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .schemaVersion(1)
            .build()
        realm = Realm.getInstance(config)


        task = realm.where(Task::class.java).equalTo("name", "task").findFirst()
        if (task == null) {
            realm.executeTransaction {
                task = realm.createObject(Task::class.java, "task")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        text.text = "${text.text}\nFOUND ${task!!.name}"
    }

    override fun onStop() {
        super.onStop()

        realm.close()
    }
}


open class Task : RealmObject() {
    @PrimaryKey
    var name: String = "task"

    var status: Status
        get() = Status.valueOf(statusName)
        set(value) {
            statusName = value.name
        }

    private var statusName: String = Status.Open.name

    enum class Status {
        Open,
        InProgress,
        Complete,
    }
}
