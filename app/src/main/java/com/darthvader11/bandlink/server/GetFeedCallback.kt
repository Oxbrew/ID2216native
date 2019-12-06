package com.darthvader11.bandlink.server

import org.json.JSONArray


interface GetFeedCallback {

    fun done(returnedCode: JSONArray)


}