package com.zil.codex.shared.hilt.modules

import com.google.gson.GsonBuilder
import com.zil.codex.shared.constants.UrlConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {

	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class BaseOkHttpClient

	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class BaseRetrofitClient

	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class CodexRetrofitClient

	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class GsonConverterFactoryQualifier

	@BaseOkHttpClient
	@Singleton
	@Provides
	fun providesBaseOkHttpClient() : OkHttpClient =
		OkHttpClient.Builder()
			.apply {
				readTimeout(30000, TimeUnit.MILLISECONDS)
				writeTimeout(30000, TimeUnit.MILLISECONDS)
				connectTimeout(15000, TimeUnit.MILLISECONDS)
			}
			.build()

	@BaseRetrofitClient
	@Singleton
	@Provides
	fun providesBaseRetrofitClient(
		@BaseOkHttpClient baseOkHttpClient: OkHttpClient
	): OkHttpClient = baseOkHttpClient.newBuilder().apply {
		//Add interceptors here
	}.build()

	@CodexRetrofitClient
	@Singleton
	@Provides
	fun providesRetrofitClient(
		@BaseRetrofitClient baseRetrofitClient: OkHttpClient
	): Retrofit = Retrofit.Builder()
		.client(baseRetrofitClient)
		.baseUrl(UrlConstants.baseMTGRetrofitUrl)
		.build()

	@GsonConverterFactoryQualifier
	@Singleton
	@Provides
	fun providesGsonConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create(GsonBuilder().create())
}
