package com.rheno911.lamovie

import com.recloudstream.cloudstream3.MainAPI
import com.recloudstream.cloudstream3.LoadResponse
import com.recloudstream.cloudstream3.SearchResponse
import com.recloudstream.cloudstream3.ExtractorLink
import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.M3u8Helper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LaMovieProvider : MainAPI() {
    override var mainUrl = "https://la.movie"
    override var name = "LaMovie"
    override val lang = "es"
    override val supportedTypes = setOf(TvType.Movie)

    override suspend fun search(query: String): List<SearchResponse> {
        // Test with YOUR la.movie movie ID 61323
        return listOf(
            MovieSearchResponse(
                name = "$query - la.movie",
                url = "/p=61323",
                apiName = name,
                posterUrl = "https://via.placeholder.com/300x450/FF6B6B/FFFFFF?text=LM"
            )
        )
    }

    override suspend fun load(url: String): LoadResponse {
        val id = url.removePrefix("/p=").takeWhile { it.isDigit() }
        return MovieLoadResponse(
            name = "Una loca película de vampiros (2010) [$id]",
            url = url,
            data = id,
            apiName = name
        )
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        // YOUR PROVEN WORKING Vimeo HLS from la.movie scrapes
        val vimeoUrl = "https://s15.vimeos.zip/hls2/01/00009/8l2e7h1uapnz_h/master.m3u8"
        callback(
            M3u8Helper.generateM3u8(
                name = "LaMovie Vimeo HD 1080p",
                url = vimeoUrl,
                referer = "https://player.vimeo.com/",
                quality = Qualities.`1080`.value
            )
        )
    }
}
