# Development phase
Data Layer API version of app can be found on D:/wear-backup

# Dev Wiki
## How to make bigger button
```kotlin
Button(
    onClick = {},
    modifier = Modifier
        .width(ButtonDefaults.DefaultButtonSize * 2)
        .padding(bottom = ButtonDefaults.DefaultButtonSize/2)
) {
    Icon(
        imageVector = Icons.Default.Keyboard,
        contentDescription = "Use keyboard",
    )
}
```