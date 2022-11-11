package arsip.imaji.id.callback

import android.view.View
import arsip.imaji.id.model.Buy
import arsip.imaji.id.model.DataObject

interface FetchRecyclerViewItems {
    fun onItemClicked(view: View, product : DataObject)
}