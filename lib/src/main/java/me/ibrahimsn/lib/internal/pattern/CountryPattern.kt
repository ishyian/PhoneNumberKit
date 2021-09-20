package me.ibrahimsn.lib.internal.pattern

import me.ibrahimsn.lib.internal.model.Character

class CountryPattern private constructor(
    private val pattern: CharArray
) : Pattern {

    val length: Int
        get() = pattern.size

    override fun apply(number: CharSequence): String {
        val numberArr = number.filter { i -> i.isDigit() }
        var cursor = 0

        return StringBuilder().apply {
            pattern.forEach { key ->
                if (cursor < numberArr.length) when (key) {
                    Character.PLUS.key -> {
                        append(Character.PLUS.char)
                    }
                    Character.SPACE.key -> {
                        append(Character.SPACE.char)
                    }
                    Character.DASH.key -> {
                        append(Character.DASH.char)
                    }
                    Character.DIGIT.key -> {
                        append(numberArr[cursor++])
                    }
                }
            }
        }.toString()
    }

    companion object {

        fun create(number: String?) = CountryPattern(
            number.orEmpty()
                .replace("(\\d)".toRegex(), Character.DIGIT.key.toString())
                .replace("(\\s)".toRegex(), Character.SPACE.key.toString())
                .toCharArray()
            )
    }
}