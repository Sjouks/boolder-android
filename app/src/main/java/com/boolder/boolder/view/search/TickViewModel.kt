package com.boolder.boolder.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boolder.boolder.R
import com.boolder.boolder.data.database.repository.TickRepository
import com.boolder.boolder.domain.convert
import kotlinx.coroutines.launch
import java.text.Normalizer

class TickViewModel(
    private val tickRepository: TickRepository,
) : ViewModel() {

    private val _result = MutableLiveData<List<BaseObject>>()
    val searchResult: LiveData<List<BaseObject>> = _result

    fun list(){
        viewModelScope.launch {
//            tickRepository.deleteAll()
            val areasWithProblems = tickRepository.getProblemsPerArea();

            _result.value = buildList {
                if (areasWithProblems.isNotEmpty()) {
                    add(CategoryHeader(R.string.category_ticks))
                    areasWithProblems.forEach { areaWithProblems ->
                        add(areaWithProblems.areaEntity.convert())
                        for (problem in areaWithProblems.problems){
                            val state_entity = tickRepository.loadById(problem.id)
                            if (state_entity != null) {
                                add(problem.convert(state=state_entity.state))
                            }
                            else{
                                add(problem.convert())
                            }
                        }
                    }
                }
            }
        }
    }

    fun search(query: String?) {
        viewModelScope.launch {
            val pattern = query
                ?.takeIf { it.isNotBlank() }
                ?.let { "%${it.normalized()}%" }
                .orEmpty()

            val areasWithProblems = tickRepository.getProblemsByNamePerArea(pattern)

            _result.value = buildList {
                if (areasWithProblems.isNotEmpty()) {
                    add(CategoryHeader(R.string.category_ticks))
                    areasWithProblems.forEach { areaWithProblems ->
                        add(areaWithProblems.areaEntity.convert())
                        for (problem in areaWithProblems.problems){
                            val state_entity = tickRepository.loadById(problem.id)
                            if (state_entity != null) {
                                add(problem.convert(state=state_entity.state))
                            }
                            else{
                                add(problem.convert())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun String.normalized() =
        Normalizer.normalize(this, Normalizer.Form.NFD)
            .replace(REGEX_EXCLUDED_CHARS, "")
            .lowercase()

    companion object {
        private val REGEX_EXCLUDED_CHARS = Regex("[^0-9a-zA-Z]")
    }
}