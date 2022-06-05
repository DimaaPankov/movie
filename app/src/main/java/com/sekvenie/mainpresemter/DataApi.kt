package com.sekvenie.mainpresemter

interface DataApi<T> {
   suspend fun getData(): T
}