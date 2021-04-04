package com.vettons.sample.vtsample.util

suspend fun <A> performGetOperation(networkCall: suspend () -> Resource<A>) : Resource<A>{

    return networkCall.invoke()
 }
