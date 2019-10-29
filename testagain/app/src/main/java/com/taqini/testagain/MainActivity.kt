package com.taqini.testagain
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.cos

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

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
        val a:IntArray = intArrayOf(1,2,3)
        val b:MutableList<Int> = mutableListOf(1,2,4,8)
        val phone:List<String> = listOf("Oneplus 6", "iPhone 8", "Huawei P30")
        val marks:Map<String,Int> = mapOf("Biua" to 67, "Civa" to 89, "Diua" to 78)
        a[1] = 0
        button_ok.setOnLongClickListener{
            i += 1
            val c:List<String> = s.split(".")
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
            val n:String? = null
            hello.text = "$n.length=${n?.length?:-1}"
            val l:Int = n?.length ?: -1
            val ll = listOf('a','2',1)
            hello.text = "$n.length=$l\n"
            hello.text = "${hello.text}sum(1..9)=${sum3plusDigit(1,2,3,4,5,6,7,8,9)}\n"
            hello.text = "${hello.text}sum(1,2,3)=${sum3plusDigit(1,2,3)}\n"
            hello.text = "${hello.text}${appendStrings("str=","abc",123,true,'c',null,"\n//--byAppendString")}\n"
            for(i in ll){ hello.text = "${hello.text}$i, " }
            hello.text="${hello.text}\n"
            hello.text="${hello.text}10!=${factorial(10)}\n"
            hello.text="${hello.text}tailrec_func:${findFixPoint(0.5)}\n"
            val text = arrayOf("How", "do", "you", "do", "I'm", "fine")
            hello.text="${hello.text}the longest str: ${maxCustom(text) { a, b -> a.length > b.length}}\n"
            hello.text="${hello.text}normal str sort: ${maxCustom(text) { a, b -> a>b}}\n"
            hello.text="${hello.text}count:${stringMapper(hello.text as String) { input -> input.length+6 }}\n"
            val array:Array<Int> = arrayOf(1,2,3,4)
            hello.text="${hello.text}${setArrayStr(array)}\n"
            array.swap(0,3)
            hello.text="${hello.text}${setArrayStr(array)}\n"
            date_area.text="${DateUtil.nowDateTime}\n"
            true
        }
        button_new.setOnClickListener {
            val animal = Animal(this, "Chicken")
            hello.text = "animal class init: \n${animal}\n"
            val pig = AnimalSeparate(this, "pig")
//            val dog = AnimalSeparate(this, "dog", 1)
//            val huyoujin = AnimalSeparate(this, "pig", 0)
            var monkey = WildAnimal("mokey", 1)
            var piggy = WildAnimal("pig")
//            hello.text = "${hello.text}${monkey.name} is ${if(monkey.sex==0) "male" else "female"}\n"
//            hello.text = "${hello.text}${piggy.name} is ${if(piggy.sex==0) "male" else "female"}\n"
//            hello.text = "${hello.text}${monkey.name} is ${monkey.sexName}\n"
//            hello.text = "${hello.text}${piggy.name} is ${piggy.sexName}\n"
            hello.text = "${hello.text}${monkey.getDesc("MKz")}\n"
            hello.text = "${hello.text}${piggy.getDesc("PIGz")}\n"
        }
    }
}

class Animal(context: Context, name: String){
    init{
        context.toast("this is a $name")
    }
}

class AnimalSeparate{
    constructor(context: Context, name: String){
        context.toast("this is a $name")
    }
    constructor(context: Context,name: String,sex:Int){
        val sexName = if (sex==0) "male" else "female"
        context.toast("this is a $sexName $name")
    }
}

class WildAnimal(var name:String, val sex:Int=0){
    var sexName:String = if(sex==0) "male" else "female"
    fun getDesc(tag:String) = "welcome to $tag, this $name is $sexName"
}

inline fun <reified T : Number> setArrayStr(array:Array<T>):String? {
    var str = "array item: "
    for (item in array) {
        str = "$str$item, "
    }
    return str
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

fun factorial(n:Int):Int = if (n <= 1) n else n*factorial(n-1)

tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == cos(x)) x else findFixPoint(cos(x))

fun <T> maxCustom(array: Array<T>, greater: (T,T) -> Boolean):T?{
    var max:T? = null
    for(i in array){
        if(max == null || greater(i,max)){
            max = i
        }
    }
    return max
}

fun stringMapper(str: String, mapper: (String) -> Int): Int {
    // Invoke function
    return mapper(str)
}

fun Array<Int>.swap(pos1: Int, pos2: Int){
    this[pos1] = this[pos1] xor this[pos2]
    this[pos2] = this[pos1] xor this[pos2]
    this[pos1] = this[pos1] xor this[pos2]
}

object DateUtil {
    val nowDateTime: String
    get() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }
    val nowDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(Date())
        }
    val nowTime: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss")
            return sdf.format(Date())
        }
    val nowTimeDetail: String
        get() {
            val sdf = SimpleDateFormat("HH:mm:ss.SSS")
            return sdf.format(Date())
        }
    fun getFormatTime(format: String=""): String {
        val ft: String = format
        val sdf = if (!ft.isEmpty()) SimpleDateFormat(ft)
        else SimpleDateFormat("yyyyMMddHHmmss")
        return sdf.format(Date())
    }
}
