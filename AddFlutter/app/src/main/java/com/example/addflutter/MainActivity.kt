package com.example.addflutter

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.addflutter.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFlutterEngine()
    }

    override fun onResume() {
        super.onResume()
        setViews()
    }

    private fun setViews() {
        binding.apply {
            button1.setOnClickListener {
                startActivity(
                    /**
                     * initial flutter route   -> '/'
                     */
                    FlutterActivity.createDefaultIntent(this@MainActivity)
                )
            }
            button2.setOnClickListener {
                startActivity(
                    /**
                     * initial flutter route   -> '/my_route'
                     */
                    FlutterActivity
                        .withNewEngine()
                        .initialRoute("/my_route")
                        .build(this@MainActivity)
                )
            }
            button3.setOnClickListener {
                startActivity(
                    /**
                     *
                     * pre-warm flutter engine
                     * 減少啟動FlutterActivity 時的delay
                     */
                    FlutterActivity
                        .withCachedEngine(App.MY_ENGINE_ID)
                        .build(this@MainActivity)
                )
            }
        }
    }

    private fun initFlutterEngine() {
        MethodChannel(
            App.flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                CHANNEL_METHOD_GET_URL -> {
                    result.success(binding.editText.text.toString())

//                    Thread {
//                        result.success(binding.editText.text.toString())
//                    }.start()

//                    runOnUiThread {
//                        result.success(binding.editText.text.toString())
//                    }
                }
                else -> result.notImplemented()
            }
        }
    }
}