package com.zil.codex.shared.repositories

import android.content.Context
import android.util.Log
import com.zil.codex.shared.api.services.CardService
import com.zil.codex.shared.hilt.modules.ApiModule
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import javax.inject.Inject


class CardRepository @Inject constructor(
	@ApplicationContext private val context: Context,
	@ApiModule.CodexRetrofitClient private val api: Retrofit
) {
	private var cardService: CardService = api.create(CardService::class.java)

	suspend fun fetch10E() = withContext(Dispatchers.IO) {
		val response = cardService.get10E().execute().body()
		response
	}

	suspend fun fetchSetList() = withContext(Dispatchers.IO) {
		val response = cardService.getSetList().execute().body()
		response
	}

	suspend fun fetchAllPrintings() = withContext(Dispatchers.IO) {
		cardService.getAllPrintings().enqueue(object : Callback<ResponseBody?> {
			override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
				if (response.isSuccessful) {
					Log.d("zxcv", "server contacted and has file")
					val writtenToDisk = writeResponseBodyToDisk(response.body())
					Log.d("zxcv", "file download was a success? $writtenToDisk")
				} else {
					Log.d("zxcv", "server contact failed")
				}
			}

			override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
				Log.e("zxcv", "error")
			}
		})
	}


	private fun writeResponseBodyToDisk(body: ResponseBody?): Boolean {
		if (body == null) return false
		return try {
			val futureStudioIconFile =
				File(
					context.getExternalFilesDir("Codex")
						.toString() + File.separator + "AllPrintings.json"
				)
			var inputStream: InputStream? = null
			var outputStream: OutputStream? = null
			try {
				val fileReader = ByteArray(4096)
				val fileSize = body.contentLength()
				var fileSizeDownloaded: Long = 0
				inputStream = body.byteStream()
				outputStream = FileOutputStream(futureStudioIconFile)
				while (true) {
					val read = inputStream.read(fileReader)
					if (read == -1) {
						break
					}
					outputStream.write(fileReader, 0, read)
					fileSizeDownloaded += read.toLong()
					Log.d("zxcv", "file download: $fileSizeDownloaded of $fileSize")
				}
				outputStream.flush()
				true
			} catch (e: IOException) {
				false
			} finally {
				inputStream?.close()
				outputStream?.close()
			}
		} catch (e: IOException) {
			false
		}
	}
}
