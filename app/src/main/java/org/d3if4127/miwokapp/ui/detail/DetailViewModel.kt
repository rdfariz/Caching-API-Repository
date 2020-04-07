package org.d3if4127.miwokapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4127.miwokapp.data.Miwok
import org.d3if4127.miwokapp.database.MiwokDAO
import org.d3if4127.miwokapp.database.MiwokDB
import org.d3if4127.miwokapp.database.MiwokRepository

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : MiwokRepository
    private val miwokDAO: MiwokDAO

    private val _miwok: LiveData<List<Miwok>>
    val miwok : LiveData<List<Miwok>> get() = _miwok

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private var job = Job()
    private val crScope = CoroutineScope(job + Dispatchers.Main)

    init {
        miwokDAO = MiwokDB.getInstance(application).miwokDAO
        repository = MiwokRepository(miwokDAO)
        crScope.launch{
            try{
                repository.refreshMiwok()
            }catch (t: Throwable){
                _message.value = "Anda Offline"
            }
        }
        _miwok = repository.miwok
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}