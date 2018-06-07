package ru.isanechek.common.tools

interface Mapper<F, T> {
    fun map(from: F): T
    fun map(from: List<F>): List<T>
}