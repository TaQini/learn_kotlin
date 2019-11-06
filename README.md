# Learn Kotlin

## keyword: var
 - Int, Byte, Short, Long, Boolean, Float, Double
 - IntArray, LongArray, FloatArray, DoubleArray, CharArray, BooleanArray
 - MutableSet<>, MutableList<>, MutableMap<>
  - isEmpty()
  - isNotEmpty()
  - clear()
  - contains()
  - iterator()
  - count()
```kotlin
var count: Int = 10
count = 15

var a:IntArray = intArrayOf(1,2,3)
a[1] = 34

var b:MutableList<Int> = mutableListOf(1,2,4,8)
val phone:Set<String> = setOf("Oneplus6", "iPhone8", "HuaweiP30")

```
 
## keyword val
 - does not change
 - String,
 - Set<>, List<>, Map<>
```kotlin
val languageName: String = "Kotlin"
```

### Null safety
```kotlin
// wrong 
val languageName: String = null

// force assign null
val languageName: String? = null
```
 - String? -> null or string

## Conditionals
```kotlin
if (count == 42) {
    println("I have the answer.")
} else if (count > 35) {
    println("The answer is close.")
} else {
    println("The answer eludes me.")
}
```

### conditional expressions
 - replacement of ternary operator(e.g. a=1:2?(b==1) )
```kotlin
val answerString: String = if (count == 42) {
    "I have the answer."
} else if (count > 35) {
    "The answer is close."
} else {
    "The answer eludes me."
}

println(answerString)
```

## Functions
```kotlin
fun generateAnswerString(countThreshold: Int): String {
    val answerString = if (count > countThreshold) {
        "I have the answer."
    } else {
        "The answer eludes me."
    }

    return answerString
}

// call it
val answerString = generateAnswerString(42)

```

### <T> - when type of args is unknow
```kotlin
fun <T> appendStrings(tag:String, vararg otherInfo:T?):String{
    var str ="$tag"
    for(i in otherInfo){
        str = "$str${i.toString()}"
    }
    return str
}
```

### Simplifying function declarations
 - return if ... else
```kotlin
fun generateAnswerString(countThreshold: Int): String {
    return if (count > countThreshold) {
        "I have the answer."
    } else {
        "The answer eludes me."
    }
}
```
 - assign operator ...
```kotlin
fun generateAnswerString(countThreshold: Int): String = if (count > countThreshold) {
        "I have the answer"
    } else {
        "The answer eludes me"
    }

```

 - function in one line
```kotlin
fun factorial(n:Int):Int = if (n <= 1) n else n*factorial(n-1)

```

### tail recursive functions
```kotlin
tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == cos(x)) x else findFixPoint(cos(x))
```

### Anonymous functions
 - Not every function needs a name. Some functions are more directly identified by their inputs and outputs. These functions are called _anonymous functions_.
```kotlin
val stringLengthFunc: (String) -> Int = { input ->
    input.length
}

// call it
val stringLength: Int = stringLengthFunc("Android")

```

### Higher-order functions
 - A function can take another function as an argument. Functions that use other functions as arguments are called _higher-order functions_.
```kotlin
fun stringMapper(str: String, mapper: (String) -> Int): Int {
    // Invoke function
    return mapper(str)
}

// call it
stringMapper("Android") {input -> input.length}

```
 - other
```kotlin
fun <T> maxCustom(array: Array<T>, greater: (T,T) -> Boolean):T?{
    var max:T? = null
    for(i in array){
        if(max == null || greater(i,max)){
            max = i
        }
    }
    return max
}

// call it
var text ="normal str sort: ${maxCustom(text) { a, b -> a.length > b.length}}\n"
```
### vararg - number of function args is varable
```kotlin
fun sum3plusDigit(a:Int, b:Int, c:Int, vararg otherInfo: Int):Int{
    var sum = 0
    sum += a + b + c
    for(i in otherInfo){
        sum += i
    }
    return sum
}
```

