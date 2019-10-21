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
stringMapper("Android", { input ->
    input.length
})

```

## Classes
 ...

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
