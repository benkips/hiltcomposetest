package com.mabnets.hiltcomposetest.data.remote.dto

import com.mabnets.hiltcomposetest.data.local.Entity.Newsentity
import com.mabnets.hiltcomposetest.domain.model.News

data class Newsdto(val id: Int,
                   val links: String,
                   val title: String,
                   val type: String,
                   val description: String,
                   val imagelink:String)


fun Newsdto.tonews(): News {
    return  News(
        id = id,
        links = links,
        title =title,
        type = type,
        description =description,
        imagelink = imagelink
    )

}
fun Newsdto.tonewsentity(): Newsentity {
    return  Newsentity(
        id = id,
        links = links,
        title =title,
        type = type,
        description =description,
        imagelink = imagelink
    )

}