### system fucntion extension
```kotlin
fun Array<Int>.swap(pos1: Int, pos2: Int){
    this[pos1] = this[pos1] xor this[pos2]
    this[pos2] = this[pos1] xor this[pos2]
    this[pos1] = this[pos1] xor this[pos2]
}

```

## Classes
 - member in class
```kotlin
class WildAnimal(var name:String, val sex:Int=0){
    var sexName:String = if(sex==0) "male" else "female"
    fun getDesc(tag:String) = "welcome to $tag, this $name is $sexName"
}

// call
var monkey = WildAnimal("mokey", 1)
var piggy = WildAnimal("pig")
hello.text = "${hello.text}${monkey.getDesc("MKz")}\n"
hello.text = "${hello.text}${piggy.getDesc("PIGz")}\n"
```

 - 2 type of constructor 
```kotlin
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
```

### Companion object & static args
```kotlin
class WildAnimalCompanion(var name:String, sex:Int = MALE){
    var sexName:String = if(sex==MALE) "male" else "female"
    fun getDesc(tag:String) = "welcome to $tag, this $name is $sexName"
    companion object WildAnimal{
        val MALE = 0
        val FEMALE = 1
        val UNKNOWN = -1
        fun judgeSex(sexName: String):Int{
            var sex:Int = when(sexName){
                "male" -> MALE
                "female" -> FEMALE
                else -> UNKNOWN
            }
            return sex
        }
    }
}

// call it
val text = [static] id of male is ${WildAnimalCompanion.judgeSex("male")}\n"
val number = WildAnimalCompanion.FEMALE
```

### inheritance
 - class in kotlin is final, make class `open` first
```kotlin
open class WildAnimalCompanion(var name:String, sex:Int = MALE){...}

class tiger(name: String="tiger", sex:Int=0):WildAnimalCompanion(name,sex){
    //...
}
```
 - more type of class: public(default), private, protected, internal
 - override function (make the function open first)
```kotlin
open class WildAnimalCompanion(var name:String, sex:Int = MALE){
    var sexName:String = if(sex==MALE) "male" else "female"
    open fun getDesc(tag:String) = "welcome to $tag, this $name is $sexName"
}

class Tiger(name: String="tiger", sex:Int=MALE): WildAnimalCompanion(name,sex){
    override fun getDesc(tag:String) = "I am $name ($sexName).\n"
}
```

### abstract class 
```kotlin
abstract class Chicken(name: String, sex:Int, var voice:String) :WildAnimalCompanion(name,sex){
    abstract fun callOut(times:Int):String
}
class Cock(name: String="chicken", sex: Int=MALE, voice: String="wo~wo~wo~"):Chicken(name, sex, voice){
    override fun callOut(times: Int) = "the $sexName $name call out $voice $times times.\n"
}
class Hen(name: String="chicken", sex: Int=FEMALE, voice: String="ge~ge~ge~"):Chicken(name, sex, voice){
    override fun callOut(times: Int) = "the $sexName $name call out $voice $times times.\n"
}

//call it
var cock=Cock()
cock.callOut(7)
var hen=Hen()
hen.callOut(8)
```

### interface 
 - use interface to complete multi-inheritance
 - no construstor function in interface
 - function in interface are abstract defaultly
 - e.g. View.OnClickListener, CompoundButton.OnCheckedChangeListener ...

```kotlin
interface Behavior{
    open abstract fun fly():String
    fun swim():String
    fun run():String = "most of animals can run, except fish..."
    var skilledSports:String
}

class Goose(name: String = "goose", sex: Int = MALE):WildAnimalCompanion(name, sex),Behavior{
    override fun fly(): String = "$name can fly but not far not high"
    override fun swim(): String = "$name can swim well"
//    override fun run(): String = super.run()
    override var skilledSports: String = "swimming"
}

//call it 
val info = "skill:\n" +
           "  ${goose.run()}\n" +
           "  ${goose.fly()}\n" +
           "  ${goose.swim()}\n" +
           "skilledSports: ${goose.skilledSports}\n"
```

