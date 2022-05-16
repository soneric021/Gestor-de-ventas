package com.rikson.gestordeventas.utils

fun Double.addAutomaticThousandSeparator()
        = toInt()
    .toString()
    .reversed()
    .chunked(3)
    .joinToString(",")
    .reversed() + if (this % 1 > 0) ".${(this % 1).toString().split(".")[1].take(2)}" else ""
