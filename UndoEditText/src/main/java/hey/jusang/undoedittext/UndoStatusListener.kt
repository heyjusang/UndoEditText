package hey.jusang.undoedittext

interface UndoStatusListener {
    fun onUndoStatusChanged(canUndo: Boolean)
    fun onRedoStatusChanged(canRedo: Boolean)
}