package com.example.phrases.core

import com.example.phrases.data.model.Phrase

sealed class SealedClass1{
    object Loading: SealedClass1()
    data class Content(val sealedList: List<Phrase>): SealedClass1()
    object Error: SealedClass1()
}

