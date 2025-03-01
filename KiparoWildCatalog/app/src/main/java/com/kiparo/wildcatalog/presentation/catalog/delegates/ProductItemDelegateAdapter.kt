package com.kiparo.wildcatalog.presentation.catalog.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.kiparo.wildcatalog.R
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import com.kiparo.wildcatalog.presentation.catalog.model.ProductItem

class ProductItemDelegateAdapter(
    private val productRemoveListener: (product: ProductItem) -> Unit,
) : CatalogItemDelegateAdapter<ProductItem, ProductItemDelegateAdapter.ViewHolder> {

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_catalog_product,
                parent,
                false
            ),
            removeListener = productRemoveListener
        )
    }

    override fun bindViewHolder(item: ProductItem, viewHolder: ViewHolder) {
        viewHolder.bind(item)
    }

    override fun bindViewHolder(
        item: ProductItem,
        viewHolder: ViewHolder,
        payloads: List<CatalogItem.PayloadChange>
    ) {
        viewHolder.bind(item)
    }

    override val itemClass = ProductItem::class.java


    class ViewHolder(
        itemView: View,
        private val removeListener: (product: ProductItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val productImage: ImageView = itemView.findViewById(R.id.img_product)
        private val productTitle: TextView = itemView.findViewById(R.id.txt_product_name)
        private val productPrice: TextView =
            itemView.findViewById(R.id.txt_product_price)
        private val productRemoveBtn: ImageButton =
            itemView.findViewById(R.id.btn_product_remove_btn)

        private val placeHolder = AppCompatResources.getDrawable(
            productImage.context,
            R.drawable.ic_product_placeholder
        )

        private val circularProgress = CircularProgressDrawable(productImage.context)

        fun bind(product: ProductItem) {
            productTitle.text = product.title
            productPrice.text = product.price

            circularProgress.strokeWidth = 5f
            circularProgress.centerRadius = 30f
            circularProgress.start()

            Glide
                .with(productImage.context)
                .load(product.imageUrl)
                .fitCenter()
                .placeholder(circularProgress)
                .error(placeHolder)
                .into(productImage)

            productRemoveBtn.setOnClickListener { removeListener(product) }
        }
    }

}