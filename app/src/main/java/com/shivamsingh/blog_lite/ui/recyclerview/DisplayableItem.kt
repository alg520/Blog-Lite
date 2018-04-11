package com.aasaanjobs.partnerinternal.recyclerview

data class DisplayableItem<T>(val type: Int, val model: T) {

    companion object {

        fun <T> toDisplayableItem(model: T, type: Int): DisplayableItem<T> {
            return DisplayableItem<T>(type, model)
        }
    }
}