### Delegation
```kotlin
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

// Delegation - use it by keyword 'by'
class WildFowl(name:String, sex:Int, behavior:Behavior) : WildAnimalCompanion(name, sex), Behavior by behavior{
}

//call it
val fowl = when(count){
    0 -> WildFowl("penguin", WildAnimalCompanion.FEMALE, BehaviorSwim())
    1 -> WildFowl("chicken",WildAnimalCompanion.MALE, BehaviorRun())
    else -> WildFowl("wild goose",WildAnimalCompanion.MALE, BehaviorFly())
}
count = (count+1)%3
val info:String = "${fowl.name}:\n  ${fowl.fly()}\n  ${fowl.swim()}\n  ${fowl.run()}\n"

```
### Other Class
 - nested and inner class
```kotlin
class Tree(var treeColor:String){
    fun getColor() = "This is a $treeColor tree"
    class Flower(private var flowerColor:String){
        fun getColor() = "This is a $flowerColor flower"
    }
    inner class Fruit (private var fruitColor:String){
        fun getColor() = "There is a $fruitColor fruit on $treeColor tree"
    }
}

//call it
text = "nested class: ${Tree("green").getColor()} and ${Tree.Flower("red").getColor()}\n"
text = "$text${Tree("blue").Fruit("black").getColor()}\n"
```
 - Enum class
```kotlin
enum class SeasonType(val seasonName: String) {
    SPRING("spring"),
    SUMMER("summer"),
    AUTUMN("autumn"),
    WINTER("winter")
}

//call it
text = "${text}season: ${when(count++%4){
    0 -> SeasonType.SPRING.seasonName
    1 -> SeasonType.SUMMER.seasonName
    2 -> SeasonType.AUTUMN.seasonName
    else -> SeasonType.WINTER.seasonName
}}"
```
 - Sealed class
```kotlin
sealed class SeasonSealed{
    class Spring(var name:String):SeasonSealed()
    class Summer(var name:String):SeasonSealed()
    class Autumn(var name:String):SeasonSealed()
    class Winter(var name:String):SeasonSealed()
}

//call it
var season = when(count){
    0 -> SeasonSealed.Spring("spring")
    1 -> SeasonSealed.Summer("summer")
    2 -> SeasonSealed.Autumn("autumn")
    else -> SeasonSealed.Spring("winter")
}
text = "${text}season[$count]: ${when(season){
    is SeasonSealed.Spring -> season.name
    is SeasonSealed.Summer -> season.name
    is SeasonSealed.Autumn -> season.name
    is SeasonSealed.Winter -> season.name
}}\n"
```
 - Data class
```kotlin
data class Plant(var name:String, var stem:String, var leaf:String, var flower:String, var fruit:String, var seed:String) {}

//call it
var lotus = Plant("莲","莲藕","荷叶","荷花","莲蓬","莲子")
var lotus2 = lotus.copy(flower = "莲花")
//lotus2.flower = "莲花"
hello.text = "${hello.text}lotus[${count%2}]: ${when(count%2){
    0 -> lotus.toString()
    else -> lotus2.toString()
}}\n"
```
## Loop 
```kotlin
val phone:List<String> = listOf("Oneplus 6", "iPhone 8", "Huawei P30")
var desc = ""

// for-in
for(i in phone.indices){
    desc += "phone3: ${phone[i]}\n"
}

// forEach
phone.forEach { i ->
    desc += "phone: $i\n"
}

// iterator(like ptr in C)
val p = phone.iterator()
while(p.hasNext()){
    val i = p.next()
    desc += "phone: $i\n"
}

```
# Android 
## get id of all xml elements
```kotlin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.text = "233333"
    }

}
```
 - `hello` is an id in activity\_main.xml
```xml
<TextView
        android:id="@+id/hello"
        android:text="Hello world!"
    />

```
 - TextView palette was found by id
 - botton Listener
```kotlin
...
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    ...

    button_ok.setOnClickListener {
        hello.text = "I Love Nier"
        button_ok.text = "Dinger:"
        toast("Dinger say ...")
    }
    ...
}
```


