package com.duyts.imageapplication.adapter

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("profile")
	val profile: Profile? = null
)

data class MomentItem(

	@field:SerializedName("date")
	val date: Int? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("latlong")
	val latlong: String? = null,

	@field:SerializedName("location")
	val location: String? = null
)

data class Profile(

	@field:SerializedName("images")
	val images: List<String>? = null,

	@field:SerializedName("birthdate")
	val birthdate: Int? = null,

	@field:SerializedName("education")
	val education: String? = null,

	@field:SerializedName("work_date")
	val workDate: Int? = null,

	@field:SerializedName("hobbies")
	val hobbies: List<String?>? = null,

	@field:SerializedName("work")
	val work: String? = null,

	@field:SerializedName("songs")
	val songs: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("moment")
	val moment: List<MomentItem?>? = null
)
