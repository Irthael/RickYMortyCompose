package com.dani.composerickandmorty.model.mycharacters

import com.dani.composerickandmorty.model.database.entities.CharactersEntity
import com.dani.domain.*

fun CharactersEntity.toCharactersDomain(): Mycharacter = Mycharacter(
    id,
    name,
    status,
    species,
    type,
    gender,
    origin,
    location,
    image,
    episode,
    url,
    created,
)

fun Mycharacter.toCharactersEntity(): CharactersEntity =
    CharactersEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin,
        location = this.location,
        image = this.image,
        episode = this.episode,
        url = this.url,
        created = this.created
    )

