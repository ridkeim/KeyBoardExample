package ru.ridkeim.keyboardexample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alertDialog = AlertDialog.Builder(this)
            .setTitle("Выход: Вы уверены?")
            .setPositiveButton("Да") { _, _ ->
                Toast.makeText(this, "Ок. Пока",Toast.LENGTH_SHORT).show()
                finish() }
            .setNegativeButton("Нет") { dialog, _ ->
                Toast.makeText(this, "Ок.",Toast.LENGTH_SHORT).show()
                dialog.dismiss() }
            .create()

        val listener1 = TextView.OnEditorActionListener { v, actionId, event ->
            Timber.d("Listener 1 $actionId $event")
            false
        }

        val listener2 = object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                Timber.d("Listener 2 $actionId $event")
                return false
            }
        }

        val listener3 = { v : TextView, actionId : Int, event : KeyEvent? ->
            Timber.d("Listener 3 $actionId $event")
            false
        }

        editTextPersonName.setOnEditorActionListener(listener1)
        editTextPersonName2.setOnEditorActionListener(listener2)
        editTextPersonSurname.setOnEditorActionListener(listener3)

        editTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Timber.d("bTC $s start:$start count:$count after:$after")
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Timber.d("oTC $s start:$start before:$before count:$count")
            }

            override fun afterTextChanged(s: Editable?) {
                Timber.d("aTC $s")
            }

        })
    }

    override fun onBackPressed() {
        alertDialog.show()
        Timber.d("onBackPressed")
    }

    override fun onUserLeaveHint() {
        Timber.d("onUserLeaveHint")
        super.onUserLeaveHint()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_MENU -> Timber.d("MenuKey")
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Timber.d("VolumeUpKey")
                return true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Timber.d("VolumeDownKey")
                return true
            }
            KeyEvent.KEYCODE_BACK -> Timber.d("BackKey")
            KeyEvent.KEYCODE_HOME -> Timber.d("HomeKey")
        }
        return super.onKeyDown(keyCode, event)
    }


}