package com.aispeech.validationtv.ui

import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aispeech.platform.act.BasicActivity
import com.aispeech.validationtv.R
import com.aispeech.validationtv.bean.VocabData
import com.aispeech.validationtv.bean.VocabWrapper
import com.aispeech.validationtv.databinding.ActivityLauncherBinding
import com.aispeech.validationtv.databinding.LauncherItemBinding
import com.aispeech.validationtv.ui.flow.FlowDemoActivity
import com.aispeech.validationtv.ui.other.OtherActivity
import com.aispeech.validationtv.ui.recipe.RecipeDetailActivity
import com.aispeech.validationtv.utils.startAct
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser.parseString

class LauncherActivity : BasicActivity<ActivityLauncherBinding>() {
    private val mDatas by lazy {
        mutableListOf(
            resources.getString(R.string.module_voice_recipe),
            resources.getString(R.string.module_flow),
            resources.getString(R.string.module_other)
        )
    }


    override fun initView() {
        setTitle(getString(R.string.app_name))
        binding.rvLauncher.run {
            layoutManager = LinearLayoutManager(this@LauncherActivity, RecyclerView.VERTICAL, false)
            adapter = LauncherAdapter(mDatas).apply {
                itemClick = {
                    when (it) {
                        resources.getString(R.string.module_voice_recipe) -> {
                            startAct(RecipeDetailActivity::class.java)
                        }


                        resources.getString(R.string.module_flow) -> {
                            startAct(FlowDemoActivity::class.java)
                        }

                        resources.getString(R.string.module_other) -> {
                            startAct(OtherActivity::class.java)
                        }
                    }
                }

            }
        }
//        vocabTest()
    }

