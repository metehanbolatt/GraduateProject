package com.metehanbolat.graduateproject.mostusedfunctions

class DivideWords {

    fun getFirstPart(word : String) : String{
        var length = 0
        var subsEmail : String? = null
        while(length <= word.length - 1){
            if (word[length].toString() == "@"){
                subsEmail = (word.substring(0, length))
            }
            length += 1
        }
        return subsEmail.toString()
    }
}