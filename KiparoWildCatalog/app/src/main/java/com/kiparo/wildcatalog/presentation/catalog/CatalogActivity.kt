package com.kiparo.wildcatalog.presentation.catalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.databinding.ActivityCatalogBinding
import com.kiparo.wildcatalog.presentation.catalog.data.DataSource

const val TAG = "Catalog"

class CatalogActivity : AppCompatActivity() {

    private val dataSource = DataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSource.generate()

        val catalogAdapter = CatalogAdapter(
            productRemoveListener = { product ->
                dataSource.delete(product)
            },
            generateNextVideoListener = { video ->
                dataSource.generateNext(video)
            },
            onFavoritesToggle = {adv ->
                dataSource.toggleFavorite(adv.id)
            }
        ).apply {
            items = dataSource.getData()
        }

        dataSource.observer = {
            catalogAdapter.items = dataSource.getData()
        }

        with(binding.catalogRecyclerView) {
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            adapter = catalogAdapter
            addItemDecoration(SpaceDecoration(resources.getDimensionPixelSize(R.dimen.padding_default)))
        }
    }
}