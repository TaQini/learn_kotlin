package com.taqini.testagain
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import org.jetbrains.anko.longToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_ok.setOnClickListener {
            if(hello.text != "I Love Nier"){
                hello.text = "I Love Nier"
                button_ok.text = "Dinger:"
                toast("Dinger say ...")
            }else{
                hello.text = "I Love Dinger"
                button_ok.text = "Nier:"
                longToast("NIer say ...")
            }
        }
        var i = 0
        val s = "12345.678"
        var a:IntArray = intArrayOf(1,2,3)
        var b:MutableList<Int> = mutableListOf(1,2,4,8)
        val phone:List<String> = listOf("Oneplus 6", "iPhone 8", "Huawei P30")
        val marks:Map<String,Int> = mapOf("Biua" to 67, "Civa" to 89, "Diua" to 78)
        a[1] = 0
        button_ok.setOnLongClickListener{
            i += 1
            var c:List<String> = s.split(".")
            for(i in c) {
                a[1] += add10(i.toInt())
            }
            var desc=""
            val p = phone.iterator()
            while(p.hasNext()){
                val i = p.next()
                desc += "phone: $i\n"
            }
            phone.forEach { i ->
                desc += "phone2: $i\n"
            }
            for(i in phone.indices){
                desc += "phone3: ${phone[i]}\n"
            }
            marks.forEach{ i ->
                desc += "mark: ${i.key}:${i.value}\n"
            }
            hello.text = "long press ${a[1]} times,\n b[1]=${b[1]}\n$desc"
            true
        }
        var count = 0
        button_ex.setOnClickListener {
            hello.text = when (count){
                1,3,5,7,9 -> "$count is even"
                0,2,4,6,8 -> "$count is odd"
                !in 0 .. 9 -> "$count is not in range 0-9"
                else -> "wrong!"
            }
            count = (count + 1) % 12
        }
        button_ex.setOnLongClickListener {
            var n:String? = null
            hello.text = "$n.length=${n?.length?:-1}"
            var l:Int = if (n!=null) n.length else -1
            var ll = listOf('a','2',1)
            hello.text = "$n.length=$l\n"
            hello.text = "${hello.text}sum(1..9)=${sum3plusDigit(1,2,3,4,5,6,7,8,9)}\n"
            hello.text = "${hello.text}sum(1,2,3)=${sum3plusDigit(1,2,3)}\n"
            hello.text = "${hello.text}${appendStrings("str=","abc",123,true,'c',null,"\n//--byAppendString")}\n"
            for(i in ll){
                hello.text = "${hello.text}$i\n"
            }
            true
        }

    }
    inline fun <reified T : Number> setArrayStr(array:Array<T>) {
        var str:String = "item in array are: "
        for (item in array) {
            str = "$str${item.toString()}, "
        }
        hello.text = str
    }
}

fun add10(n:Int):Int{
    return n+10
}

fun sum3plusDigit(a:Int, b:Int, c:Int, vararg otherInfo: Int):Int{
    var sum = 0
    sum += a + b + c
    for(i in otherInfo){
        sum += i
    }
    return sum
}

fun <T> appendStrings(tag:String, vararg otherInfo:T?):String{
    var str ="$tag"
    for(i in otherInfo){
        str = "$str${i.toString()}"
    }
    return str
}
