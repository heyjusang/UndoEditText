package hey.jusang.undoedittext.memento

internal class Memento constructor(
    val start: Int,
    val before: CharSequence?,
    val after: CharSequence?
)