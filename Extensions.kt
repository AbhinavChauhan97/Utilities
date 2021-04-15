package com.abhinav.chouhan.dmo

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 method of return the last location in sychronous fasion so that you don't need to use any callback in your code 
 @return the last location of the device **/
@SuppressLint("MissingPermission")
suspend fun FusedLocationProviderClient.awaitLastLocation() =
    suspendCoroutine<Location> { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }
    }

/**
 method returns the drawble associated with the provided id
 this method just makes your code smaller , now in your activity you don't have to say
 ContextCompat.getDrawble(this,R.id.your_drawble) just say getAsDrawble(R.id.your_drawble)
**/
fun Context.getAsDrawble(id:Int) = ContextCompat.getDrawble(this,id)

/**
similar to above to use in frament without using requireActivity().getAsDrawble(Int) 
**/

fun Fragment.getAsDrawble(id:Int) = ContextCompat.getDrawble(this.requireActivity(),id)


