package ru.mts.mtstv.getshop.presentation.microsite.presenter

import ru.mts.mtstv.getshop.presentation.microsite.view.MicrositeFragment

class MicrositePresenterImpl(private val micrositeFragment: MicrositeFragment) : MicrositePresenter {
    private lateinit var history: ArrayList<String>

    override fun sendAction(micrositeAction: String) {
        micrositeFragment.doAction(micrositeAction)
    }

    override fun showMicrosite(micrositeURL: String) {
        setContentForMicrositeView(micrositeURL)
        micrositeFragment.show()
    }

    private fun setContentForMicrositeView(micrositeURL: String) {
        TODO("set microsite information to microsite view")
    }

    override fun back() {
        if (history.size > 0) {
            TODO("apply prev site to current")
        } else {
            micrositeFragment.hide()
        }
    }
}