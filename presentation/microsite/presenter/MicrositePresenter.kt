package ru.mts.mtstv.getshop.presentation.microsite.presenter

interface MicrositePresenter {
    fun sendAction(micrositeAction: String)

    fun showMicrosite(micrositeURL: String)

    fun back()
}