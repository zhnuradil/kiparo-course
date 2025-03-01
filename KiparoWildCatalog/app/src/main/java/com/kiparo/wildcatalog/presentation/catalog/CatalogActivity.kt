package com.kiparo.wildcatalog.presentation.catalog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.databinding.ActivityCatalogBinding
import com.kiparo.wildcatalog.presentation.catalog.data.DataSource
import com.kiparo.wildcatalog.presentation.catalog.delegates.AdvertisementDelegateAdapter
import com.kiparo.wildcatalog.presentation.catalog.delegates.ProductItemDelegateAdapter
import com.kiparo.wildcatalog.presentation.catalog.delegates.VideoStreamDelegateAdapter
import com.kiparo.wildcatalog.presentation.catalog.model.AdvertisementItem
import com.kiparo.wildcatalog.presentation.catalog.model.ProductItem

const val TAG = "Catalog"

class CatalogActivity : AppCompatActivity() {

    private val dataSource = DataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSource.generate()

        val productItemDelegateAdapter = ProductItemDelegateAdapter { product ->
            dataSource.delete(product)
        }

        val advertisementDelegateAdapter = AdvertisementDelegateAdapter { advertisement ->
            dataSource.toggleFavorite(advertisement.id)
        }

        val videoStreamDelegateAdapter = VideoStreamDelegateAdapter { video ->
            dataSource.generateNext(video)
        }

        val catalogCompositeAdapter = CatalogCompositeAdapter
            .Builder()
            .add(productItemDelegateAdapter)
            .add(advertisementDelegateAdapter)
            .add(videoStreamDelegateAdapter)
            .build()
            .apply {
                submitList(dataSource.getData())
            }

        dataSource.observer = {
            catalogCompositeAdapter.submitList(dataSource.getData())
        }

        with(binding.catalogRecyclerView) {
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            adapter = catalogCompositeAdapter
            addItemDecoration(SpaceDecoration(resources.getDimensionPixelSize(R.dimen.padding_default)))
        }
    }
}