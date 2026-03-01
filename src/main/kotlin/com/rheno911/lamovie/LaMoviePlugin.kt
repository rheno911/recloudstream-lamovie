package com.rheno911.lamovie

import com.recloudstream.cloudstream3.ProvidersRepo

class LaMoviePlugin : ProvidersRepo() {
    override val repoId = "rheno911-lamovie"
    override val name = "Rheno LaMovie"
    
    override val providers = arrayOf(LaMovieProvider())
}
