UndoEditText
===========
[![](https://jitpack.io/v/heyjusang/UndoEditText.svg)](https://jitpack.io/#heyjusang/UndoEditText)

Android EditText supporting Undo/Redo

Download
--------
### Gradle:
Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ..
    maven { url 'https://jitpack.io' }
  }
}
```
Step 2. Add the dependency:
```gradle	
dependencies {
  implementation 'com.github.heyjusang:UndoEditText:1.0.0'
}
```

### or Maven:
Step 1. Add the JitPack repository to your build file
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Step 2. Add the dependency:
```xml
<dependency>
  <groupId>com.github.heyjusang</groupId>
  <artifactId>UndoEditText</artifactId>
  <version>1.0.0</version>
</dependency>
```

Usage
-----
Just replace EditText with UndoEditText!

In xml,
```xml
<hey.jusang.undoedittext.UndoEditText
  android:id="@+id/undoEditText"
  android:layout_width="300dp"
  android:layout_height="150dp" />
```

In Kotlin,
```kotlin
var undoEditText: UndoEditText = UndoEditText(context)
```

### Undo/Redo
```kotlin
undoEditText.undo()
undoEditText.redo()
```

### Check if Undo/Redo is possible
``` kotlin
undoEditText.canUndo()
undoEditText.canRedo()
```

### History Max Size
Set max size of undo/redo history.
``` kotlin
undoEditText.setMaxHistorySize(10)

// Set history size to infinite (default)
undoEditText.setMaxHistorySize(UndoEditText.HISTORY_INFINITE)
```

### Clear History
``` kotlin
undoEditText.clearHistory()
```

### UndoStatusListener
``` kotlin
undoEditText.setUndoStatusListener(object: UndoStatusListener {
  override fun onUndoStatusChanged(canUndo: Boolean) {
    // Implement something. (e.g. disable/enable undo button)
  }

  override fun onRedoStatusChanged(canRedo: Boolean) {
    // Implement something. (e.g. disable/enable redo button)
  }
})
```

Features
--------
* supports undo/redo
* supports setting maximum size of history
* supports undo/redo status changed listener
