package com.kiparo.wildcatalog.presentation.catalog.data

import com.kiparo.wildcatalog.presentation.catalog.model.AdvertisementItem
import com.kiparo.wildcatalog.presentation.catalog.model.AudioItem
import com.kiparo.wildcatalog.presentation.catalog.model.CatalogItem
import com.kiparo.wildcatalog.presentation.catalog.model.ProductItem
import com.kiparo.wildcatalog.presentation.catalog.model.VideoItem
import net.datafaker.Faker

private const val VIDEO_URL =
    "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
private const val IMAGE_URL_ADV = "https://picsum.photos/id/%d/200/300"
private const val IMAGE_URL_PRODUCT = "https://picsum.photos/id/%d/200/300"

//private const val IMAGE_URL_VIDEO = "https://picsum.photos/id/%d/500/300"
private const val IMAGE_URL_VIDEO =
    "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

private val images = listOf(
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerMeltdowns.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg",
"https://storage.googleapis.com/gtv-videos-bucket/sample/images/TearsOfSteel.jpg"
)

private val videoUrls = listOf(
    "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.jpg",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
    "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
)

class DataSource {

    lateinit var observer: (items: List<CatalogItem>) -> Unit

    private val faker = Faker()
    private val advs = mutableListOf<AdvertisementItem>()
    private val videos = mutableListOf<VideoItem>()
    private val audios = mutableListOf<AudioItem>()
    private val products = mutableListOf<ProductItem>()
    private val catalog = mutableListOf<CatalogItem>()

    fun generate() {
        videos()
        advs()
        products()
//        audios()
        catalog
            .apply {
                this.addAll(advs)
                this.addAll(videos)
                this.addAll(products)
                this.addAll(audios)
            }.apply {
                this.shuffle()
            }
    }

    fun getData(): List<CatalogItem> {
        return catalog.toList()
    }

    fun generateNext(advertisement: AdvertisementItem) {
        TODO("Not yet implemented")
    }

    fun generateNext(product: ProductItem) {
        TODO("Not yet implemented")
    }

    fun generateNext(video: VideoItem) {
        val index = videos.indexOf(video)
        val catalogIndex = catalog.indexOf(video)
        val id = generateId(videos)
        val newVideo = createVideo(id + 1)
        videos.add(index + 1, newVideo)
        catalog.add(catalogIndex + 1, newVideo)
        observer(getData())
    }

    fun delete(product: ProductItem) {
        products.remove(product)
        catalog.remove(product)
        observer(getData())
    }

    fun delete(advertisement: AdvertisementItem) {
        TODO("Not yet implemented")
    }

    fun delete(video: VideoItem) {
        TODO("Not yet implemented")
    }

    private fun generateId(videos: MutableList<VideoItem>): Int {
        videos.sortBy { video ->
            video.id.filter { id ->
                id.isDigit()
            }.toInt()
        }
        val id = videos.last().id.filter {
            it.isDigit()
        }.toInt()
        return id
    }


    private fun videos() {
        videos.addAll(mutableListOf<VideoItem>().apply {
            (1..30).forEach {
                this.add(
                    createVideo(it)
                )
            }
        })
    }

    private fun audios() {
        audios.addAll(mutableListOf<AudioItem>().apply {
            (1..30).forEach {
                this.add(
                    createAudio(it)
                )
            }
        })
    }

    private fun advs() {
        advs.addAll(mutableListOf<AdvertisementItem>().apply {
            (1..30).forEach {
                this.add(
                    createAdvertisement(it)
                )
            }
        })
    }

    private fun products() {
        products.addAll(mutableListOf<ProductItem>().apply {
            (1..30).forEach {
                this.add(
                    createProduct(it)
                )
            }
        })
    }

    private fun createAdvertisement(it: Int) = AdvertisementItem(
        id = "$it-advs",
        imageUrl = String.format(IMAGE_URL_ADV, it),
        title = "AD: ${faker.funnyName().name()}",
        description = faker.text().text(50),
        isFavorite = false
    )

    private fun createVideo(it: Int) = VideoItem(
        id = "$it-video",
        videoUri = videoUrls[it % videoUrls.size],
        title = "Video: ${videoUrls[it % videoUrls.size].split("/").last()}",
        description = faker.text().text(100)
    )

    private fun createAudio(it: Int) = AudioItem(
        id = "$it-video",
        title = "Audio: ${faker.funnyName().name()}",
    )

    private fun createProduct(it: Int) = ProductItem(
        id = "$it-product",
        imageUrl = String.format(IMAGE_URL_PRODUCT, it),
        title = faker.animal().scientificName(),
        price = "${faker.number().digits(3)} ${faker.money().currency()}"
    )

    fun toggleFavorite(id: String) {
        catalog.indexOfFirst {
            it.id() == id
        }.takeIf {
            it >= 0
        }?.run {
            val item = catalog[this] as AdvertisementItem
            catalog[this] = item.copy(
                isFavorite = !item.isFavorite
            )
            observer(getData())
        }
    }
}