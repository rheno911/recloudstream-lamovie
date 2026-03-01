package com.rheno911.lamovie

import com.recloudstream.cloudstream3.MainAPI
import com.recloudstream.cloudstream3.LoadResponse
import com.recloudstream.cloudstream3.SearchResponse
import com.recloudstream.cloudstream3.ExtractorLink
import com.lagradost.cloudstream3.utils.M3u8Helper
import kotlinx.serialization.json.Json
import com.lagradost.cloudstream3.app

class LaMovieProvider : MainAPI() {
    override var mainUrl = "https://la.movie"
    override var name = "LaMovie"
    override val supportedTypes = setOf(TvType.Movie)
    
    override suspend fun search(query: String): List<SearchResponse> {
        // Test with your movie ID 61323
        return listOf(
            MovieSearchResponse(
                name = "$query (la.movie)",
                url = "/p=61323",
                apiName = name
            )
        )
    }
    
    override suspend fun load(url: String): LoadResponse {
        val id = url.substringAfter("p=").takeWhile { it.isDigit() }
        return MovieLoadResponse(
            name = "Una loca película de vampiros (2010)",
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
        // Your WORKING Vimeo stream from la.movie
        callback(
            ExtractorLink(
                source = "Vimeo HD",
                name = "LaMovie Vimeo",
                url = "https://s15.vimeos.zip/hls2/01/00009/8l2e7h1uapnz_h/master.m3u8",
                referer = "https://player.vimeo.com/",
                quality = Qualities.`1080`.value
            )
        )
    }
}
