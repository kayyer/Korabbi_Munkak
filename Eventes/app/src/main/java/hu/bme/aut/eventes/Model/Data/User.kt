package hu.bme.aut.eventes.Model.Data

import kotlin.collections.ArrayList

data class User(var friends: ArrayList<Friend> ?= null,
                var nickname: String ?=null, var email: String ?= null,
                var description: String ?= null,
                var ratings: ArrayList<FriendRating> ?= null)