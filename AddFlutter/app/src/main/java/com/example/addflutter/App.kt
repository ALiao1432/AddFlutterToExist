package com.example.addflutter

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class App : Application() {

    companion object {
        const val MY_ENGINE_ID = "MY_ENGINE_ID"
        lateinit var flutterEngine: FlutterEngine
    }

    override fun onCreate() {
        super.onCreate()

        flutterEngine = FlutterEngine(this).apply {
            navigationChannel.setInitialRoute("/pre_warm_route")

            /**
             * 一旦執行 executeDartEntrypoint()
             * dart entrypoint 就會開始執行
             * 如果entrypoint 內呼叫了runApp()
             * flutter app 就會像是執行在一個width = 0, height = 0 的視窗裡
             */
            dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
        }
        FlutterEngineCache.getInstance().put(MY_ENGINE_ID, flutterEngine)
    }
}