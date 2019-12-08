package com.darthvader11.bandlink.server

import org.json.JSONArray


interface GetCommentCallback {

    fun done(returnedCode: JSONArray)


}