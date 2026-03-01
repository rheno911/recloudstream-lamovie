package com.rheno911.lamovie

import com.recloudstream.cloudstream3.ProvidersRepo
import com.recloudstream.cloudstream3.LoadResponse
import com.lagradost.cloudstream3.MainAPI

class Plugin : ProvidersRepo() {
    override val repoId = "rheno911-lamovie"
    override val name = "Rheno LaMovie"
    override val githubRelease = "rheno911/recloudstream-lamovie@latest"
    
    override val providers = arrayOf(LaMovieProvider())
}
