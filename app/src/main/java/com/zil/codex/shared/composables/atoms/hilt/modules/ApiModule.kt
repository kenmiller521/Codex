package com.zil.codex.shared.composables.atoms.hilt.modules

import com.google.gson.GsonBuilder
import com.zil.codex.BuildConfig
import com.zil.codex.shared.constants.UrlConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

	@Qualifier
	@Retention(AnnotationRetention.BINARY)
	annotation class HttpLoggingInterceptorQualifier

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
		@BaseOkHttpClient baseOkHttpClient: OkHttpClient,
		@HttpLoggingInterceptorQualifier httpLoggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient = baseOkHttpClient.newBuilder().apply {
		//Add interceptors here
		if(BuildConfig.DEBUG){
			addInterceptor(httpLoggingInterceptor)
		}
	}.build()

	@CodexRetrofitClient
	@Singleton
	@Provides
	fun providesRetrofitClient(
		@BaseRetrofitClient baseRetrofitClient: OkHttpClient,
		@GsonConverterFactoryQualifier gsonConverterFactory: GsonConverterFactory
	): Retrofit = Retrofit.Builder()
		.client(baseRetrofitClient)
		.baseUrl(UrlConstants.baseMTGRetrofitUrl)
		.addConverterFactory(gsonConverterFactory)
		.build()

	@GsonConverterFactoryQualifier
	@Singleton
	@Provides
	fun providesGsonConverterFactory(): GsonConverterFactory =
		GsonConverterFactory.create(GsonBuilder().create())

	@HttpLoggingInterceptorQualifier
	@Singleton
	@Provides
	fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
		HttpLoggingInterceptor()
			.apply {
				level = HttpLoggingInterceptor.Level.BODY
			}
}