    private fun vocabTest() {
        val intent = Intent()
        intent.setComponent(
            ComponentName(
                "com.aispeech.tvui",
                "com.aispeech.tvui.vocab.VocabService"
            )
        )

        val data = JsonObject().apply {
            addProperty("vocab", "美丽云南")
            val intentStr = obtainIntentData()
            addProperty("intent", Gson().toJson(intentStr))   //result 为Json字符串，具体字段请查看下面参数表
        }
        val data1 = JsonObject().apply {
            addProperty("vocab", "历史收藏")
            val intentStr = obtainIntentData()
            addProperty("intent", Gson().toJson(intentStr))   //result 为Json字符串，具体字段请查看下面参数表
        }
        val data2 = JsonObject().apply {
            addProperty("vocab", "我的应用")
            val intentStr = obtainIntentData()
            addProperty("intent", Gson().toJson(intentStr))   //result 为Json字符串，具体字段请查看下面参数表
        }
        val data3 = JsonObject().apply {
            addProperty("vocab", "桌面切换")
            val intentStr = obtainIntentData1()
            addProperty("intent", Gson().toJson(intentStr))   //result 为Json字符串，具体字段请查看下面参数表
        }
        val data4 = JsonObject().apply {
            addProperty("vocab", "系统设置")
            val intentStr = obtainIntentData()
            addProperty("intent", Gson().toJson(intentStr))   //result 为Json字符串，具体字段请查看下面参数表
        }
        val wrap = VocabWrapper(
            "美丽云南",
            VocabData(
                action = "com.avit.tv.activity.LauncherModeChooseActivity",
                data = "http://h5.ynbit.iptv/webvod/view/history/history.html",
                flags = 268435456
            )
        )
        val wrap1 = VocabWrapper(
            "历史收藏",
            VocabData(
                className = "android.browser.BROWSER",
                data = "http://h5.ynbit.iptv/webvod/view/history/history.html",
                flags = 268435456
            )
        )
        val wrap2 = VocabWrapper(
            "我的应用",
            VocabData(
                action = "android.browser.BROWSER",
                data = "http://h5.ynbit.iptv/webvod/view/history/history.html",
                flags = 268435456
            )
        )
        val wrap3 = VocabWrapper(
            "桌面切换",
            VocabData(
                action = "android.browser.BROWSER",
                data = "http://h5.ynbit.iptv/webvod/view/history/history.html",
                flags = 268435456
            )
        )
        val wrap4 = VocabWrapper(
            "系统设置",
            VocabData(
                action = "android.browser.BROWSER",
                data = "http://h5.ynbit.iptv/webvod/view/history/history.html",
                flags = 268435456
            )
        )
        val value = Gson().toJson(listOf(wrap, wrap1, wrap2, wrap3, wrap4))
        intent.putExtra("data", value)


        Log.d("XXW", value)
        startService(intent)
//        parse(intent)
    }

//    fun parse(intent: Intent) {
//        val jsonString = intent.getStringExtra("data")?.trimIndent() ?: ""
////        val jsonString ="[{\\package_name\\:\\\\,\\class_name\\:\\\\,\\action\\:\\avit.intent.action.launcher.CHOOSE\\,\\categories\\:\\\\,\\flags\\:-1,\\extras\\:{\\key\\:\\\\,\\type\\:\\String\\,\\value\\:\\\\},\\data\\:\\\\,\\type\\:\\text/plain\\}]"
////        log("[{\\package_name\\:\\\\,\\class_name\\:\\\\,\\action\\:\\avit.intent.action.launcher.CHOOSE\\,\\categories\\:\\\\,\\flags\\:-1,\\extras\\:{\\key\\:\\\\,\\type\\:\\String\\,\\value\\:\\\\},\\data\\:\\\\,\\type\\:\\text/plain\\}]")
//        // 使用 Gson 解析整个 JSON 数组
//        val gson = Gson()
//
//        try {
//            val vocabList = gson.fromJson(jsonString.trimIndent(), Array<VocabWrapper>::class.java)
//
//            // 遍历解析后的数据
//            vocabList.forEach { vocabItem ->
//                val vocab = vocabItem.vocab  // 提取 vocab
//                val intentString = vocabItem.intent  // 获取 intent 字符串
//                log("vocab $vocab")
//                log("intentString $intentString")
//                // 解析 intent 字段
//                val intentArray = parseString(intentString).asJsonArray
//
//                // 将 intentArray 转换为 VocabData 列表
//                val intentDataList = intentArray.map { intentObject ->
//                    // 提取 extras 字段的 JSON 字符串
//                    val extrasJsonString = intentObject.asJsonObject.get("extras")?.asString ?: ""
//
//                    // 将 extras 字符串解析为 Extra 列表
//                    val extrasList = if (extrasJsonString.isNotEmpty()) {
//                        val extrasArray = JsonParser.parseString(extrasJsonString).asJsonArray
//                        gson.fromJson(extrasArray, Array<Extra>::class.java).toList()
//                    } else {
//                        emptyList()
//                    }
//
//                    // 将 intentObject 解析为 VocabData，并手动设置 extras
//                    val vocabData = gson.fromJson(intentObject, VocabData::class.java)
//                    vocabData.extraList = extrasList
//
//                    vocabData
//                }
//
//                // 输出结果
//                log("Vocab: $vocab")
//                intentDataList.forEach { intentData ->
//                    log("Parsed Intent Data: $intentData")
//                    log(intentData.packageName ?: "")
//                    log(intentData.action ?: "")
//                }
//            }
//        } catch (e: JsonSyntaxException) {
//            log("JSON parsing error: ${e.message}")
//        } catch (e: Exception) {
//            log("An error occurred: ${e.message}")
//        }
//    }

    private fun test(intentData: String) {
        // 第一步：使用Gson解析整个JSON数组
        val gson = Gson()
        val jsonArray = gson.fromJson(intentData, JsonArray::class.java)
        val datas = mutableListOf<JsonObject>()
        // 遍历数组，解析每个对象
        for (element in jsonArray) {
            val jsonObject = element.asJsonObject

            // 提取intent字段并解析为JSON数组
            val intentString = jsonObject.get("intent").asString  // 这是带双引号的JSON字符串
            val intentJsonArray = parseString(intentString).asJsonArray  // 解析为JSON数组

            // 将intent字段重新放回到jsonObject中，不再是字符串，而是解析后的JSON数组
            jsonObject.add("intent", intentJsonArray)

            // 打印最终的json对象
            log(gson.toJson(jsonObject))
            datas.add(jsonObject)
        }

        val vocabs =
            datas.filter { it.has("vocab") }.map { it.get("vocab").toString() }
    }

    private fun log(msg: String) {
        Log.d("XXW", msg)
    }

