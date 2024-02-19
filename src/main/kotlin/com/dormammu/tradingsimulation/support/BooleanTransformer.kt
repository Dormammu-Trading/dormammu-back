package com.dormammu.tradingsimulation.support

class BooleanTransformer {
    companion object {
        fun binaryToBoolean(binary: String): Boolean {
            return when (binary) {
                "1" -> true
                "0" -> false
                else -> throw Exception("잘못된 입력")
            }
        }

        fun booleanToBinary(boolean: Boolean): String {
            return if (boolean) "1" else "0"
        }
    }
}