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

@Suppress("NAME_SHADOWING")
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
//            val pig = AnimalSeparate(this, "pig")
//            val dog = AnimalSeparate(this, "dog", 1)
            val monkey = WildAnimalCompanion("monkey", WildAnimalCompanion.FEMALE)
            val piggy = WildAnimalCompanion("pig")
//            hello.text = "${hello.text}${monkey.name} is ${if(monkey.sex==0) "male" else "female"}\n"
//            hello.text = "${hello.text}${piggy.name} is ${if(piggy.sex==0) "male" else "female"}\n"
//            hello.text = "${hello.text}${monkey.name} is ${monkey.sexName}\n"
//            hello.text = "${hello.text}${piggy.name} is ${piggy.sexName}\n"
            hello.text = "${hello.text}${monkey.getDesc("MKz")}\n"
            hello.text = "${hello.text}${piggy.getDesc("PIGz")}\n"
            hello.text="${hello.text}[static] id of male is ${WildAnimalCompanion.judgeSex("male")}\n"
//            WildAnimalCompanion.FEMALE
            val tim=Tiger(name = "Tim", sex=WildAnimalCompanion.FEMALE)
            hello.text = "${hello.text}${tim.getDesc("tim's home")}\n"
            val cock=Cock()
            hello.text = "${hello.text}${cock.callOut(7)}\n"
            val hen=Hen()
            hello.text = "${hello.text}${hen.callOut(8)}\n"
            val goose=Goose()
            hello.text = "${hello.text}" +
                    "skill:\n" +
                    "  ${goose.run()}\n" +
                    "  ${goose.fly()}\n" +
                    "  ${goose.swim()}\n" +
                    "skilledSports: ${goose.skilledSports}\n"
            val fowl = when(count){
                0 -> WildFowl("penguin", WildAnimalCompanion.FEMALE, BehaviorSwim())
                1 -> WildFowl("chicken",WildAnimalCompanion.MALE, BehaviorRun())
                else -> WildFowl("wild goose",WildAnimalCompanion.MALE, BehaviorFly())
            }
            count = (count+1)%3
            hello.text = "${hello.text}${fowl.name}:\n  ${fowl.fly()}\n  ${fowl.swim()}\n  ${fowl.run()}\n"
        }
        button_new.setOnLongClickListener {
            hello.text = "nested class: ${Tree("green").getColor()} and ${Tree.Flower("red").getColor()}\n"
            hello.text = "${hello.text}${Tree("blue").Fruit("black").getColor()}\n"
            hello.text = "${hello.text}season[$count]: ${when(count){
                SeasonType.SPRING.ordinal -> SeasonType.SPRING.seasonName
                SeasonType.SUMMER.ordinal -> SeasonType.SUMMER.seasonName
                SeasonType.AUTUMN.ordinal -> SeasonType.AUTUMN.seasonName
                else -> SeasonType.WINTER.seasonName
            }}\n"
            var season = when(count){
                0 -> SeasonSealed.Spring("spring")
                1 -> SeasonSealed.Summer("summer")
                2 -> SeasonSealed.Autumn("autumn")
                else -> SeasonSealed.Spring("winter")
            }
            hello.text = "${hello.text}season[$count]: ${when(season){
                is SeasonSealed.Spring -> season.name
                is SeasonSealed.Summer -> season.name
                is SeasonSealed.Autumn -> season.name
                is SeasonSealed.Winter -> season.name
            }}\n"
            count = (count+1)%4
            true
        }
    }
}

enum class SeasonType(val seasonName: String) {
    SPRING("spring"),
    SUMMER("summer"),
    AUTUMN("autumn"),
    WINTER("winter")
}

sealed class SeasonSealed{
    class Spring(var name:String):SeasonSealed()
    class Summer(var name:String):SeasonSealed()
    class Autumn(var name:String):SeasonSealed()
    class Winter(var name:String):SeasonSealed()
}

// nested class and inner class
class Tree(var treeColor:String){
    fun getColor() = "This is a $treeColor tree"
    class Flower(private var flowerColor:String){
        fun getColor() = "This is a $flowerColor flower"
    }
    inner class Fruit (private var fruitColor:String){
        fun getColor() = "There is a $fruitColor fruit on $treeColor tree"
    }
}

class Animal(context: Context, name: String){
    init{
        context.toast("this is a $name")
    }
}

/*
class AnimalSeparate{
constructor(context: Context, name: String){
context.toast("this is a $name")
}
constructor(context: Context,name: String,sex:Int){
val sexName = if (sex==0) "male" else "female"
context.toast("this is a $sexName $name")
}
}
*/

open class WildAnimalCompanion(var name:String, sex:Int = MALE){
    var sexName:String = if(sex==MALE) "male" else "female"
    open fun getDesc(tag:String) = "welcome to $tag, this $name is $sexName"
    companion object WildAnimal{
        const val MALE = 0
        const val FEMALE = 1
        private const val UNKNOWN = -1
        fun judgeSex(sexName: String):Int{
            return when(sexName){
                "male" -> MALE
                "female" -> FEMALE
                else -> UNKNOWN
            }
        }
    }
}

class Tiger(name: String="tiger", sex:Int=MALE): WildAnimalCompanion(name,sex){
    override fun getDesc(tag:String) = "I am $name ($sexName).\n"
}

abstract class Chicken(name: String, sex:Int, var voice:String) :WildAnimalCompanion(name,sex){
    abstract fun callOut(times:Int):String
}
class Cock(name: String="chicken", sex: Int=MALE, voice: String="wo~wo~wo~"):Chicken(name, sex, voice){
    override fun callOut(times: Int) = "the $sexName $name call out $voice $times times.\n"
}
class Hen(name: String="chicken", sex: Int=FEMALE, voice: String="ge~ge~ge~"):Chicken(name, sex, voice){
    override fun callOut(times: Int) = "the $sexName $name call out $voice $times times.\n"
}

interface Behavior{
    fun fly():String
    fun swim():String
    fun run():String = "most of animals can run, except fish..."
    var skilledSports:String
}

class BehaviorFly:Behavior{
    override fun fly(): String = "I can fly~~~~"
    override fun run(): String = "run? WHAT????"
    override fun swim(): String = "I can't swim..."
    override var skilledSports: String = "flying"
}

class BehaviorRun:Behavior{
    override fun fly(): String = "Fly?? r u kidding?"
    override fun run(): String = "run? YES!!"
    override fun swim(): String = "I can't swim..."
    override var skilledSports: String = "running"
}

class BehaviorSwim:Behavior{
    override fun fly(): String = "I can not fly!"
    override fun run(): String = "run? WELL..."
    override fun swim(): String = "I like swimming!!"
    override var skilledSports: String = "swimming"
}

class WildFowl(name:String="fowl", sex:Int=MALE, behavior:Behavior=BehaviorRun()) : WildAnimalCompanion(name, sex), Behavior by behavior{
}

class Goose(name: String = "goose", sex: Int = MALE):WildAnimalCompanion(name, sex),Behavior{
    override fun fly(): String = "$name can fly but not far not high"
    override fun swim(): String = "$name can swim well"
//    override fun run(): String = super.run()
    override var skilledSports: String = "swimming"
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
    var str = tag
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
    @SuppressLint("SimpleDateFormat")
    get() {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }
    /*
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
*/
}
