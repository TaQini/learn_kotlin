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

## Loop 
```
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