    private fun obtainIntentData(): List<JsonObject> {
        val list = mutableListOf<JsonObject>()
        list.add(JsonObject().apply {
            //应用包名，可以为空, 属于ComponentName的属性
            addProperty("package_name", "cn.com.avit.tv.launcher.ynbit")
            //Activity类名， 可以为空, 属于ComponentName的属性class_name为空时，action不得为空。
            addProperty("class_name", "com.avit.tv.activity.LauncherModeChooseActivity")
            //可以为任意字符串或空。action为空时，package_name和class_name都不得为空。
            // 默认可以提供系统定义的action供选择，见Intent.java所有以ACTION_开头的常量。
            // 常用值: Intent.ACTION_MAIN, Intent.ACTION_VIEW
//            addProperty("action", "avit.intent.action.launcher.CHOOSE")
            //该分类用于修饰Action，可以为任意字符串或空，但有一些固定值可供选择，见Intent.java所有以CATEGORY_开头的常量。常用值:Intent.CATEGORY_DEFAULT, Intent.CATEGORY_LAUNCHER
//            addProperty("categories", "android.intent.category.DEFAULT")
            //整形值，由多个FLAG按位或得到。见Intent.java所有以FLAG_开头的常量
            addProperty("flags", 268435456)
//            val extras = listOf(JsonObject().apply {
//                addProperty("key", "")
//                addProperty("type", "")
//                addProperty("value", "")
//            })
//            addProperty("extras",Gson().toJson(extras))
//            //参数
////            add("extras", )
//            //需要能被当做Uri解析出来
//            addProperty("data", "")
//            //MIME type，例如text/plain。用于说明data的类型，若data为空时，此值必定为空。可以为自定义值。
//            addProperty("type", "")
        })
        list.add(JsonObject().apply {

        })
        return list
    }

    private fun obtainIntentData1(): List<JsonObject> {
        val list = mutableListOf<JsonObject>()
        list.add(JsonObject().apply {
            //应用包名，可以为空, 属于ComponentName的属性
            addProperty("package_name", "cn.com.avit.tv.launcher.ynbit")
            //Activity类名， 可以为空, 属于ComponentName的属性class_name为空时，action不得为空。
//            addProperty("class_name", "com.avit.tv.activity.LauncherModeChooseActivity")
            //可以为任意字符串或空。action为空时，package_name和class_name都不得为空。
            // 默认可以提供系统定义的action供选择，见Intent.java所有以ACTION_开头的常量。
            // 常用值: Intent.ACTION_MAIN, Intent.ACTION_VIEW
            addProperty("action", "avit.intent.action.launcher.CHOOSE")
            //该分类用于修饰Action，可以为任意字符串或空，但有一些固定值可供选择，见Intent.java所有以CATEGORY_开头的常量。常用值:Intent.CATEGORY_DEFAULT, Intent.CATEGORY_LAUNCHER
//            addProperty("categories", "android.intent.category.DEFAULT")
            //整形值，由多个FLAG按位或得到。见Intent.java所有以FLAG_开头的常量
            addProperty("flags", 268435456)
//            val extras = listOf(JsonObject().apply {
//                addProperty("key", "")
//                addProperty("type", "")
//                addProperty("value", "")
//            })
//            addProperty("extras",Gson().toJson(extras))
//            //参数
////            add("extras", )
//            //需要能被当做Uri解析出来
//            addProperty("data", "")
//            //MIME type，例如text/plain。用于说明data的类型，若data为空时，此值必定为空。可以为自定义值。
//            addProperty("type", "")
        })
        list.add(JsonObject().apply {

        })
        return list
    }

    override fun initData() {
    }

    internal class LauncherAdapter(var data: List<String>) :
        RecyclerView.Adapter<LauncherViewHolder>() {

        var itemClick: (String) -> Unit = { }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherViewHolder {
            val root =
                LayoutInflater.from(parent.context).inflate(R.layout.launcher_item, parent, false)
            return LauncherViewHolder(LauncherItemBinding.bind(root))
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: LauncherViewHolder, position: Int) {
            holder.binding.tvTitle.text = data[position]
            holder.binding.root.setOnClickListener {
                itemClick.invoke(data[position])
            }
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("XXW", "keyCode $keyCode")
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            Log.d("XXW", "keyCode $keyCode")
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    internal class LauncherViewHolder(val binding: LauncherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}